package se459.agile.Player;

import java.awt.*;

public class Player {
    private int x, y;  // Player's position
    private int screenWidth, screenHeight;
    private final int size = 20;  // Player size
    private final int speed = 10; // Movement speed

    // ✅ Added constructor for compatibility with Level1.java
    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.screenWidth = 800;  // Default screen width
        this.screenHeight = 600; // Default screen height
    }

    // ✅ Existing constructor (used in PlayerTest.java)
    public Player(int startX, int startY, int screenWidth, int screenHeight) {
        this.x = startX;
        this.y = startY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void move(int dx, int dy) {
        int newX = x + (dx * speed);
        int newY = y + (dy * speed);

        // Prevent moving out of bounds
        if (newX >= 0 && newX <= screenWidth - size) {
            x = newX;
        }
        if (newY >= 0 && newY <= screenHeight - size) {
            y = newY;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
