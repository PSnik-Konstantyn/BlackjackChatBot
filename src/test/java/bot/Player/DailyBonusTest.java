package bot.Player;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DailyBonusTest {

    @Test
    void getDailyBonus() {
        String sourceDate = "2022-09-25";
        String knownDate = "2022-09-26";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date myDate = format.parse(sourceDate);
            Date testDate = format.parse(knownDate);
            myDate = DateUtil.addDays(myDate, 1);
            assertEquals(myDate, testDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}