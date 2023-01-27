package bot.Player;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

public class Registration {
    public static void playerRegistration(String playerName, long playerId, long chatId) {

        RedissonClient redisson = Redisson.create();
        RMap<String, Player> playerBaseMap = redisson.getMap("playerBaseMap");

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
