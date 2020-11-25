package housie.game;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The InputCollector is responsible for interacting with the user and collecting input
 * to setup the game and to further progress.
 */
public class InputCollector {

    private static InputCollector instance = null;

    private static final Object lock = new Object();

    private Scanner scanner;

    /**
     * @return the InputCollector instance and instantiate it if it is currently null.
     */
    public static InputCollector getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new InputCollector();
                }
            }
        }
        return instance;
    }

    /**
     * Create a new InputCollector and instantiate the Scanner for collecting input.
     */
    protected InputCollector() {
        this.scanner = new Scanner(System.in);
    }

    private static final Pattern pattern = Pattern.compile("(\\d{1,})\\s*[xX]\\s*(\\d{1,})");

    /**
     * Collect the main input from the user at the start of the Game. The Input collector packages
     * the user input up into a GameSettings object used to create the Game.
     * The user can enter 'Q' at any time to quite the game.
     * @return the newly created GameSettings.
     */
    public GameSettings collectUserInput() {
        try {

            int numberRange = processIntegerInput("Enter the number range (1-n): ",  GameSettings.NUMBER_RANGE_DEF);

            int numberOfPlayers = processIntegerInput("Enter number of players playing the game: ", GameSettings.NUMBER_OF_PLAYERS_DEF);

            Pair pair = processPatternInput("Enter ticket size (rows x cols): ", GameSettings.ROWS_IN_TICKET_DEF, GameSettings.COLS_IN_TICKET_DEF);
            int ticketRows = pair.getX();
            int ticketCols = pair.getY();

            int numbersPerRow = processIntegerInput("Enter numbers per row: ", GameSettings.NUMBERS_PER_ROW_DEF);
            return new GameSettings(numberRange, numberOfPlayers, ticketRows, ticketCols, numbersPerRow);
        } catch (NoSuchElementException ex) {
            System.out.println("Incorrect input supplied, starting the game with defaults.");
            return new GameSettings();
        }
    }

    /**
     * Interface to allow the user to progress the game. The user must enter 'N' to get the next
     * number from the caller. User can also enter 'Q' to quite at any time.
     * @param message the message we want to display to the user for input.
     * @param defaultValue the defalt value if the user enters incorrect input.
     * @return the integer value parsed from the scanner.
     */
    protected int processIntegerInput(String message, int defaultValue) {
        String line;
        int value;

        System.out.print(message);
        line = scanner.nextLine();
        line.trim();
        checkForQuit(line);

        try {
            value = Integer.parseInt(line);
            if (value < 1) {
                System.out.println("\t\tNumber out of range using default value " + defaultValue);
                value = defaultValue;
            }
        } catch (NumberFormatException ex) {
            System.out.println("\t\tNo parsable number found, using default value " + defaultValue);
            value = defaultValue;
        }

        return value;
    }

    /**
     * Process the input for the rows and columns provided. This input is provided in the form
     * aXb or axb.
     * @param message the message to display to the user for input.
     * @return a Pair representing the rows and column input collected from the user.
     */
    @SuppressWarnings("SameParameterValue")
    protected Pair processPatternInput(String message, int rowDef, int colDef) {
        String line;
        Pair pair = new Pair(rowDef, colDef);

        System.out.print(message);
        line = scanner.nextLine();
        line.trim();
        checkForQuit(line);

        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            try {
                int ticketRows = Integer.parseInt(matcher.group(1));
                if (ticketRows < 1) {
                    System.out.println("\t\tNumber out of range. Using default value " + rowDef);
                    ticketRows = rowDef;
                }
                int ticketCols = Integer.parseInt(matcher.group(2));
                if (ticketCols < 1) {
                    System.out.println("\t\tNumber out of range. Using default value " + colDef);
                    ticketCols = colDef;
                }
                pair = new Pair(ticketRows, ticketCols);
            } catch (NumberFormatException ex) {
                // do nothing since the default is already set.
                System.out.println("No parsable input found using default values " + rowDef + " x " + colDef);
            }
        } else {
            System.out.println("\t\tNo parsable input found using default values " + rowDef + " x " + colDef);
        }

        return pair;
    }

    /**
     * A simple class to hold two integer values.
     */
    static class Pair {
        private final int x;
        private final int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    /**
     * Prompt the user for the next number in the Game.
     */
    public void nextNumberInput() {
        boolean parsedSuccessfully = false;

        do {
            System.out.print("Press 'N' to generate next number. ");
            try {
                String line = scanner.nextLine();
                line.trim();
                checkForQuit(line);
                if (line.toLowerCase().equals("n")) {
                    parsedSuccessfully = true;
                }
            } catch (NoSuchElementException ex) {
                // do nothing since parsed flag is defaulted to false
            }
        } while (!parsedSuccessfully);
    }

    /**
     * We need to check each input request for 'q' in case the user wants to quit the game.
     * @param line the line of user input collected by the scanner.
     */
    private void checkForQuit(String line) {
        if (line.toLowerCase().equals("q")) {
            System.exit(0);
        }
    }

    void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
