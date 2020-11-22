package housie.player;

import housie.game.WinCondition;

import java.util.List;

/**
 * Simple observer interface to allow the Game to communicate to the Players.
 */
public interface Observer {
    /**
     * Provide a new number to the Observer.
     * @param number the number to use.
     * @return any win condition claimed this update.
     */
    List<WinCondition> update(Integer number);
}
