package controllers;

import builders.GameBuilder;
import models.Board;
import models.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardControllerTest {
    private GameBuilder gameBuilder;
    private BoardController boardController;
    
    @BeforeEach
    public void setUp() {
        gameBuilder = new GameBuilder();
        boardController = gameBuilder.getBoard();
    }
    
    @AfterEach
    public void tearDown() {
        gameBuilder = null;
        boardController = null;
    }

    /**
     * Test of getSize method, of class BoardController.
     */
    @Test
    public void testGetSize() {
        assertEquals(11, boardController.getSize());
    }

    /**
     * Test of getPlayerPosition method, of class BoardController.
     */
    @Test
    public void testGetPlayerPosition() {
        assertEquals(new Position(9, 9), boardController.getPlayerPosition());
    }

    /**
     * Test of getNumberOfWumpus method, of class BoardController.
     */
    @Test
    public void testGetNumberOfWumpus() {
        assertEquals(3, boardController.getNumberOfWumpus());
    }

    /**
     * Test of decreaseNumberOfWumpus method, of class BoardController.
     */
    @Test
    public void testDecreaseNumberOfWumpus() {
        assertEquals(3, boardController.getNumberOfWumpus());
        boardController.decreaseNumberOfWumpus();
        assertEquals(2, boardController.getNumberOfWumpus());
        boardController.decreaseNumberOfWumpus();
        boardController.decreaseNumberOfWumpus();
        assertEquals(0, boardController.getNumberOfWumpus());
        boardController.decreaseNumberOfWumpus();
        assertEquals(0, boardController.getNumberOfWumpus());
    }

    /**
     * Test of setPlayerPosition method, of class BoardController.
     */
    @Test
    public void testSetPlayerPosition() {
        assertEquals(new Position(9, 9), boardController.getPlayerPosition());
        boardController.setPlayerPosition(new Position(2, 2));
        assertEquals(new Position(2, 2), boardController.getPlayerPosition());
    }

    /**
     * Test of getCell method, of class BoardController.
     */
    @Test
    public void testGetCell() {
        assertEquals('H', boardController.getCell(new Position(9, 9)));
    }

    /**
     * Test of setCell method, of class BoardController.
     */
    @Test
    public void testSetCell() {
        Position position = new Position(8, 9);
        assertEquals('W', boardController.getCell(position));
        boardController.setCell(position, '_');
        assertEquals('_', boardController.getCell(position));
    }

    /**
     * Test of setRow method, of class BoardController.
     */
    @Test
    public void testWhenRowIndexLessThenZeroSetRow() {
        try {
            boardController.setRow(-1, "W_________W");
            fail("The expected RuntimeException did not occur");
        } catch (RuntimeException exception) {            
            assertEquals("Invalid input file!", "Invalid input file!", exception.getMessage());
        }
    }
    
    /**
     * Test of setRow method, of class BoardController.
     */
    @Test
    public void testWhenRowIndexGreaterThenBoardSizeSetRow() {
        try {
            boardController.setRow(12, "W_________W");
            fail("The expected RuntimeException did not occur");
        } catch (RuntimeException exception) {            
            assertEquals("Invalid input file!", "Invalid input file!", exception.getMessage());
        }
    }
    
    /**
     * Test of setRow method, of class BoardController.
     */
    @Test
    public void testWhenRowLongerThenBoardSizeSetRow() {
        try {
            boardController.setRow(1, "W__________W");
            fail("The expected RuntimeException did not occur");
        } catch (RuntimeException exception) {            
            assertEquals("Invalid input file!", "Invalid input file!", exception.getMessage());
        }
    }

    /**
     * Test of isValidMove method, of class BoardController.
     */
    @Test
    public void testIsValidMove() {
        assertTrue(boardController.isValidMove(new Position(9, 8)));
        assertFalse(boardController.isValidMove(new Position(8, 9)));
        assertFalse(boardController.isValidMove(new Position(-1, 9)));
        assertFalse(boardController.isValidMove(new Position(12, 9)));
        assertFalse(boardController.isValidMove(new Position(8, -1)));
        assertFalse(boardController.isValidMove(new Position(8, 12)));
    }

    /**
     * Test of hashCode method, of class BoardController.
     */
    @Test
    public void testHashCode() {
        BoardController boardController2 = new BoardController(new Board(3, new Position(1, 1)));
        
        assertEquals(boardController2.hashCode(), boardController.hashCode());
    }

    /**
     * Test of equals method, of class BoardController.
     */
    @Test
    public void testEquals() {
        BoardController boardController2 = new BoardController(new Board(3, new Position(1, 1)));
        BoardController unconstructed = null;
        var incompatibleClass = "row: 2; column: 4";

        
        assertTrue(boardController.equals(boardController));
        assertFalse(boardController.equals(boardController2)); 
        assertFalse(boardController.equals(unconstructed));
        assertFalse(boardController.equals(incompatibleClass));
    }
    
}
