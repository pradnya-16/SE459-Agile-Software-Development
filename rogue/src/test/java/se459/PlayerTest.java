package se459;
import se459.agile.Player.Player;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;
    private final int SPEED = 10;
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;

    @BeforeEach
    void setUp() {
        player = new Player(50, 50, SCREEN_WIDTH, SCREEN_HEIGHT); // Initial position (50,50)
    }

    @Test
    void testMoveRight() {
        int initialX = player.getX();
        player.move(1, 0); // Move right
        assertEquals(initialX + SPEED, player.getX(), "Player should move right by speed");
    }

    @Test
    void testMoveLeft() {
        int initialX = player.getX();
        player.move(-1, 0); // Move left
        assertEquals(initialX - SPEED, player.getX(), "Player should move left by speed");
    }

    @Test
    void testMoveUp() {
        int initialY = player.getY();
        player.move(0, -1); // Move up
        assertEquals(initialY - SPEED, player.getY(), "Player should move up by speed");
    }

    @Test
    void testMoveDown() {
        int initialY = player.getY();
        player.move(0, 1); // Move down
        assertEquals(initialY + SPEED, player.getY(), "Player should move down by speed");
    }

    @Test
    void testMoveOutOfBounds() {
    int screenWidth = 800;
    int screenHeight = 600;

    player = new Player(screenWidth - 20, screenHeight - 20, screenWidth, screenHeight);
    player.move(1, 0); // Attempt to move beyond right boundary
    assertEquals(screenWidth - 20, player.getX(), "Player should not move beyond screen width");

    player.move(0, 1); // Attempt to move beyond bottom boundary
    assertEquals(screenHeight - 20, player.getY(), "Player should not move beyond screen height");

    player = new Player(0, 0, screenWidth, screenHeight);
    player.move(-1, 0); // Attempt to move beyond left boundary
    assertEquals(0, player.getX(), "Player should not move beyond left boundary");

    player.move(0, -1); // Attempt to move beyond top boundary
    assertEquals(0, player.getY(), "Player should not move beyond top boundary");
}
}

