package se459.agile.Levels;

import java.awt.*;

public class Stairs {
    private int x, y;           // Stairs' position
    private final int size = 20; // Size of the stairs

    public Stairs(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }


    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, size, size);
    }

    // Check if the player is on the stairs
    public boolean checkCollision(int playerX, int playerY) {
        return Math.abs(playerX - x) < size && Math.abs(playerY - y) < size;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
