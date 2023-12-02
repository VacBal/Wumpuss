package models;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player(3, 'W');
    }

    @AfterEach
    public void tearDown() throws Exception {
        player = null;
    }

    /**
     * Test of getNumberOfSteps method, of class Player.
     */
    @Test
    public void testGetNumberOfSteps() {        
        assertEquals(0, player.getNumberOfSteps());        
    }

    /**
     * Test of setNumberOfSteps method, of class Player.
     */
    @Test
    public void testSetNumberOfSteps() {
        assertEquals(0, player.getNumberOfSteps());
        player.setNumberOfSteps(5);
        assertEquals(5, player.getNumberOfSteps());        
    }

    /**
     * Test of getNumberOfArrows method, of class Player.
     */
    @Test
    public void testGetNumberOfArrows() {
        assertEquals(3, player.getNumberOfArrows()); 
    }

    /**
     * Test of setNumberOfArrows method, of class Player.
     */
    @Test
    public void testSetNumberOfArrows() {
        assertEquals(3, player.getNumberOfArrows());
        player.setNumberOfArrows(2);
        assertEquals(2, player.getNumberOfArrows());   
    }

    /**
     * Test of getCurrentDirection method, of class Player.
     */
    @Test
    public void testGetCurrentDirection() {        
        assertEquals('W', player.getCurrentDirection());        
    }

    /**
     * Test of setCurrentDirection method, of class Player.
     */
    @Test
    public void testSetCurrentDirection() {
        assertEquals('W', player.getCurrentDirection());
        player.setCurrentDirection('N');
        assertEquals('N', player.getCurrentDirection());  
    }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        StringBuilder sb = new StringBuilder("Player {");
        sb.append("Number Of Arrows = ").append(3);
        sb.append(", Number Of Steps = ").append(0);
        sb.append(", Current Direction = ").append('W');
        sb.append('}');

        assertEquals(sb.toString(), player.toString());
    }
}
