package housie.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InputCollectorTest {

    private static final String SCANNER_FIELD = "scanner";
    private Field scannerField;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void processValidIntegerInput() {
        InputCollector inputCollector = InputCollector.getInstance();
        String line = "1\n";

        InputStream in = new ByteArrayInputStream(line.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);

        inputCollector.setScanner(scanner);
        int expected = 1;
        int foundInt = inputCollector.processIntegerInput("Message", 5);
        assertEquals(expected, foundInt);
    }

    @Test
    public void processInvalidIntegerInput() {
        InputCollector inputCollector = InputCollector.getInstance();
        String line = "r\n";

        InputStream in = new ByteArrayInputStream(line.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);

        inputCollector.setScanner(scanner);
        int expected = 5;
        int foundInt = inputCollector.processIntegerInput("Message", 5);
        assertEquals(expected, foundInt);
    }

    @Test
    public void processValidPatternInput() {
        InputCollector inputCollector = InputCollector.getInstance();
        String line = "4x8\n";

        InputStream in = new ByteArrayInputStream(line.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);

        inputCollector.setScanner(scanner);
        int expectedX = 4;
        int expectedY = 8;
        InputCollector.Pair pair = inputCollector.processPatternInput("Message", 3, 10);
        assertEquals(expectedX, pair.getX());
        assertEquals(expectedY, pair.getY());
    }

    @Test
    public void processInvalidPatternInput() {
        InputCollector inputCollector = InputCollector.getInstance();
        String line = "6, 20 \n";

        InputStream in = new ByteArrayInputStream(line.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);

        inputCollector.setScanner(scanner);
        int expectedX = 3;
        int expectedY = 10;
        InputCollector.Pair pair = inputCollector.processPatternInput("Message", 3, 10);
        assertEquals(expectedX, pair.getX());
        assertEquals(expectedY, pair.getY());
    }

    @Test
    public void processNextNumberInput() {
        InputCollector inputCollector = InputCollector.getInstance();
        String line = "N\n";

        InputStream in = new ByteArrayInputStream(line.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);

        inputCollector.setScanner(scanner);
        InputCollector inputCollectorSpy = spy(inputCollector);
        inputCollectorSpy.nextNumberInput();
        verify(inputCollectorSpy, times(1)).nextNumberInput();
    }

//    public void scannerAccess() throws Exception {
//        InputCollector inputCollector = InputCollector.getInstance();
//        scannerField = InputCollector.class.getDeclaredField(SCANNER_FIELD);
//        scannerField.setAccessible(true);
//        Scanner testScanner = (Scanner) scannerField.get(inputCollector);
//
//        scannerField.setAccessible(false);
//    }
}
