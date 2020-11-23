package housie.player;

import housie.game.GameSettings;
import housie.game.GameState;
import housie.game.WinCondition;
import housie.ticketing.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * The Player participates in the game by responding to numbers provided by the Caller via the Game,
 * and managing his ticket and claiming win conditions in the GameState. The Players also keeps track
 * of the called numbers via a boolean array for fast lookup.
 */
public class Player implements Observer, Comparable<Player> {

    private final String name;

    private final Ticket ticket;

    private final GameState gameState;

    private final boolean[] numbersCalled;

    /**
     * Create a new Player.
     * @param name simple string name for this player.
     * @param newTicket the ticket dealt to this player.
     * @param newGameState the state object for the Game this player is participating in.
     * @param gameSettings the settings object for the Game this player is participating in.
     */
    public Player(String name, Ticket newTicket, GameState newGameState, GameSettings gameSettings) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        } 

        if (newTicket == null) {
            throw new IllegalArgumentException("Game ticket cannot be null or empty.");
        }

        if (newGameState == null) {
            throw new IllegalArgumentException("GameState cannot be null.");
        }

        if (gameSettings == null) {
            throw new IllegalArgumentException("GameSettings cannot be null.");
        }

        this.name = name;
        this.ticket = newTicket;
        this.gameState = newGameState;
        numbersCalled = new boolean[gameSettings.getNumberRange() + 1];
        System.out.println("\nTicket for player " + name);
        ticket.printTicket();
        System.out.println("\n");
    }

    /**
     * @return the name of this Player.
     */
    public String getName() {
        return name;
    }

    /**
     * Update this player with a newly called number.
     * @param number the number to use.
     * @return any win conditions this player is claiming.
     */
    @Override
    public List<WinCondition> update(Integer number) {
        numbersCalled[number] = true;
        List<WinCondition> winsThisTurn = new ArrayList<>(3);

        if (!gameState.hasWinCondition(WinCondition.FIRST_FIVE)) {
            if (ticket.hasFirstFive(numbersCalled)) {
                gameState.setWinCondition(WinCondition.FIRST_FIVE, this);
                winsThisTurn.add(WinCondition.FIRST_FIVE);
            }
        }

        if (!gameState.hasWinCondition(WinCondition.TOP_ROW)) {
            if (ticket.hasTopLine(numbersCalled)) {
                gameState.setWinCondition(WinCondition.TOP_ROW, this);
                winsThisTurn.add(WinCondition.TOP_ROW);
            }
        }

        if (!gameState.hasWinCondition(WinCondition.ALL_NUMBERS)) {
            if (ticket.hasFullHouse(numbersCalled)) {
                gameState.setWinCondition(WinCondition.ALL_NUMBERS, this);
                winsThisTurn.add(WinCondition.ALL_NUMBERS);
            }
        }

        return winsThisTurn;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int compareTo(Player o) {
        return this.name.compareTo(o.name);
    }
}
