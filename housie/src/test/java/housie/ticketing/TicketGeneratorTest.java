package housie.ticketing;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import housie.game.GameSettings;

public class TicketGeneratorTest {
    
    private List<Integer> numbers;

    private TicketGenerator ticketGenerator;

    private GameSettings gameSettings;

    @Before
    public void setUp() {
        this.gameSettings = new GameSettings();
        this.ticketGenerator = new TicketGenerator(gameSettings);
    }

    @After
    public void tearDown() {
        this.numbers = null;
        this.ticketGenerator = null;
    }

    @Test
    public void ticketCreateInitialize() {
        Ticket ticket = ticketGenerator.generateTicket();
        int expectedNumbers = 5;
        int foundNumbers = 0;

        List<List<Integer>> ticketGrid = ticket.getNumbers();

        for (List<Integer> integers : ticketGrid) {
            for (Integer integer : integers) {
                if (integer != 0) {
                    foundNumbers++;
                }
            }
            assertEquals(expectedNumbers, foundNumbers);
            foundNumbers = 0;
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void illegalGameSettingsArgument() {
        ticketGenerator = new TicketGenerator(null);
    }

     @Test
     public void moreNumberPerRowThanCols() {
         gameSettings = new GameSettings(90, 3, 3, 10, 11);
         ticketGenerator = new TicketGenerator(gameSettings);
         Ticket ticket = ticketGenerator.generateTicket();

         int expectedNumbers = 10;
         int foundNumbers = 0;

         List<List<Integer>> ticketGrid = ticket.getNumbers();

         for (List<Integer> integers : ticketGrid) {
             for (Integer integer : integers) {
                 if (integer != 0) {
                     foundNumbers++;
                 }
             }
             assertEquals(expectedNumbers, foundNumbers);
             foundNumbers = 0;
         }
     }

     @Test
     public void singleRowSingleColTicket() {
         gameSettings = new GameSettings(90, 3, 1, 1, 11);
         ticketGenerator = new TicketGenerator(gameSettings);
         Ticket ticket = ticketGenerator.generateTicket();

         int expectedNumbers = 1;
         int foundNumbers = 0;

         List<List<Integer>> ticketGrid = ticket.getNumbers();

         for (List<Integer> integers : ticketGrid) {
             for (Integer integer : integers) {
                 if (integer != 0) {
                     foundNumbers++;
                 }
             }
             assertEquals(expectedNumbers, foundNumbers);
             foundNumbers = 0;
         }
     }
}
