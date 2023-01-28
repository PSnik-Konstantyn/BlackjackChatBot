package bot.Player;

import com.pengrad.telegrambot.request.SendMessage;

import java.util.Date;

import static bot.BotController.playerDBMap;
import static bot.Chat.Messages.sendMessage;
import static bot.RedissonDB.changePlayersBalance;


public class DailyBonus {
    public static void getDailyBonus(long playerId) {
        Player currentPlayer = playerDBMap.get(String.valueOf(playerId));
        Date lastTimePlusOneDay = DateUtil.addDays(currentPlayer.getLastTimeBonus(), 0);
        Date currentDate = new Date();

        if (lastTimePlusOneDay.getTime() < currentDate.getTime()) {
            changePlayersBalance(playerId, 10);
        } else {
            long chatId = currentPlayer.getChatId();
            SendMessage message = new SendMessage(chatId, "Ви вже отримували сьогодні бонус");
            sendMessage(message);
        }
    }

}
