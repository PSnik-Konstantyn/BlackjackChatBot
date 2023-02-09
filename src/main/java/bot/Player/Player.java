package bot.Player;


import java.io.Serializable;
import java.util.Date;


public class Player implements Serializable {

    private long playerId;
    private String playerName;
    private int basicBet;
    private int balance;
    private Date lastTimeBonus;

    public Date getLastTimeBonus() {
        return lastTimeBonus;
    }

    public void setLastTimeBonus(Date lastTimeBonus) {
        this.lastTimeBonus = lastTimeBonus;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getBasicBet() {
        return basicBet;
    }

    public void setBasicBet(int basicBet) {
        this.basicBet = basicBet;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


}



