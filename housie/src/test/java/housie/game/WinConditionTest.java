package housie.game;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class WinConditionTest {

    List<String> winConditionMessages = Arrays.asList("Top Line", "Full House", "First Five");

    @Test
    public void verifyMessages() {
        for (WinCondition winCondition : WinCondition.values()) {
            assertTrue(winConditionMessages.contains(winCondition.getWinMessage()));
        }
    }
}
