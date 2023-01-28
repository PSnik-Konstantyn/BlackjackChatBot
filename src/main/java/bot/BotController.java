package bot;

import bot.Player.Player;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static bot.Chat.Messages.sendMessage;
import static bot.Player.DailyBonus.getDailyBonus;
import static bot.RedissonDB.accountRegistration;


public class BotController {

    static RedissonClient redisson = Redisson.create();
    public static RMap<String, Player> playerDBMap = redisson.getMap("playerDBMap");

    public static void main(String[] args) {

        String TOKEN = "";
        try {
            String configFilePath = "src/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            TOKEN = prop.getProperty("TOKEN");

        } catch (IOException e) {
            e.printStackTrace();
        }

        TelegramBot bot = new TelegramBot(TOKEN);

        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {

                //:TODO create getting info from buttons too (if player presses button update message equals null)

                String playerName = "";
                long playerId = 0L;
                long chatId = 0L;
                String messageText = "";
                int messageId = 0;

                if (update.callbackQuery() == null) {
                    playerName = update.message().from().username();
                    playerId = update.message().from().id();
                    chatId = update.message().chat().id();
                    messageText = update.message().text();
                    messageId = update.message().messageId();
                } else if (update.message() == null) {
                    playerName = update.callbackQuery().from().username();
                    playerId = update.callbackQuery().from().id();
                    chatId = update.callbackQuery().message().chat().id();
                    messageText = update.callbackQuery().data();
                    messageId = update.callbackQuery().message().messageId();
                }

                if (!playerDBMap.containsKey(String.valueOf(playerId))) {
                    accountRegistration(playerName, playerId, chatId);
                } else {
                    System.out.println(playerDBMap.get(String.valueOf(playerId)).getPlayerName() + " already registered");
                }

                if (!(update.message() == null) || !(update.callbackQuery() == null) && !(messageText == null)) {
                    if (messageText.equals("/get_daily")) {
                        getDailyBonus(playerId, messageId, chatId);
                    }
                }
                System.out.println(update);
                bot.execute(new SendMessage(chatId, messageText));
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

}
