package builders;

import models.Position;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBuilderTest {
    private GameBuilder builder;

    @BeforeEach
    public void setUp() {        
        builder = new GameBuilder();
    }

    @AfterEach
    public void tearDown() throws Exception {
    }
    
    @Test
    void testPlayerInitialized() {
        assertNotNull(builder.getPlayer());        
    }
    
    @Test
    void testPlayerInitialPosition() {
        assertEquals(new Position(9, 9), builder.getBoard().getPlayerPosition());        
    }
    
    @Test
    void testPlayerInitialArrow() {
        assertEquals(3, builder.getPlayer().getNumberOfArrows());        
    }
    
    @Test
    void testBoardInitialized() {
        assertNotNull(builder.getBoard());        
    }
    
    @Test
    void testBoardSize() {
        assertEquals(11, builder.getBoard().getSize());        
    }
}
