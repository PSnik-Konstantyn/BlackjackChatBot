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

import static bot.Player.Registration.playerRegistration;

public class BotController {
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

        RedissonClient redisson = Redisson.create();
        RMap<String, Player> playerBaseMap = redisson.getMap("myMap");

        TelegramBot bot = new TelegramBot(TOKEN);

        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                String playerName = update.message().from().firstName();
                long playerId = update.message().from().id();
                long chatId = update.message().chat().id();
                String messageText = update.message().text();
                if(!playerBaseMap.containsKey(String.valueOf(playerId))) {
                    playerRegistration(playerName, playerId, chatId, playerBaseMap);
                } else {
                    System.out.println("already registered");
                }
                System.out.println(update);
                String userText = update.message().text();
                bot.execute(new SendMessage(chatId, userText));
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

}
