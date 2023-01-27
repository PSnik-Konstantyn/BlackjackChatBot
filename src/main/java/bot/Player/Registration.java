package bot.Player;

import org.redisson.api.RMap;

public class Registration {
    public static void playerRegistration(String playerName, long playerId, long chatId, RMap<String, Player> playerBaseMap) {

        Player newPlayer = new Player();
        newPlayer.setPlayerName(playerName);
        newPlayer.setPlayerId(playerId);
        newPlayer.setChatId(chatId);
        newPlayer.setBasicBet(1);
        newPlayer.setBalance(0);
        playerBaseMap.put(String.valueOf(playerId),newPlayer);
        System.out.println(playerName + " registered");

    }
}
