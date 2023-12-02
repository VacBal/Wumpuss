package controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainControllerTest {

    private MainController mainController;

    @BeforeEach
    public void setUp() {
        mainController = new MainController();
    }

    @AfterEach
    public void tearDown() {
        mainController = null;
    }

    /**
     * Test of run method, of class MainController.
     */
    @Test
    public void testWrongUserInputType() {
        System.setIn(new ByteArrayInputStream("s\n3".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
            
        mainController.run();
            
        assertEquals(3, mainController.getSelectedItem());
        assertFalse(mainController.isGameRunning());
    }
    
    /**
     * Test of run method, of class MainController.
     */
    @Test
    public void testMainMenuGreaterInput() {
        System.setIn(new ByteArrayInputStream("4\n3".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
            
        mainController.run();
            
        assertEquals(3, mainController.getSelectedItem());
        assertFalse(mainController.isGameRunning());
    }
    
    /**
     * Test of run method, of class MainController.
     */
    @Test
    public void testMainMenuSmallerInput() {
        System.setIn(new ByteArrayInputStream("0\n3".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
            
        mainController.run();
            
        assertEquals(3, mainController.getSelectedItem());
        assertFalse(mainController.isGameRunning());
    }
    
    /**
     * Test of run method, of class MainController.
     */
    @Test
    public void testMainMenuScoreTable() {        
        System.setIn(new ByteArrayInputStream("2\n3".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
            
        mainController.run();
            
        assertEquals(3, mainController.getSelectedItem());
        assertFalse(mainController.isGameRunning());
    }

    /**
     * Test of run method, of class MainController.
     */
    @Test
    public void testRunAndClose() {
        System.setIn(new ByteArrayInputStream("3".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
            
        mainController.run();
            
        assertEquals(3, mainController.getSelectedItem());
        assertFalse(mainController.isGameRunning());
    }

    /**
     * Test of getSelectedItem method, of class MainController.
     */
    @Test
    public void testGetSelectedItem() {
        assertEquals(0, mainController.getSelectedItem());
    }

}
