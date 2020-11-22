package housie.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameSettingsTest {

    @Test
    public void verifyDefaultSettings() {
        GameSettings gameSettings = new GameSettings();
        assertEquals(GameSettings.NUMBER_RANGE_DEF, gameSettings.getNumberRange());
        assertEquals(GameSettings.NUMBER_OF_PLAYERS_DEF, gameSettings.getNumberOfPlayers());
        assertEquals(GameSettings.ROWS_IN_TICKET_DEF, gameSettings.getTicketRows());
        assertEquals(GameSettings.COLS_IN_TICKET_DEF, gameSettings.getTicketColumns());
        assertEquals(GameSettings.NUMBERS_PER_ROW_DEF, gameSettings.getNumbersPerRow());
    }
}
