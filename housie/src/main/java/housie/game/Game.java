package housie.game;

import housie.caller.Caller;
import housie.player.Player;
import housie.ticketing.TicketGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The main entry point for the Application. This Object keeps track of all the parts of
 * the application. It maintains a list of players and runs the game to completion.
 *
 * Rules of the Game:
 * 1. Each player will have a ticket with a given amount of numbers.
 * 2. Numbers on a single ticket are unique.
 * 3. The Caller calls the numbers randomly generated number queue one at a time.
 * 4. A number can be called only once.
 * 5. If a number called by the Caller appears on the ticket it is marked.
 * 6. The winner is the first person to mark off all their numbers of a winning combination.
 *    Winning combinations are:
 *          Top Line - The numbers on the top line of the ticket are all marked.
 *          First Five - The player who marks off the first five numbers matched.
 *          Full House - This condition ends the game. All numbers on the ticket are marked.
 * 7. If a particular winning combination has been claimed it cannot be claimed again.
 */
public class Game {

    private final GameState gameState;

    private final GameSettings gameSettings;

    private final TicketGenerator ticketGenerator;

    private List<Player> players;

    private final Caller caller;

    private final InputCollector inputCollector;

    /**
     * Create a new Game of housie with the user provided settings.
     * @param gameSettings the settings as collected from the user.
     */
    public Game(GameSettings gameSettings) {
        this.gameState = new GameState();
        this.gameSettings = gameSettings;
        this.ticketGenerator = new TicketGenerator(gameSettings);
        this.caller = new Caller(gameSettings);
        createPlayers();
        inputCollector = InputCollector.getInstance();
    }

    /**
     * Create the Players for the Game and generate the tickets for each of them.
     */
    private void createPlayers() {
        int numPlayers = gameSettings.getNumberOfPlayers();
        players = new ArrayList<>(numPlayers);
        for (int i = 0; i < numPlayers; i++) {
            String playerName = "Player" + (i + 1);
            players.add(new Player(
                    playerName,
                    ticketGenerator.generateTicket(),
                    gameState,
                    gameSettings));
        }

        System.out.println("*** Tickets created successfully ***");
    }

    /**
     * This method lets each of the players know the next number in the Game and determines
     * if a game combination has been won and if the game is over. This method relies on the
     * user to progress the Game.
     *
     * In order to simulate a game we shuffle the players before updating them otherwords in
     * principle players would always respond in the same order which does not happen in a
     * real game.
     */
    private void updatePlayers() {
        Collections.shuffle(players);

        inputCollector.nextNumberInput();

        int nextNumber = caller.callNumber();
        if (nextNumber == -1) {
            // no more numbers so we end the housie.game
            gameState.setGameOver(true);
            System.out.println("\n ***** Game Over *****");
            return;
        }

        System.out.println("Next number is: " + nextNumber);
        for (Player p : players) {
            List<WinCondition> winsForPlayer = p.update(nextNumber);
            if (!winsForPlayer.isEmpty()) {
                for (WinCondition winCondition : winsForPlayer) {
                    System.out.println(
                            "\nWe have a winner: " +
                            p.getName() +
                            " has won '" +
                            winCondition.getWinMessage() +
                            "' winning combination!\n");
                }
            }
        }
    }

    /**
     * Show the results of the Game and which player won which combinations. A player can win
     * up to three combinations.
     */
    private void showResults() {
        System.out.println("\n=============================================================");
        System.out.println("Summary:");
        Collections.sort(players);
        for (Player p : players) {
            StringBuilder playerWins = new StringBuilder();
            for (WinCondition w : WinCondition.values()) {
                if (gameState.hasWinCondition(w) && gameState.getWinner(w).equals(p)) {
                    if (playerWins.length() == 0) {
                        playerWins.append(w.getWinMessage());
                    } else {
                        playerWins.append(" and ").append(w.getWinMessage());
                    }
                }
            }
            if (playerWins.length() == 0) {
                playerWins.append("Nothing");
            }
            System.out.println(p.getName() + " : " + playerWins.toString());
        }
        System.out.println("=============================================================\n");
    }

    /**
     * Run the game for the players.
     */
    public void run() {
        while (!gameState.isGameOver()) {
            updatePlayers();
        }

        showResults();
    }

    /**
     * Start our application.
     * @param args not currently used.
     */
    public static void main(String[] args) {
        InputCollector inputCollector = InputCollector.getInstance();
        GameSettings gameSettings = inputCollector.collectUserInput();
        Game g = new Game(gameSettings);
        g.run();
    }
}
