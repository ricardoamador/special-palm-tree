package housie.caller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumberGeneratorTest {
    
    private NumberGenerator numberGenerator;
    private static final int CAPACITY = 10;

    @Before
    public void setUp() {
        numberGenerator = new NumberGenerator(CAPACITY);
    }

    @After
    public void tearDown() {
        numberGenerator = null;
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInvalidRange() {
        numberGenerator = new NumberGenerator(-1);
    }

    @Test
    public void testInitialize() { 
        for (int i = 0; i < CAPACITY; i++) {
            int nextInt = numberGenerator.getNextNumber();
            assertTrue(nextInt <= CAPACITY);
        }
    }

    @Test
    public void testReset() {
        for (int i = 0; i < CAPACITY; i++) {
            int nextInt = numberGenerator.getNextNumber();
            assertTrue(nextInt <= CAPACITY);
        }

        numberGenerator.reset();

        for (int i = 0; i < CAPACITY; i++) {
            int nextInt = numberGenerator.getNextNumber();
            assertTrue(nextInt <= CAPACITY);
        }
    }

    @Test
    public void testGetBeyondCapacity() {
        for (int i = 0; i < CAPACITY; i++) {
            int nextInt = numberGenerator.getNextNumber();
            assertTrue(nextInt <= CAPACITY);
        }

        int beyond = numberGenerator.getNextNumber();
        assertEquals(-1, beyond);
    }
}
