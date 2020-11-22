package housie.game;

/**
 * Enumeration of the winning conditions that can be claimed in the Game.
 */
public enum WinCondition {
    TOP_ROW("Top Line"),
    FIRST_FIVE("First Five"),
    ALL_NUMBERS("Full House");

    private final String winMessage;

    WinCondition(String winMessage) {
        this.winMessage = winMessage;
    }

    /**
     * @return the win message associated with the win condition.
     */
    public String getWinMessage() {
        return this.winMessage;
    }
}
