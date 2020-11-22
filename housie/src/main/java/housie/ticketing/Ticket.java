package housie.ticketing;

import java.util.List;

/**
 * Ticket abstraction to represent a ticket grid.
 */
public class Ticket {

    private final List<List<Integer>> ticketGrid;

    /**
     * Create a new ticket.
     * @param newTicketGrid the new TicketGrid to store.
     */
    public Ticket(List<List<Integer>> newTicketGrid) {
        this.ticketGrid = newTicketGrid;
    }

    /**
     * @return the ticket grid.
     */
    List<List<Integer>> getNumbers() {
        return ticketGrid;
    }

    /**
     * Check this ticket for the first five winning condition.
     * @param numbersCalled the numbers that have already been called.
     * @return true if this ticket contains the first five numbers winning condition, false otherwise.
     */
    public boolean hasFirstFive(boolean[] numbersCalled) {
        int numToWinFirstFive = 5;
        int winningNumbers = 0;
        for (List<Integer> integers : ticketGrid) {
            for (Integer integer : integers) {
                if (integer != 0 && numbersCalled[integer]) {
                    winningNumbers++;
                }
                if (winningNumbers == numToWinFirstFive) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check this ticket for the top line winning condition.
     * @param numbersCalled the numbers that have already been called.
     * @return true if this ticket contains the top line winning condition, false otherwise.
     */
    public boolean hasTopLine(boolean[] numbersCalled) {
        for (Integer integer : ticketGrid.get(0)) {
            if (integer != 0 && !numbersCalled[integer]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check this ticket for the full house winning condition.
     * @param numbersCalled the numbers that have already been called.
     * @return true if this ticket contains the full house winning condition, false otherwise.
     */
    public boolean hasFullHouse(boolean[] numbersCalled) {
        for (List<Integer> integers : ticketGrid) {
            for (Integer integer : integers) {
                if (integer != 0 && !numbersCalled[integer]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Display the ticket grid stored.
     */
    public void printTicket() {
        for (List<Integer> integers : ticketGrid) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
