package bot.Player;

import bot.Player.Player;

public class Registration {
    public static void playerRegistration(String playerName, long playerId, long chatId) {

        Player newPlayer = new Player();
        newPlayer.setPlayerName(playerName);
        newPlayer.setPlayerId(playerId);
        newPlayer.setChatId(chatId);
        newPlayer.setBasicBet(1);
        newPlayer.setBalance(0);
        System.out.println(playerName + " registered");

    }
}
