package housie.game;

import java.util.HashMap;
import java.util.Map;

import housie.player.Player;

/**
 * The GameState is responsible for tracking the status of the game after each number. It holds
 * the winner of each winning combination and determines whether or not a game is currently over.
 */
public class GameState {

    private final Map<WinCondition, Player> state;

    private boolean gameOver;

    /**
     * Create a GameState tracking object.
     */
    public GameState() {
        this.state = new HashMap<>();
        this.gameOver = false;
    }

    /**
     * Set the winner of a win condition. Subsequent calls on the same WinCondition will
     * not update the map.
     * @param condition the winning condition to add to the map.
     * @param player the player who won with that condition.
     */
    public void setWinCondition(WinCondition condition, Player player) {
        state.putIfAbsent(condition, player);
        for (WinCondition w : WinCondition.values()) {
            if (!state.containsKey(w)) {
                return;
            }
        }
        this.gameOver = true;
    }

    /**
     * Check to see if a winning condition has been recorded.
     * @param condition the winning condition to check for.
     * @return true if the winning condition has been recorded, false if it has not.
     */
    public boolean hasWinCondition(WinCondition condition) {
        return state.containsKey(condition);
    }

    /**
     * Fetch the winner of the winning condition if it has been reported. It is important to
     * note that a winner for each condition will be recorded.
     * @param condition the winning condition for which we want the winner of.
     * @return the winner who claimed the winning condition.
     */
    public Player getWinner(WinCondition condition) {
        return state.get(condition);
    }

    /**
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * @param end set the game over condition.
     */
    public void setGameOver(boolean end) {
        gameOver = end;
    }

    /**
     * @return get the current winning conditions.
     */
    public Map<WinCondition, Player> getState() {
        return state;
    }
}
