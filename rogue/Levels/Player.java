package Levels;

import java.awt.*;

public class Player {
    private int x, y;
    private final int size = 20;
    private final int speed = 10;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void move(int dx, int dy) {
        x += dx * speed;
        y += dy * speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSpeed() { return speed; }

    // ✅ FIX: Implement setPosition() to reset player position
    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}
