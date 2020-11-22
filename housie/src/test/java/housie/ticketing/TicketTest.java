package housie.ticketing;

import housie.game.GameSettings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TicketTest {

    private GameSettings gameSettings;
    private TicketGenerator ticketGenerator;
    private Ticket ticket;
    private List<Integer> numbersOnTicket;
    private boolean[] numbersCalled;

    @Before
    public void setUp() {
        this.gameSettings = new GameSettings();
        this.ticketGenerator = new TicketGenerator(gameSettings);
        this.ticket = ticketGenerator.generateTicket();
        this.numbersOnTicket = new LinkedList<>();
        getNumbersOnTicket();
        this.numbersCalled = new boolean[gameSettings.getNumberRange() + 1];
    }

    @After
    public void tearDown() {
        this.gameSettings = null;
        this.ticketGenerator = null;
        this.ticket = null;
        this.numbersOnTicket = null;
    }

    private void getNumbersOnTicket() {
        List<List<Integer>> ticketGrid = ticket.getNumbers();

        for (List<Integer> integers : ticketGrid) {
            for (Integer integer : integers) {
                if (integer != 0) {
                    numbersOnTicket.add(integer);
                }
            }
        }
    }

    @Test
    public void firstFiveNotWon() {
        assertFalse(ticket.hasFirstFive(numbersCalled));
    }

    @Test
    public void topLineNotWon() {
        assertFalse(ticket.hasTopLine(numbersCalled));
    }

    @Test
    public void fullHouseNotWon() {
        assertFalse(ticket.hasFullHouse(numbersCalled));
    }

    @Test
    public void firstFiveWin() {
        Collections.shuffle(numbersOnTicket);

        for (int i = 0; i < 5; i++) {
            numbersCalled[numbersOnTicket.get(i)] = true;
        }

        assertTrue(ticket.hasFirstFive(numbersCalled));
    }

    @Test
    public void topLineWin() {
        for (int i = 0; i < 5; i++) {
            numbersCalled[numbersOnTicket.get(i)] = true;
        }

        assertTrue(ticket.hasTopLine(numbersCalled));
    }

    @Test
    public void fullHouseWin() {
        for (Integer integer : numbersOnTicket) {
            numbersCalled[integer] = true;
        }

        assertTrue(ticket.hasFullHouse(numbersCalled));
    }

    @Test
    public void firstFiveAndTopLine() {
        for (int i = 0; i < 5; i++) {
            numbersCalled[numbersOnTicket.get(i)] = true;
        }

        assertTrue(ticket.hasTopLine(numbersCalled));
        assertTrue(ticket.hasFirstFive(numbersCalled));
    }

    // shameless code coverage test
    @Test
    public void printTicket() {
        ticket.printTicket();
    }
}
