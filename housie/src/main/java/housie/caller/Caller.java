package housie.caller;

import java.util.Set;
import java.util.TreeSet;

import housie.game.GameSettings;

/**
 * The Caller is responsible for communicating the new numbers to the Game object for Players to
 * consume. The Caller use the NumberGenerator to prepare and communicate the next number in the
 * game.
 */
public class Caller {
    /**
     * The number generator object used to get the next random number for the game.
     */
    private final NumberGenerator numberGenerator;

    /**
     * A set of numbers that have already been called in the Game.
     */
    private final Set<Integer> calledNumbers;

    /**
     * Construct a new Caller with the provided GameSettings.
     * @param gameSettings the settings for the current game. Caller uses the NumberRange
     *                     to track the amount of numbers expected in the game.
     * @throws IllegalArgumentException if the GameSettings passed in is null.
     */
    public Caller(GameSettings gameSettings) {
        if (gameSettings == null) {
            throw new IllegalArgumentException("GameSettings cannot be null."); 
        }

        this.numberGenerator = new NumberGenerator(gameSettings.getNumberRange());
        this.calledNumbers = new TreeSet<>();
    }

    /**
     * Communicate the next number to the Game (and the Players).
     * @return the next random number the players of the game will try to match. If the
     * called numbers has exceeded the number range chosen for the game this will return
     * -1.
     */
    public int callNumber() {
        int nextNumber = numberGenerator.getNextNumber();
        if (nextNumber != -1) {
            calledNumbers.add(nextNumber);
        }
        return nextNumber;
    }

    /**
     * @return the current set of called numbers.
     */
    public Set<Integer> getCalledNumbers() {
        return calledNumbers;
    }
}
