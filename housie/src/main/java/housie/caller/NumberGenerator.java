package housie.caller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The NumberGenerator class generates a pseudo random number. It keeps a range of numbers then
 * shuffles the numbers to guarantee each number is seen once and that time is not wasted with
 * collisions on numbers generated.
 */
public class NumberGenerator {

    /**
     * The list of numbers the NumberGenerator will draw from.
     */
    private List<Integer> numbers;

    /**
     * A pointer to the next number to call. Do this instead of destroying the
     * number list.
     */
    private int pointer;

    /**
     * The max number to call. Dictates the range of numbers to pull from.
     */
    private final int range;

    /**
     * Construct a new NumberGenerator with the specified range.
     * @param range the maximum value in the range of numbers. The NumberGenerator
     *              calls from the range 1 to n (range).
     * @throws IllegalArgumentException if the range is less than 1.
     */
    public NumberGenerator(int range) {
        if (range < 1) {
            throw new IllegalArgumentException("Invalid range. Range is [1,n]");
        }
        
        this.range = range;
        this.pointer = 0;
        initializeNumberRange();
    }

    /**
     * Initialize the list of numbers we will draw from. We create the list then use
     * the Collections API to shuffle the numbers into a random order.
     */
    public void initializeNumberRange() {
        numbers = new ArrayList<>(range);
        
        for (int i = 0; i < range; i++) {
            numbers.add(i, i + 1);
        }
        
        Collections.shuffle(numbers);
    }

    /**
     * @return the next number in the list to the caller.
     */
    public int getNextNumber() {
        if (pointer >= range) {
            return -1;
        }
        return numbers.get(pointer++);
    }

    /**
     * Reset the Caller with a new List of numbers to call from using the currently stored
     * range value.
     */
    public void reset() {
        numbers = null;
        pointer = 0;
        initializeNumberRange();
    }
}
