package housie.game;

import housie.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GameStateTest {
    private GameState gameState;
    private Player player1;
    private Player player2;
    private Player player3;

    @Before
    public void setUp() {
        this.gameState = new GameState();
        this.player1 = mock(Player.class);
        this.player2 = mock(Player.class);
        this.player3 = mock(Player.class);
    }

    @After
    public void tearDown() {
        this.gameState = null;
        this.player1 = null;
        this.player2 = null;
        this.player3 = null;
    }

    @Test
    public void initialize() {
        assertEquals(0, gameState.getState().size());
        assertFalse(gameState.isGameOver());
        // verify no win conditions
        assertNull(gameState.getWinner(WinCondition.FIRST_FIVE));
        assertNull(gameState.getWinner(WinCondition.ALL_NUMBERS));
        assertNull(gameState.getWinner(WinCondition.TOP_ROW));
        assertFalse(gameState.hasWinCondition(WinCondition.FIRST_FIVE));
        assertFalse(gameState.hasWinCondition(WinCondition.ALL_NUMBERS));
        assertFalse(gameState.hasWinCondition(WinCondition.TOP_ROW));
    }

    @Test
    public void gameOverNotReachedWhenConditionAreNotAllClaimed() {
        gameState.setWinCondition(WinCondition.FIRST_FIVE, player2);
        assertFalse(gameState.isGameOver());
        gameState.setWinCondition(WinCondition.TOP_ROW, player1);
        assertFalse(gameState.isGameOver());
    }

    @Test
    public void gameOverReached() {
        gameState.setWinCondition(WinCondition.TOP_ROW, player1);
        gameState.setWinCondition(WinCondition.FIRST_FIVE, player2);
        gameState.setWinCondition(WinCondition.ALL_NUMBERS, player3);
        assertTrue(gameState.isGameOver());
    }

    @Test
    public void winConditionNotOverwritten() {
        gameState.setWinCondition(WinCondition.FIRST_FIVE, player1);
        gameState.setWinCondition(WinCondition.FIRST_FIVE, player2);
        assertEquals(player1, gameState.getWinner(WinCondition.FIRST_FIVE));
    }

    @Test
    public void setGameState() {
        gameState.setGameOver(true);
        assertTrue(gameState.isGameOver());
    }
}
