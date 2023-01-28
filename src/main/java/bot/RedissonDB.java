package bot;

import bot.Player.Player;
import com.pengrad.telegrambot.request.SendMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static bot.BotController.playerDBMap;
import static bot.Chat.Messages.sendMessage;

public class RedissonDB {

    //:TODO create new method with config for redissonDB

    public static void accountRegistration(String playerName, long playerId, long chatId) {

        Player newPlayer = new Player();
        newPlayer.setPlayerName(playerName);
        newPlayer.setPlayerId(playerId);
        newPlayer.setBasicBet(1);
        newPlayer.setBalance(0);
        String sourceDate = "2022-09-25";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date myDate = format.parse(sourceDate);
            newPlayer.setLastTimeBonus(myDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        playerDBMap.put(String.valueOf(playerId), newPlayer);

        System.out.println(playerName + " registered");

    }

    public static void changePlayersBalance(long playerId, int extra, int messageId, long chatId) {
        Player currentPlayer = playerDBMap.get(String.valueOf(playerId));
        Date currentDate = new Date();
        currentPlayer.setLastTimeBonus(currentDate);
        currentPlayer.setBalance(currentPlayer.getBalance() + extra);
        playerDBMap.put(String.valueOf(playerId), currentPlayer);
        SendMessage balance = new SendMessage(chatId, "Баланс " + playerDBMap.get(String.valueOf(playerId)).getBalance())
                .replyToMessageId(messageId);
        sendMessage(balance);
    }

}
