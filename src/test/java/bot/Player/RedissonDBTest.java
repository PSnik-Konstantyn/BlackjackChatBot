package bot.Player;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RedissonDBTest {

    @Test
    void accountRegistration() {
        RedissonClient redisson = Redisson.create();
        RMap<String, Player> testBaseMap = redisson.getMap("testBaseMap");

        Player testPlayer = new Player();
        testPlayer.setPlayerName("Felix");
        testPlayer.setPlayerId(1L);
        testPlayer.setBasicBet(11);
        testPlayer.setBalance(111);
        String sourceDate = "2022-09-25";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date myDate = format.parse(sourceDate);
            testPlayer.setLastTimeBonus(myDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        testBaseMap.put(String.valueOf(1L), testPlayer);

        assertEquals(testBaseMap.get(String.valueOf(1L)).getPlayerName(), "Felix");
        assertEquals(testBaseMap.get(String.valueOf(1L)).getPlayerId(), 1L);
        assertEquals(testBaseMap.get(String.valueOf(1L)).getBasicBet(), 11);
        assertEquals(testBaseMap.get(String.valueOf(1L)).getBalance(), 111);
        try {
            Date myDate = format.parse(sourceDate);
            assertEquals(testBaseMap.get(String.valueOf(1L)).getLastTimeBonus(), myDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}