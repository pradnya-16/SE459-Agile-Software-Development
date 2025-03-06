package se459.agile.Levels;

import java.awt.*;
import java.awt.event.*;
import java.time.Instant;
import javax.swing.*;
import se459.agile.Player.Monster;
import se459.agile.Levels.Stairs;
import se459.agile.Player.Player;

public class Level2 extends JPanel implements KeyListener {
    private Player player;
    private Monster monster1, monster2, monster3; // Three monsters, one per room
    private Stairs stairs;
    private int playerHealth = 100;
    private int playerScore = 0;
    private String playerName;
    private Instant startTime;
    private long lastDamageTime = 0;
    private final long damageCooldown = 1000;
    private long lastAttackTime = 0;
    private final long attackCooldown = 500;

    public Level2() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 500));
        player = new Player(120, 200); // Start in Room 1

        // One monster per room
        monster1 = new Monster(220, 280);
        monster2 = new Monster(520, 150);
        monster3 = new Monster(200, 420);

        stairs = new Stairs(120, 380); // Stairs in the last room
        addKeyListener(this);
        setFocusable(true);
        this.startTime = Instant.now();

        String[] names = {"Rogue", "Warrior", "Knight", "Shadow", "Hunter"};
        playerName = names[(int) (Math.random() * names.length)];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Log player position
        System.out.println("x = " + player.getX());
        System.out.println("y = " + player.getY());

        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 500);

        // Draw walls and paths similar to Level 1
        g.setColor(Color.WHITE);
        drawWallsAndPaths(g);

        // Draw game objects
        player.draw(g);
        if (monster1.isAlive()) monster1.draw(g);
        if (monster2.isAlive()) monster2.draw(g);
        if (monster3.isAlive()) monster3.draw(g);
        stairs.draw(g);

        // UI Stats Bar
        g.setColor(Color.WHITE);
        g.fillRect(0, 460, 800, 40);
        g.setColor(Color.BLACK);
        g.drawString("Player: " + playerName, 20, 480);
        g.drawString("Health: " + playerHealth, 200, 480);
        g.drawString("Score: " + playerScore, 350, 480);
    }

    private void drawWallsAndPaths(Graphics g) {
        // Draw room walls and structure using a pattern similar to Level 1
        for (int i = 1; i < 4; i++) {
            g.drawString("-------------------||--------------------", i * 200, (i * 150) - 100);
            g.drawString("-------------------||--------------------", i * 200, i * 150);
            for (int j = 1; j < 6; j++) {
                g.drawString("|", i * 200, ((i * 150) - 100) + (j * 20));
                g.drawString("|", (i * 200) + 160, ((i * 150) - 100) + (j * 20));
            }
        }

        // Draw connecting paths
        g.setColor(Color.GREEN);
        for (int i = 1; i < 6; i++) {
            g.drawString("#", 275, 200 + (i * 10));
        }
        for (int i = 1; i < 11; i++) {
            g.drawString("#", 275 + (i * 10), 250);
        }
        for (int i = 1; i < 5; i++) {
            g.drawString("#", 475, 250 + (i * 10));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int dx = 0, dy = 0;

        if (key == KeyEvent.VK_LEFT) dx = -1;
        if (key == KeyEvent.VK_RIGHT) dx = 1;
        if (key == KeyEvent.VK_UP) dy = -1;
        if (key == KeyEvent.VK_DOWN) dy = 1;

        player.move(dx, dy);
        repaint();

        // Activate monsters only when the player enters their room
        if (checkRoom1()) {
            monster1.activateIfPlayerInRoom(player);
            monster1.moveTowards(player);
        }
        if (checkRoom2()) {
            monster2.activateIfPlayerInRoom(player);
            monster2.moveTowards(player);
        }
        if (checkRoom3()) {
            monster3.activateIfPlayerInRoom(player);
            monster3.moveTowards(player);
        }

        // Combat Mechanic: Player Attacks Monsters
        if (key == KeyEvent.VK_SPACE) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastAttackTime >= attackCooldown) {
                attackMonsters();
                lastAttackTime = currentTime;
            }
        }

        // Monster Damage to Player
        if (monster1.checkCollision(player) || monster2.checkCollision(player) || monster3.checkCollision(player)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastDamageTime >= damageCooldown) {
                playerHealth -= 10;
                lastDamageTime = currentTime;
            }

            if (playerHealth <= 0) {
                JOptionPane.showMessageDialog(this, "Game Over! The Monsters got you!");
                System.exit(0);
            }
        }

        // If player reaches stairs, they WIN
        if (stairs.checkCollision(player.getX(), player.getY())) {
            JOptionPane.showMessageDialog(this, "Congratulations! You have completed all levels!");
            System.exit(0);
        }
    }

    private void attackMonsters() {
        if (monster1.isAlive() && monster1.checkCollision(player)) {
            monster1.takeDamage(20);
            if (!monster1.isAlive()) {
                playerScore += 100;
                JOptionPane.showMessageDialog(this, "You defeated a monster!");
            }
        }
        if (monster2.isAlive() && monster2.checkCollision(player)) {
            monster2.takeDamage(20);
            if (!monster2.isAlive()) {
                playerScore += 100;
                JOptionPane.showMessageDialog(this, "You defeated a monster!");
            }
        }
        if (monster3.isAlive() && monster3.checkCollision(player)) {
            monster3.takeDamage(20);
            if (!monster3.isAlive()) {
                playerScore += 100;
                JOptionPane.showMessageDialog(this, "You defeated a monster!");
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    private boolean checkRoom1() {
        return player.getX() > 100 && player.getX() < 300 && player.getY() > 150 && player.getY() < 300;
    }

    private boolean checkRoom2() {
        return player.getX() > 400 && player.getX() < 600 && player.getY() > 100 && player.getY() < 250;
    }

    private boolean checkRoom3() {
        return player.getX() > 100 && player.getX() < 300 && player.getY() > 350 && player.getY() < 450;
    }
}
