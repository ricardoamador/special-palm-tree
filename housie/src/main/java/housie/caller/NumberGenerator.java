package housie.caller;

import java.util.*;

/**
 * The NumberGenerator class generates a pseudo random number. It keeps a range of numbers then
 * shuffles the numbers to guarantee each number is seen once and that time is not wasted with
 * collisions on numbers generated.
 */
public class NumberGenerator {

    /**
     * The list of numbers the NumberGenerator will draw from.
     */
    // TODO use a queue instead to remove the need for a pointer.
    private Queue<Integer> numbers;

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
        initializeNumberRange();
    }

    /**
     * Initialize the list of numbers we will draw from. We create the list then use
     * the Collections API to shuffle the numbers into a random order.
     */
    public void initializeNumberRange() {
        List<Integer> numberList = new ArrayList<>(range);
        
        for (int i = 0; i < range; i++) {
            numberList.add(i + 1);
        }
        
        Collections.shuffle(numberList);
        this.numbers = new LinkedList<>(numberList);
    }

    /**
     * @return the next number in the list to the caller.
     * @throws NoSuchElementException if the numbers queue is empty.
     */
    public int getNextNumber() throws NoSuchElementException {
        return numbers.remove();
    }

    /**
     * Reset the Caller with a new List of numbers to call from using the currently stored
     * range value.
     */
    public void reset() {
        initializeNumberRange();
    }
}
