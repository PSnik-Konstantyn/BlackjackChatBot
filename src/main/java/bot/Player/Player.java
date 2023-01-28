package bot.Player;


import kotlin.jvm.internal.SerializedIr;

import java.io.Serializable;
import java.util.Date;


public class Player implements Serializable {

    long playerId;
    String playerName;
    long chatId;
    int basicBet;

    int balance;
    Date lastTimeBonus;

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

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
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



