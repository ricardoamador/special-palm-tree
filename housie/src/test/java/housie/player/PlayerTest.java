package housie.player;

import housie.game.GameSettings;
import housie.game.GameState;
import housie.game.WinCondition;
import housie.ticketing.Ticket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PlayerTest {

    private Ticket ticket;
    private GameState gameState;
    private GameSettings gameSettings;

    @Before
    public void setUp() {
        this.ticket = mock(Ticket.class);
        this.gameSettings = mock(GameSettings.class);
        this.gameState = new GameState();
    }

    @After
    public void tearDown() {
        this.ticket = null;
        this.gameSettings = null;
        this.gameState = null;
    }

    @Test (expected = IllegalArgumentException.class)
    public void illegalPlayerNameArgument() {
        new Player("", ticket, gameState, gameSettings);
    }

    @Test (expected = IllegalArgumentException.class)
    public void illegalTicketArgument() {
        new Player("Player1", null, gameState, gameSettings);
    }

    @Test (expected = IllegalArgumentException.class)
    public void illegalGameStateArgument() {
        new Player("Player1", ticket, null, gameSettings);
    }

    @Test (expected = IllegalArgumentException.class)
    public void illegalGameSettingsArgument() {
        new Player("Player1", ticket, gameState, null);
    }

    @Test
    public void playerWinsFirstFive() {
        when(gameSettings.getNumberRange()).thenReturn(90);
        when(ticket.hasFirstFive(any())).thenReturn(true);
        when(ticket.hasFullHouse(any())).thenReturn(false);
        when(ticket.hasTopLine(any())).thenReturn(false);
        String playerName = "Player1";
        Player p1 = new Player(playerName, ticket, gameState, gameSettings);
        p1.update(45);
        p1.update(46);
        p1.update(47);
        p1.update(48);
        p1.update(49);
        assertTrue(gameState.hasWinCondition(WinCondition.FIRST_FIVE));
        Player winner = gameState.getWinner(WinCondition.FIRST_FIVE);
        assertEquals(p1, winner);
        assertEquals(playerName, winner.getName());
    }

    @Test
    public void playerWinsTopLine() {
        when(gameSettings.getNumberRange()).thenReturn(90);
        when(ticket.hasFirstFive(any())).thenReturn(false);
        when(ticket.hasFullHouse(any())).thenReturn(false);
        when(ticket.hasTopLine(any())).thenReturn(true);
        String playerName = "Player1";
        Player p1 = new Player(playerName, ticket, gameState, gameSettings);
        p1.update(45);
        assertTrue(gameState.hasWinCondition(WinCondition.TOP_ROW));
        Player winner = gameState.getWinner(WinCondition.TOP_ROW);
        assertEquals(p1, winner);
        assertEquals(playerName, winner.getName());
    }

    @Test
    public void playerWinsFullHouse() {
        when(gameSettings.getNumberRange()).thenReturn(90);
        when(ticket.hasFirstFive(any())).thenReturn(false);
        when(ticket.hasFullHouse(any())).thenReturn(true);
        when(ticket.hasTopLine(any())).thenReturn(false);
        String playerName = "Player1";
        Player p1 = new Player(playerName, ticket, gameState, gameSettings);
        p1.update(45);
        assertTrue(gameState.hasWinCondition(WinCondition.ALL_NUMBERS));
        Player winner = gameState.getWinner(WinCondition.ALL_NUMBERS);
        assertEquals(p1, winner);
        assertEquals(playerName, winner.getName());
    }

    @Test
    public void verifyCompareTo() {
        when(gameSettings.getNumberRange()).thenReturn(90);
        Player player1 = new Player("Player1", ticket, gameState, gameSettings);
        Player player2 = new Player("Player2", ticket, gameState, gameSettings);
        Player player3 = new Player("Player1", ticket, gameState, gameSettings);
        assertEquals(-1, player1.compareTo(player2));
        assertEquals(0, player1.compareTo(player3));
        assertEquals(1, player2.compareTo(player1));
    }
}
