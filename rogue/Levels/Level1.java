package Levels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Level1 extends JPanel implements KeyListener {
    private Player player;
    private int playerHealth = 100;
    private int playerScore = 0;
    private final Rectangle staircase = new Rectangle(700, 400, 40, 40); // Staircase Position

    public Level1() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 500));
        player = new Player(50, 50); 
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        for (int i = 1; i < 3; i++) {
            g.drawString("----------------||--------------------", i * 100, (i * 200) - 100);
            g.drawString("----------------||--------------------", i * 100, i * 200);
            for (int j = 1; j < 6; j++) {
                g.drawString("|", (i * 100), ((i * 100 + ((i - 1) * 100)) + (j * 20)) - 10);
                g.drawString("|", (i * 100) + 160, ((i * 100 + ((i - 1) * 100)) + (j * 20)) - 10);
            }
        }

        // Draw Player
        player.draw(g);

        // Draw Staircase (Exit)
        g.setColor(Color.YELLOW);
        g.fillRect(staircase.x, staircase.y, staircase.width, staircase.height);
        g.setColor(Color.BLACK);
        g.drawString("EXIT", staircase.x + 5, staircase.y + 25);

        // Draw Player Stats at the Bottom
        g.setColor(Color.WHITE);
        g.fillRect(0, 460, 800, 40); // Background for stats
        g.setColor(Color.BLACK);
        g.drawString("Health: " + playerHealth, 20, 480);
        g.drawString("Score: " + playerScore, 150, 480);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int dx = 0, dy = 0;

        if (key == KeyEvent.VK_LEFT) { dx = -1; }
        if (key == KeyEvent.VK_RIGHT) { dx = 1; }
        if (key == KeyEvent.VK_UP) { dy = -1; }
        if (key == KeyEvent.VK_DOWN) { dy = 1; }

        // Check for collisions before moving the player
        if (canMove(player.getX() + dx * player.getSpeed(), player.getY() + dy * player.getSpeed())) {
            player.move(dx, dy);
            playerScore += 1; // Increase score when moving
        }

        // Check if player reaches the staircase (exit)
        if (staircase.contains(player.getX(), player.getY())) {
            JOptionPane.showMessageDialog(this, "You have reached the next level!", "Level Up!", JOptionPane.INFORMATION_MESSAGE);
            playerScore += 100; // Bonus score for reaching the staircase
            playerHealth += 10; // Small health boost
            player.setPosition(50, 50); // Reset player position to the start
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    // Collision Detection (Unchanged)
    private boolean canMove(int newX, int newY) {
        Rectangle playerBounds = new Rectangle(newX, newY, 20, 20);
    
        for (int i = 1; i < 3; i++) {
            int wallX1 = i * 100;
            int wallY1 = (i * 200) - 100;
            int wallY2 = i * 200;
            int wallX2 = wallX1 + 160;
    
            for (int j = 1; j < 6; j++) {
                int verticalWallY = (i * 100 + ((i - 1) * 100)) + (j * 20) - 10;
    
                Rectangle horizontalWall1 = new Rectangle(wallX1, wallY1, 160, 5);
                Rectangle horizontalWall2 = new Rectangle(wallX1, wallY2, 160, 5);
                Rectangle verticalWall1 = new Rectangle(wallX1, verticalWallY, 5, 20);
                Rectangle verticalWall2 = new Rectangle(wallX2, verticalWallY, 5, 20);
    
                // Allow player to pass through "||" gate
                if (playerBounds.intersects(verticalWall1) || playerBounds.intersects(verticalWall2)) {
                    if (newX >= wallX1 - 5 && newX <= wallX2 + 5) {
                        return true; // Allow passing through the gate
                    }
                }
    
                // Block movement through other parts of the walls
                if (playerBounds.intersects(horizontalWall1) || playerBounds.intersects(horizontalWall2)) {
                    return false;
                }
            }
        }
        return true;
    }
}
