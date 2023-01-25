package bot.Player;

public class Player {

    long playerId;
    String playerName;
    long chatId;
    int basicBet;

    int balance;

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



