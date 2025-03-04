package se459.agile.Player;

import java.awt.*;
import java.util.LinkedList;

public class Monster {
    private int x, y; // Position of the monster
    private final int size = 20; // Size of the ghost
    private final int speed = 5;
    private boolean isActive = false; // Active only when player is in Room 2
    private int health = 50; // Monster health

    // Room 2 boundaries
    private final int room2MinX = 190;
    private final int room2MaxX = 350;
    private final int room2MinY = 290;
    private final int room2MaxY = 390;

    public Monster(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void activateIfPlayerInRoom(Player player) {
        if (player.getX() >= room2MinX && player.getX() <= room2MaxX &&
                player.getY() >= room2MinY && player.getY() <= room2MaxY) {
            isActive = true;
        } else {
            isActive = false;
        }
    }

    public void moveTowards(Player player) {
        if (!isActive || health <= 0) return; // Stop moving if dead

        int dx = 0, dy = 0;

        if (player.getX() > x) dx = speed;
        if (player.getX() < x) dx = -speed;
        if (player.getY() > y) dy = speed;
        if (player.getY() < y) dy = -speed;

        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        if (health <= 0) return; // Don't draw if dead
    
         // Draw ghost body
        g.setColor(Color.BLUE);
        g.fillArc(x, y, size, size, 0, 180); // Semi-circle ghost shape
    
        // Draw ghost "legs"
        g.fillRect(x, y + size / 2, size, size / 4);
    
        // Draw eyes
        g.setColor(Color.WHITE);
        g.fillOval(x + 4, y + 4, 5, 5); // Left eye
        g.fillOval(x + 11, y + 4, 5, 5); // Right eye
    
        // Draw pupils
        g.setColor(Color.BLACK);
        g.fillOval(x + 5, y + 6, 2, 2); // Left pupil
        g.fillOval(x + 12, y + 6, 2, 2); // Right pupil

    }
    

    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            health = 0; // Ensure health doesn't go negative
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean checkCollision(Player player) {
        if (!isAlive()) return false; // No collision if dead

        return Math.abs(player.getX() - x) < size / 2 &&
               Math.abs(player.getY() - y) < size / 2;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
