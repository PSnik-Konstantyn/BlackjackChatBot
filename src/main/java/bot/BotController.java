package bot;

import bot.Player.Player;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

        TelegramBot bot = new TelegramBot(TOKEN);

        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                String playerName = update.message().from().firstName();
                long playerId = update.message().from().id();
                long chatId = update.message().chat().id();
                playerRegistration(playerName, playerId, chatId);
                System.out.println(update);
                String userText = update.message().text();
                bot.execute(new SendMessage(chatId, userText));
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

    private static void playerRegistration(String playerName, long playerId, long chatId) {
        Player newPlayer = new Player();
        newPlayer.setPlayerName(playerName);
        newPlayer.setPlayerId(playerId);
        newPlayer.setChatId(chatId);
        newPlayer.setBasicBet(1);
        newPlayer.setBalance(0);
        System.out.println("Im registered");
    }
}
