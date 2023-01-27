package bot.Player;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import static org.junit.jupiter.api.Assertions.*;
class RegistrationTest {

    @Test
    void playerRegistration() {
        RedissonClient redisson = Redisson.create();
        RMap<String, Player> testBaseMap = redisson.getMap("testBaseMap");

        Player testPlayer = new Player();
        testPlayer.setPlayerName("Felix");
        testPlayer.setPlayerId(1L);
        testPlayer.setChatId(10L);
        testPlayer.setBasicBet(11);
        testPlayer.setBalance(111);
        testBaseMap.put(String.valueOf(1L), testPlayer);

        assertEquals(testBaseMap.get(String.valueOf(1L)).getPlayerName(), "Felix");
        assertEquals(testBaseMap.get(String.valueOf(1L)).getPlayerId(), 1L);
        assertEquals(testBaseMap.get(String.valueOf(1L)).getBasicBet(), 11);
        assertEquals(testBaseMap.get(String.valueOf(1L)).getBalance(), 111);
    }
}