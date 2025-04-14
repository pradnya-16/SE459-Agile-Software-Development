package se459.agile.Player;

import java.awt.*;

import se459.agile.Items.Item;

public class Player {
    private int x, y;
    private final int size = 20;
    private final int speed = 10;
    private Pack pack;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.pack = new Pack();
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

    public Pack getPack() { return pack; }

    public void pickUpItem(Item item) {
        boolean added = pack.addItem(item);
        if (added) {
            System.out.println("Picked up: " + item.getName());
        }
    }

    public void dropItem(int index) {
        if (index < pack.getItems().size()) {
            Item removedItem = pack.getItems().get(index);
            pack.removeItem(removedItem);
            System.out.println("Dropped: " + removedItem.getName());
        }
    }
}
