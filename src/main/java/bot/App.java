package bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
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

                } else if (update.callbackQuery().data().equals("pressed")){
                    bot.execute(new SendMessage(update.callbackQuery().message().chat().id(), update.callbackQuery().from().username()+" сказал, что он гей"));

                } else if (update.callbackQuery().data().equals("edit")){
                    EditMessageText editMessageText = new EditMessageText(update.callbackQuery().message().chat().id(), update.callbackQuery().message().messageId()+1, "больше не гей");
                    bot.execute(editMessageText);
                } else if (update.callbackQuery().data().equals("alert") && update.callbackQuery().from().id() == 430823029){
                    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(update.callbackQuery().id());
                    bot.execute(answerCallbackQuery.text("fucking alert for Felix Nani").showAlert(true));
                } else if (update.callbackQuery().data().equals("alert")){
                    System.out.println(update.callbackQuery().from().id());
                    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(update.callbackQuery().id());
                    bot.execute(answerCallbackQuery.text("fucking alert for Merry Mike").showAlert(true));
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }
    public static SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Я гей");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Показать алерт");
        InlineKeyboardButton button3 = new InlineKeyboardButton("edit");
        button1.callbackData("pressed");
        button2.callbackData("alert");
        button3.callbackData("edit");
        inlineKeyboardMarkup.addRow(button1,button2);
        inlineKeyboardMarkup.addRow(button3);
        return new SendMessage(chatId, "Ты гей?").replyMarkup(inlineKeyboardMarkup);
    }
}
