package bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class App {
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
                System.out.println("\n"+update);
                if (update.message() != null && update.message().text() != null  && !update.message().from().isBot()) {
                    long chatId = update.message().chat().id();

                    //String userText = update.message().text();
                    //bot.execute(new SendMessage(chatId, userText));

                    bot.execute(sendInlineKeyBoardMessage(chatId));

                } else if (update.callbackQuery() != null){
                    bot.execute(new SendMessage(update.callbackQuery().message().chat().id(), update.callbackQuery().from().firstName()+" сказал, что он гей"));
                    EditMessageText editMessageText = new EditMessageText(update.callbackQuery().message().chat().id(), update.callbackQuery().message().messageId()-2, "больше не гей");
                    bot.execute(editMessageText);
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }
    public static SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Я гей");
        InlineKeyboardButton button2 = new InlineKeyboardButton("И мне повезло");
        InlineKeyboardButton button3 = new InlineKeyboardButton(" gay am i ");
        button1.callbackData("pressed");
        button2.callbackData("pressed");
        button3.callbackData("pressed");
        inlineKeyboardMarkup.addRow(button1,button2);
        inlineKeyboardMarkup.addRow(button3);
        return new SendMessage(chatId, "Ты гей?").replyMarkup(inlineKeyboardMarkup);
    }
}
