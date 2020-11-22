package housie.game;

/**
 * GameSettings holds the user provided settings for the game.
 *
 */
public class GameSettings {

    static final int NUMBER_RANGE_DEF = 90;

    static final int NUMBER_OF_PLAYERS_DEF = 5;

    static final int ROWS_IN_TICKET_DEF = 3;

    static final int COLS_IN_TICKET_DEF = 10;

    static final int NUMBERS_PER_ROW_DEF = 5;

    private final int numberRange;

    private final int numberOfPlayers;

    private final int ticketRows;

    private final int ticketCols;

    private final int numbersPerRow;

    /**
     * Construct a new GameSettings with the default values.
     */
    public GameSettings() {
        this(NUMBER_RANGE_DEF, NUMBER_OF_PLAYERS_DEF, ROWS_IN_TICKET_DEF, COLS_IN_TICKET_DEF, NUMBERS_PER_ROW_DEF);
    }

    /**
     * Construct a new game with custom values.
     * @param numberRange the range of numbers to pull from.
     * @param numberOfPlayers the number of players in the game.
     * @param ticketRows the number of rows in a players ticket.
     * @param ticketCols the number of columns in a players ticket.
     * @param numbersPerRow the amount of numbers in each row on a ticket.
     * @throws IllegalArgumentException if the provided values are out of range.
     */
    public GameSettings(int numberRange, int numberOfPlayers, int ticketRows, int ticketCols, int numbersPerRow) {
        this.numberRange = numberRange;
        this.numberOfPlayers = numberOfPlayers;
        this.ticketRows = ticketRows;
        this.ticketCols = ticketCols;
        this.numbersPerRow = numbersPerRow;
    }

    @Override
    public String toString() {
        return "[GameContext: " + 
            "numberRange = " + numberRange +
            ", numberOfPlayers = " + numberOfPlayers +
            ", ticketRows = " + ticketRows +
            ", ticketCols = " + ticketCols +
            ", numbersPerRow = " + numbersPerRow + "]";
    }

    /**
     * @return the number range.
     */
    public int getNumberRange() {
        return numberRange;
    }

    /**
     * @return the number of players.
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * @return the rows on a ticket.
     */
    public int getTicketRows() {
        return ticketRows;
    }

    /**
     * @return the columns on a ticket.
     */
    public int getTicketColumns() {
        return ticketCols;
    }

    /**
     * @return the numbers per row on a ticket.
     */
    public int getNumbersPerRow() {
        return numbersPerRow;
    }
}
