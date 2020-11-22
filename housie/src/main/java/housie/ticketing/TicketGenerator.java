package housie.ticketing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import housie.game.GameSettings;

public class TicketGenerator {

    private final GameSettings gameSettings;
    private List<Integer> numbers;

    public TicketGenerator(GameSettings gameSettings) {
        if (gameSettings == null) {
            throw new IllegalArgumentException("GameSettings must not be null");
        }

        this.gameSettings = gameSettings;
        generateUsableNumbers(gameSettings.getNumberRange());
    }

    private void generateUsableNumbers(int range) {
        this.numbers = new ArrayList<>(range);
        for (int i = 0; i < range; i++) {
            numbers.add(i + 1);
        }
    }

    public Ticket generateTicket() {
        // shuffle each time to randomize the numbers a ticket has a little more
        Collections.shuffle(numbers);
        Queue<Integer> usableNumbers = new LinkedList<>(numbers);

        List<List<Integer>> ticket = new LinkedList<>();

        for (int r = 0; r < gameSettings.getTicketRows(); r++) {
            List<Integer> newList = new ArrayList<>(gameSettings.getTicketColumns());
            for (int c = 0; c < gameSettings.getTicketColumns(); c++) {
                if (c < gameSettings.getNumbersPerRow() && !numbers.isEmpty()) {
                    newList.add(usableNumbers.poll());
                } else { 
                    newList.add(0);
                }
            }
            Collections.shuffle(newList);
            ticket.add(newList);
        }

        return new Ticket(ticket);
    }
}
