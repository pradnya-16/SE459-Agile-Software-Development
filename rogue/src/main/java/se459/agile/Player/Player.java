package se459.agile.Player;

import java.awt.*;

public class Player {
    private int x, y;  // Player's position
    private final int size = 20;  // Player size
    private final int speed = 10; // Movement speed

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
}
