package housie.caller;

import housie.game.GameSettings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallerTest {

    private GameSettings gameSettings;

    @Before
    public void setUp() {
        this.gameSettings = mock(GameSettings.class);
    }

    @After
    public void tearDown() {
        this.gameSettings = null;
    }

    @Test (expected = IllegalArgumentException.class)
    public void nullGameSettings() {
        gameSettings = null;
        new Caller(gameSettings);
    }

    @Test
    public void newCallerHasNoNumbers() {
        Caller caller = getNewCaller(5);
        assertTrue(caller.getCalledNumbers().isEmpty());
    }

    @Test
    public void callNumberWhenEmpty() {
        Caller caller = getNewCaller(10);
        assertNotEquals(-1, caller.callNumber());
    }

    @Test
    public void callUptoCapacity() {
        Caller caller = getNewCaller(5);
        for (int i = 0; i < 5; i++) {
            assertNotEquals(-1, caller.callNumber());
        }
    }

    @Test
    public void callBeyondCapacity() {
        Caller caller = getNewCaller(5);
        for (int i = 0; i < 5; i++) {
            assertNotEquals(-1, caller.callNumber());
        }
        assertEquals(-1, caller.callNumber());
    }

    private Caller getNewCaller(int numberOfNumbers) {
        when(gameSettings.getNumberRange()).thenReturn(numberOfNumbers);
        return new Caller(gameSettings);
    }
}
