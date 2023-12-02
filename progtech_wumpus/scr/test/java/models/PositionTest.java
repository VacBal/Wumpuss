package models;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {

    private Position position;

    @BeforeEach
    public void setUp() throws Exception {
        position = new Position(3, 3);
    }

    @AfterEach
    public void tearDown() throws Exception {
        position = null;
    }

    /**
     * Test of getRow method, of class Position.
     */
    @Test
    public void testGetRow() {
        assertEquals(3, position.getRow());
    }

    /**
     * Test of setRow method, of class Position.
     */
    @Test
    public void testSetRow() {
        position.setRow(5);
        assertEquals(5, position.getRow());
    }

    /**
     * Test of getColumn method, of class Position.
     */
    @Test
    public void testGetColumn() {
        assertEquals(3, position.getColumn());
    }

    /**
     * Test of setColumn method, of class Position.
     */
    @Test
    public void testSetColumn() {
        position.setColumn(5);
        assertEquals(5, position.getColumn());
    }

    /**
     * Test of hashCode method, of class Position.
     */
    @Test
    public void testHashCode() {
        assertEquals(new Position(3, 3).hashCode(), position.hashCode());
    }

    /**
     * Test of equals method, of class Position.
     */
    @Test
    public void testEquals() {
        Position unconstructedPosition = null;
        var incompatibleClass = "row: 2; column: 4";

        assertTrue(new Position(3, 3).equals(position));
        assertTrue(position.equals(position));
        assertFalse(new Position(5, 3).equals(position));
        assertFalse(new Position(3, 5).equals(position));
        assertFalse(new Position(3, 5).equals(unconstructedPosition));
        assertFalse(new Position(3, 5).equals(incompatibleClass));
    }

    /**
     * Test of toString method, of class Position.
     */
    @Test
    public void testToString() {
        String expected = "Position{row = 3, column = 3}";
        assertEquals(expected, position.toString());
    }

}
