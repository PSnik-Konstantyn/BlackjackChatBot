package bot;

import bot.Player.Player;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

public class RedissonDB {
    static RedissonClient redisson = Redisson.create();
    static RMap<String, Player> playerDBMap = redisson.getMap("playerBaseMap");

    public static void accountRegistration(String playerName, long playerId, long chatId) {

        Player newPlayer = new Player();
        newPlayer.setPlayerName(playerName);
        newPlayer.setPlayerId(playerId);
        newPlayer.setChatId(chatId);
        newPlayer.setBasicBet(1);
        newPlayer.setBalance(0);
        playerDBMap.put(String.valueOf(playerId),newPlayer);

        System.out.println(playerName + " registered");

    }
}
