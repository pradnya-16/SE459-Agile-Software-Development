package Levels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Level1 extends JPanel implements KeyListener {
    private Player player;

    public Level1() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 500));
        player = new Player(50, 50); // Start player at (50,50)
        addKeyListener(this);
        setFocusable(true); // Ensures the panel listens for key events
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw game objects
        g.setColor(Color.WHITE);
        for (int i = 1; i < 3; i++) {
            g.drawString("-------------------||--------------------", i * 100, (i * 200) - 100);
            g.drawString("-------------------||--------------------", i * 100, i * 200);
            for (int j = 1; j < 6; j++) {
                g.drawString("|", (i * 100), ((i * 100 + ((i - 1) * 100)) + (j * 20)) - 10);
                g.drawString("|", (i * 100) + 160, ((i * 100 + ((i - 1) * 100)) + (j * 20)) - 10);
            }
        }

        // Draw player
        player.draw(g);
    }

    // Handle key events to move the player
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int dx = 0, dy = 0;

        if (key == KeyEvent.VK_LEFT) { dx = -1; }
        if (key == KeyEvent.VK_RIGHT) { dx = 1; }
        if (key == KeyEvent.VK_UP) { dy = -1; }
        if (key == KeyEvent.VK_DOWN) { dy = 1; }

        player.move(dx, dy);
        repaint(); // Refresh screen after movement
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
