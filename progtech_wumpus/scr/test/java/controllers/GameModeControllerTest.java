package controllers;

import builders.GameBuilder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import models.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameModeControllerTest {
    
    private GameModeController gameModeController;
    
    
    @BeforeEach
    public void setUp() {
        GameBuilder builder = new GameBuilder();
        gameModeController = new GameModeController(builder.getBoard(), builder.getPlayer());
    }
    
    @AfterEach
    public void tearDown() {
        gameModeController = null;
    }

    /**
     * Test of start method wrong input branch, of class GameModeController.
     */
    @Test
    public void testWrongActionInput() {
        System.setIn(new ByteArrayInputStream("player\nA\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertFalse(gameModeController.isGameInProgress());
    }
    
    /**
     * Test of start method close branch, of class GameModeController.
     */
    @Test
    public void testExitFromGame() {
        System.setIn(new ByteArrayInputStream("player\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertFalse(gameModeController.isGameInProgress());
    }
    
    /**
     * Test of start method move branch, of class GameModeController.
     * The Player direction is West.
     */
    @Test
    public void testWhenPlayerMoveToEmptyCell() {
        System.setIn(new ByteArrayInputStream("player\nM\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        // player properties
        assertEquals('W', gameModeController.getPlayer().getCurrentDirection());
        assertEquals(1, gameModeController.getPlayer().getNumberOfSteps());
        assertEquals(3, gameModeController.getPlayer().getNumberOfArrows());
        
        // board properties
        assertEquals(3, gameModeController.getBoard().getNumberOfWumpus());
        assertEquals(11, gameModeController.getBoard().getSize());
        assertEquals('_', gameModeController.getBoard().getCell(new Position(9,9)));
        assertEquals('H', gameModeController.getBoard().getCell(new Position(9,8)));
        assertEquals(new Position(9, 8), gameModeController.getBoard().getPlayerPosition());
    }
    
    /**
     * Test of start method move branch, of class GameModeController.
     * The Player direction is East.
     */
    @Test
    public void testWhenPlayerMoveToStartCellWithTheGold() {
        gameModeController.getBoard().setPlayerPosition(new Position(9 ,8));
        gameModeController.setIsPlayerOwnTheGold(true);
        System.setIn(new ByteArrayInputStream("player\nR\nR\nM".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertFalse(gameModeController.isGameInProgress());
    }
    
    /**
     * Test of start method move branch, of class GameModeController.
     * The Player direction is East.
     */
    @Test
    public void testWhenPlayerMoveToStartCellWithoutTheGold() {
        gameModeController.getBoard().setPlayerPosition(new Position(9 ,8));        
        System.setIn(new ByteArrayInputStream("player\nR\nR\nM\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
                
        assertTrue(gameModeController.getBoard().getStartPosition().equals(gameModeController.getBoard().getPlayerPosition()));
        assertFalse(gameModeController.isIsPlayerOwnTheGold());
    }
    
    /**
     * Test of start method move branch, of class GameModeController.
     * The player direction is South;
     */
    @Test
    public void testWhenPlayerMoveToWall() {
        System.setIn(new ByteArrayInputStream("player\nL\nM\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        // player properties
        assertEquals('S', gameModeController.getPlayer().getCurrentDirection());
        assertEquals(1, gameModeController.getPlayer().getNumberOfSteps());
        assertEquals(3, gameModeController.getPlayer().getNumberOfArrows());
        
        // board properties
        assertEquals(3, gameModeController.getBoard().getNumberOfWumpus());
        assertEquals(11, gameModeController.getBoard().getSize());
        assertEquals('H', gameModeController.getBoard().getCell(new Position(9,9)));
        assertEquals('W', gameModeController.getBoard().getCell(new Position(10,9)));
        assertEquals(new Position(9, 9), gameModeController.getBoard().getPlayerPosition());
    }
    
    /**
     * Test of start method move branch, of class GameModeController.
     * The player direction is North;
     */
    @Test
    public void testWhenPlayerMoveToPile() {
        gameModeController.getBoard().setPlayerPosition(new Position(9 ,5));
        System.setIn(new ByteArrayInputStream("player\nR\nM\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        // player properties
        assertEquals('N', gameModeController.getPlayer().getCurrentDirection());
        assertEquals(2, gameModeController.getPlayer().getNumberOfSteps());
        assertEquals(2, gameModeController.getPlayer().getNumberOfArrows());
        
        // board properties
        assertEquals(3, gameModeController.getBoard().getNumberOfWumpus());
        assertEquals(11, gameModeController.getBoard().getSize());
    }
    
    /**
     * Test of start method move branch, of class GameModeController.
     * The player direction is North;
     */
    @Test
    public void testWhenPlayerMoveToWumpus() {
        gameModeController.getBoard().setPlayerPosition(new Position(1 ,2));
        System.setIn(new ByteArrayInputStream("player\nR\nR\nM".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        assertFalse(gameModeController.isGameInProgress());
    }
    
    /**
     * Test of start method move branch, of class GameModeController.
     * The player direction is North;
     */
    @Test
    public void testWhenPlayerMoveToGold() {
        gameModeController.getBoard().setPlayerPosition(new Position(2 ,1));
        System.setIn(new ByteArrayInputStream("player\nR\nM\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        assertFalse(gameModeController.isIsPlayerOwnTheGold());
        assertEquals(2, gameModeController.getPlayer().getNumberOfSteps());        
    }
    
    /**
     * Test of start method turn right branch, of class GameModeController.
     */
    @Test
    public void testWhenPlayerTurnRight() {
        System.setIn(new ByteArrayInputStream("player\nr\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertEquals('N', gameModeController.getPlayer().getCurrentDirection());
    }
    
    /**
     * Test of start method turn left branch, of class GameModeController.
     */
    @Test
    public void testWhenPlayerTurnLeft() {
        System.setIn(new ByteArrayInputStream("player\nl\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertEquals('S', gameModeController.getPlayer().getCurrentDirection());
    }
    
    /**
     * Test of start method shot branch, of class GameModeController.
     */
    @Test
    public void testWhenPlayerHorizontalShotToTheWall() {        
        System.setIn(new ByteArrayInputStream("player\nS\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertEquals(2, gameModeController.getPlayer().getNumberOfArrows());
        assertEquals(3, gameModeController.getBoard().getNumberOfWumpus());
    }
    
    /**
     * Test of start method shot branch, of class GameModeController.
     */
    @Test
    public void testWhenPlayerVerticalShotToTheWall() {
        gameModeController.getBoard().setPlayerPosition(new Position(2 ,9));
        System.setIn(new ByteArrayInputStream("player\nL\nS\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertEquals(2, gameModeController.getPlayer().getNumberOfArrows());
        assertEquals(3, gameModeController.getBoard().getNumberOfWumpus());
    }
    
    /**
     * Test of start method shot branch, of class GameModeController.
     */
    @Test
    public void testWhenPlayerHorizontalShotToTheWumpus() {
        gameModeController.getBoard().setPlayerPosition(new Position(1 ,2));
        System.setIn(new ByteArrayInputStream("player\nR\nR\nS\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertEquals(2, gameModeController.getPlayer().getNumberOfArrows());
        assertEquals(2, gameModeController.getBoard().getNumberOfWumpus());
    }
    
    /**
     * Test of start method shot branch, of class GameModeController.
     */
    @Test
    public void testWhenPlayerVerticalShotToTheWumpus() {
        gameModeController.getBoard().setPlayerPosition(new Position(9 ,1));
        System.setIn(new ByteArrayInputStream("player\nR\nS\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertEquals(2, gameModeController.getPlayer().getNumberOfArrows());
        assertEquals(2, gameModeController.getBoard().getNumberOfWumpus());
    }

    /**
     * Test of getUsername method, of class GameModeController.
     */
    @Test
    public void testGetUsername() {
        assertEquals("", gameModeController.getUsername());
    }

    /**
     * Test of getPlayer method, of class GameModeController.
    */
    @Test
    public void testGetPlayer() {
        PlayerController player = new PlayerController(3, 'W');
        assertEquals(player, gameModeController.getPlayer());
    }

    /**
     * Test of isGameInProgress method, of class GameModeController.
     */
    @Test
    public void testIsGameInProgress() {
        assertEquals(true, gameModeController.isGameInProgress());
    }

    /**
     * Test of setUsername method, of class GameModeController.
     */
    @Test
    public void testSetUsername() {
        assertEquals("", gameModeController.getUsername());
        gameModeController.setUsername("player");
        assertEquals("player", gameModeController.getUsername());
    }

    /**
     * Test of setGameInProgress method, of class GameModeController.
     */
    @Test
    public void testSetGameInProgress() {
        assertEquals(true, gameModeController.isGameInProgress());
        gameModeController.setGameInProgress(false);
        assertEquals(false, gameModeController.isGameInProgress());
    }

    /**
     * Test of isIsPlayerOwnTheGold method, of class GameModeController.
     */
    @Test
    public void testIsIsPlayerOwnTheGold() {
        assertFalse(gameModeController.isIsPlayerOwnTheGold());
    }

    /**
     * Test of setIsPlayerOwnTheGold method, of class GameModeController.
     */
    @Test
    public void testSetIsPlayerOwnTheGold() {
        assertFalse(gameModeController.isIsPlayerOwnTheGold());
        gameModeController.setIsPlayerOwnTheGold(true);
        assertTrue(gameModeController.isIsPlayerOwnTheGold());
    }

    /**
     * Test of pickUpGold method, of class GameModeController.
     */
    @Test
    public void testWhenPlayerNotOnGoldCellPickUpGold() {
        System.setIn(new ByteArrayInputStream("player\nP\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertFalse(gameModeController.isIsPlayerOwnTheGold());
    }
    
    /**
     * Test of pickUpGold method, of class GameModeController.
     */
    @Test
    public void testWhenPlayerOnGoldCellPickUpGold() {
        gameModeController.getBoard().setPlayerPosition(new Position(1, 1));
        System.setIn(new ByteArrayInputStream("player\nP\nE".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        gameModeController.start();
        
        assertTrue(gameModeController.isIsPlayerOwnTheGold());
    }
}
