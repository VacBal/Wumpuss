package controllers;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerControllerTest {

    private PlayerController player;

    @AfterEach
    public void tearDown() throws Exception {
        player = null;
    }

    @BeforeEach
    public void setUp() throws Exception {
        player = new PlayerController(1, 'W');
    }

    /**
     * Test of getCurrentDirection method, of class PlayerController.
     */
    @Test
    public void testGetCurrentDirection() {
        assertEquals('W', player.getCurrentDirection());
    }

    /**
     * Test of getNumberOfArrows method, of class PlayerController.
     */
    @Test
    public void testGetNumberOfArrows() {
        assertEquals(1, player.getNumberOfArrows());
    }

    /**
     * Test of getNumberOfSteps method, of class PlayerController.
     */
    @Test
    public void testGetNumberOfSteps() {
        assertEquals(0, player.getNumberOfSteps());
    }

    /**
     * Test of move method, of class PlayerController.
     */
    @Test
    public void testMove() {
        assertEquals(0, player.getNumberOfSteps());
        player.move();
        assertEquals(1, player.getNumberOfSteps());
    }

    /**
     * Test of turnRight method, of class PlayerController.
     */
    @Test
    public void testTurnRightWhenPlayerDirectionIsNorth() {
        player = new PlayerController(1, 'N');
        assertEquals('N', player.getCurrentDirection());
        player.turnRight();
        assertEquals('E', player.getCurrentDirection());
    }

    /**
     * Test of turnRight method, of class PlayerController.
     */
    @Test
    public void testTurnRightWhenPlayerDirectionIsEast() {
        player = new PlayerController(1, 'E');
        assertEquals('E', player.getCurrentDirection());
        player.turnRight();
        assertEquals('S', player.getCurrentDirection());
    }

    /**
     * Test of turnRight method, of class PlayerController.
     */
    @Test
    public void testTurnRightWhenPlayerDirectionIsSouth() {
        player = new PlayerController(1, 'S');
        assertEquals('S', player.getCurrentDirection());
        player.turnRight();
        assertEquals('W', player.getCurrentDirection());
    }

    /**
     * Test of turnRight method, of class PlayerController.
     */
    @Test
    public void testTurnRightWhenPlayerDirectionIsWest() {
        assertEquals('W', player.getCurrentDirection());
        player.turnRight();
        assertEquals('N', player.getCurrentDirection());
    }

    /**
     * Test of turnLeft method, of class PlayerController.
     */
    @Test
    public void testTurnLeftWhenPlayerDirectionIsNorth() {
        player = new PlayerController(1, 'N');
        assertEquals('N', player.getCurrentDirection());
        player.turnLeft();
        assertEquals('W', player.getCurrentDirection());
    }

    /**
     * Test of turnLeft method, of class PlayerController.
     */
    @Test
    public void testTurnLeftWhenPlayerDirectionIsEast() {
        player = new PlayerController(1, 'E');
        assertEquals('E', player.getCurrentDirection());
        player.turnLeft();
        assertEquals('N', player.getCurrentDirection());
    }

    /**
     * Test of turnLeft method, of class PlayerController.
     */
    @Test
    public void testTurnLeftWhenPlayerDirectionIsSouth() {
        player = new PlayerController(1, 'S');
        assertEquals('S', player.getCurrentDirection());
        player.turnLeft();
        assertEquals('E', player.getCurrentDirection());
    }

    /**
     * Test of turnLeft method, of class PlayerController.
     */
    @Test
    public void testTurnLeftWhenPlayerDirectionIsWest() {
        assertEquals('W', player.getCurrentDirection());
        player.turnLeft();
        assertEquals('S', player.getCurrentDirection());
    }

    /**
     * Test of shoot method, of class PlayerController.
     */
    @Test
    public void testWhenPlayerHaveArrowShoot() {
        assertEquals(1, player.getNumberOfArrows());
        player.shoot();
        assertEquals(0, player.getNumberOfArrows());
    }

    /**
     * Test of shoot method, of class PlayerController.
     */
    @Test
    public void testWhenPlayerNotHaveArrowShoot() {
        assertEquals(1, player.getNumberOfArrows());
        player.shoot();
        assertEquals(0, player.getNumberOfArrows());
        player.shoot();
        assertEquals(0, player.getNumberOfArrows());
    }

    /**
     * Test of reduceTheNumberOfArrows method, of class PlayerController.
     */
    @Test
    public void testReduceTheNumberOfArrows() {
        assertEquals(1, player.getNumberOfArrows());
        player.shoot();
        assertEquals(0, player.getNumberOfArrows());
    }

    /**
     * Test of hashCode method, of class PlayerController.
     */
    @Test
    public void testHashCode() {
        assertEquals(new PlayerController(1, 'W').hashCode(), player.hashCode());
    }

    /**
     * Test of equals method, of class PlayerController.
     */
    @Test
    public void testEquals() {
        PlayerController unconstructedPlayer = null;
        var incompatibleClass = "row: 2; column: 4";

        assertTrue(new PlayerController(1, 'W').equals(player));
        assertTrue(player.equals(player));
        assertFalse(new PlayerController(1, 'E').equals(player));
        assertFalse(new PlayerController(4, 'W').equals(player));
        assertFalse(player.equals(unconstructedPlayer));
        assertFalse(player.equals(incompatibleClass));
    }

}
