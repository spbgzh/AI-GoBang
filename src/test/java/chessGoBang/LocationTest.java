package chessGoBang;

import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    // AI
    public static final int AI = 1;

    // player
    public static final int HUMAN = 2;

    Location loc = new Location(1, 2, HUMAN, 1000);
    Location loc1 = new Location(1, 2, AI, 1000);

    @Test
    public void TestLocationGetScore() {
        assertEquals(1000, loc.getScore());
    }

    @Test
    public void TestLocationGetPlayer() {
        assertEquals(AI, loc1.getPlayer());
    }

}
