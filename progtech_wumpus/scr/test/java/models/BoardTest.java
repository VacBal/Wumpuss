package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    public BoardTest() {
    }

    @BeforeEach
    public void setUp() {
        board = new Board(5, new Position(2, 2));
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getSize method, of class Board.
     */
    @Test
    public void testGetSize() {
        assertEquals(5, board.getSize());
    }

    /**
     * Test of getBoard method, of class Board.
     */
    @Test
    public void testGetMap() {
        assertArrayEquals(new char[5][5], board.getMap());
    }

    /**
     * Test of changeBoardCell method, of class Board.
     */
    @Test
    public void testChangeBoardCell() {
        board.changeBoardCell(new Position(1, 1), 'W');
        assertEquals('W', board.getMap()[1][1]);
    }

    /**
     * Test of getPlayerPosition method, of class Board.
     */
    @Test
    public void testGetPlayerPosition() {
        assertEquals(new Position(2, 2), board.getPlayerPosition());
    }

    /**
     * Test of setPlayerPosition method, of class Board.
     */
    @Test
    public void testSetPlayerPosition() {
        assertEquals(new Position(2, 2), board.getPlayerPosition());
        board.setPlayerPosition(new Position(3, 4));
        assertEquals(new Position(3, 4), board.getPlayerPosition());
    }

    /**
     * Test of getStartPosition method, of class Board.
     */
    @Test
    public void testGetStartPosition() {
        assertEquals(new Position(2, 2), board.getStartPosition());
    }

    /**
     * Test of getNumberOfWumpus method, of class Board.
     */
    @Test
    public void testGetNumberOfWumpus() {
        assertEquals(0, board.getNumberOfWumpus());
    }

    /**
     * Test of setNumberOfWumpus method, of class Board.
     */
    @Test
    public void testSetNumberOfWumpus() {
        assertEquals(0, board.getNumberOfWumpus());
        board.setNumberOfWumpus(6);
        assertEquals(6, board.getNumberOfWumpus());
    }

    /**
     * Test of hashCode method, of class Board.
     */
    @Test
    public void testHashCode() {
        assertEquals(new Board(5, new Position(2, 2)).hashCode(), board.hashCode());
    }

    /**
     * Test of equals method, of class Board.
     */
    @Test
    public void testEquals() {
        Board testBoard = new Board(5, new Position(2, 2));
        testBoard.changeBoardCell(new Position(1, 1), 'W');

        Board unconstructedPosition = null;
        var incompatibleClass = "row: 2; column: 4";

        assertTrue(new Board(5, new Position(2, 2)).equals(board));
        assertTrue(board.equals(board));
        assertFalse(new Board(5, new Position(1, 1)).equals(board));
        assertFalse(new Board(4, new Position(2, 2)).equals(board));
        board.setNumberOfWumpus(3);
        assertFalse(new Board(5, new Position(2, 2)).equals(board));
        assertFalse(board.equals(unconstructedPosition));
        assertFalse(board.equals(incompatibleClass));
    }
}
