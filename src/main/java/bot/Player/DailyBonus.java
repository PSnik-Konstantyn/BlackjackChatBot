package bot.Player;

import com.pengrad.telegrambot.request.SendMessage;

import java.util.Date;

import static bot.BotController.playerDBMap;
import static bot.Chat.Messages.sendMessage;
import static bot.RedissonDB.changePlayersBalance;


public class DailyBonus {
    public static void getDailyBonus(long playerId) {
        Player currentPlayer = playerDBMap.get(String.valueOf(playerId));
        Date lastTimePlusOneDay = DateUtil.addDays(currentPlayer.getLastTimeBonus(), 1);
        Date currentDate = new Date();
        System.out.println("last" + lastTimePlusOneDay);
        System.out.println("current" + currentDate);
        if (lastTimePlusOneDay.before(currentDate)) {
            changePlayersBalance(playerId, 10);
        } else {
            long chatId = currentPlayer.getChatId();
            SendMessage message = new SendMessage(chatId, "Ви вже отримували сьогодні бонус");
            sendMessage(message);
        }
    }
}
