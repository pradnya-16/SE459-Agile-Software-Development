package se459.agile.Player;

import java.awt.*;
import java.util.LinkedList;

public class Monster {
    private LinkedList<Point> body; // Snake segments
    private final int segmentSize = 10;
    private final int maxLength = 5; // Number of segments
    private final int speed = 5;
    private boolean isActive = false; // Active only when player is in Room 2

    // Define Room 2 boundaries
    private final int room2MinX = 190;
    private final int room2MaxX = 350;
    private final int room2MinY = 290;
    private final int room2MaxY = 390;

    public Monster(int startX, int startY) {
        body = new LinkedList<>();
        body.add(new Point(startX, startY)); // Start near the Room 2 entry
    }

    // Activate monster when player enters Room 2
    public void activateIfPlayerInRoom(Player player) {
        if (player.getX() >= room2MinX && player.getX() <= room2MaxX &&
                player.getY() >= room2MinY && player.getY() <= room2MaxY) {
            isActive = true;
        } else {
            isActive = false;
        }
    }

    // Move towards the player if active
    public void moveTowards(Player player) {
        if (!isActive) return; // Monster stays idle if not active

        Point head = body.getFirst();
        int dx = 0, dy = 0;

        if (player.getX() > head.x) dx = speed;
        if (player.getX() < head.x) dx = -speed;
        if (player.getY() > head.y) dy = speed;
        if (player.getY() < head.y) dy = -speed;

        Point newHead = new Point(head.x + dx, head.y + dy);
        body.addFirst(newHead);

        // Limit the snake length
        if (body.size() > maxLength) {
            body.removeLast();
        }
    }

    // Draw the snake
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        for (Point p : body) {
            g.fillRect(p.x, p.y, segmentSize, segmentSize);
        }

        // Draw eyes on the head
        Point head = body.getFirst();
        g.setColor(Color.WHITE);
        g.fillOval(head.x + 2, head.y + 2, 4, 4); // Left eye
        g.fillOval(head.x + 7, head.y + 2, 4, 4); // Right eye
    }

    // Check collision with player
    public boolean checkCollision(Player player) {
        Point head = body.getFirst();
        return Math.abs(player.getX() - head.x) < segmentSize &&
                Math.abs(player.getY() - head.y) < segmentSize;
    }

    public int getX() { return body.getFirst().x; }
    public int getY() { return body.getFirst().y; }
}
