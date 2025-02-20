package Levels;

import Player.*;
import se459.agile.Player.Player;

import java.awt.*;
import java.awt.event.*;
import java.time.Instant;

import javax.swing.*;




public class Level1 extends JPanel implements KeyListener {


    private Player player;
    private int playerHealth = 100; // Player health
    private int playerScore = 0;    // Score
    private Instant startTime;      // Start time for tracking duration
    private String playerName; 	    //Assign random player name

    public Level1() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 500));
        player = new Player(150, 150); // Start player at (50,50)
        addKeyListener(this);
        setFocusable(true); // Ensures the panel listens for key events
        this.startTime = Instant.now();

	//Random Player Name
        String[] names = {"Rogue", "Warrior", "Knight", "Shadow", "Hunter"};
        playerName = names[(int) (Math.random() * names.length)];
    }


    @Override
	protected void paintComponent(Graphics g) {
        System.out.println("x = " + player.getX());
        System.out.println("y = " + player.getY());
        g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 500);
        g.setColor(Color.WHITE);
        for (int i = 1; i < 3; i++){
            g.drawString("-------------------||--------------------",i*100,(i*200)-100);
            g.drawString("-------------------||--------------------",i*100,i*200);
            for (int j = 1; j < 6; j++){
                g.drawString("|", (i*100), ((i*100 + ((i-1)*100)) + (j*20)) - 10);
                g.drawString("|", (i*100)+160, ((i*100 + ((i-1)*100)) + (j*20)) - 10);
            }

        }
        g.setColor(Color.GREEN);
        for (int i = 1; i < 6; i++){
            g.drawString("#", 175, 200+(i*10));
        }
        for (int i = 1; i < 11; i++){
            g.drawString("#",175+(i*10),250);
        }
        for (int i = 1; i < 5; i++){
            g.drawString("#",275,250+(i*10));
        }

        player.draw(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 460, 800, 40);  // Background bar for stats
        g.setColor(Color.BLACK);
        g.drawString("Player: " + playerName, 20, 480);
        g.drawString("Health: " + playerHealth, 200, 480);
        g.drawString("Score: " + playerScore, 350, 480);
        long timeElapsed = Duration.between(startTime, Instant.now()).toSeconds();
        g.drawString("Time: " + timeElapsed + "s", 500, 480);
    }


    @Override
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int dx = 0, dy = 0;

      if (checkRoom2()){
         if (player.getX() == 190){
            if (key == KeyEvent.VK_RIGHT) { dx = 1; }
            if (key == KeyEvent.VK_UP) { dy = -1; }
            if (key == KeyEvent.VK_DOWN) { dy = 1; }
        }
        else if (player.getX() == 270 && player.getY() == 290){
            if (key == KeyEvent.VK_UP) { dy = -1; }
            if (key == KeyEvent.VK_DOWN) { dy = 1; }
        }
        else if (player.getX() == 350){
            if (key == KeyEvent.VK_LEFT) { dx = -1; }
            if (key == KeyEvent.VK_UP) { dy = -1; }
            if (key == KeyEvent.VK_DOWN) { dy = 1; }
        }
        else if (player.getY() == 290){
            if (key == KeyEvent.VK_LEFT) { dx = -1; }
            if (key == KeyEvent.VK_RIGHT) { dx = 1; }
            if (key == KeyEvent.VK_DOWN) { dy = 1; }
        }
        else if (player.getY() == 390){
            if (key == KeyEvent.VK_LEFT) { dx = -1; }
            if (key == KeyEvent.VK_RIGHT) { dx = 1; }
            if (key == KeyEvent.VK_UP) { dy = -1; }
        }
        else {
            if (key == KeyEvent.VK_LEFT) { dx = -1; }
            if (key == KeyEvent.VK_RIGHT) { dx = 1; }
            if (key == KeyEvent.VK_UP) { dy = -1; }
            if (key == KeyEvent.VK_DOWN) { dy = 1; }
        }
        
        }
       else if (checkRoom1()){
                if (key == KeyEvent.VK_LEFT) { dx = -1; }
                if (key == KeyEvent.VK_RIGHT) { dx = 1; }
                if (key == KeyEvent.VK_UP) { dy = -1; }
                if (key == KeyEvent.VK_DOWN) { dy = 1; }
            
        }
        else {
         if (player.getX() == 270 && player.getY() >= 230){
            if (player.getY() == 230){
                if (key == KeyEvent.VK_LEFT) { dx = -1; }
                if (key == KeyEvent.VK_DOWN) { dy = 1; }
            }
            else {
                if (key == KeyEvent.VK_UP) { dy = -1; }
                if (key == KeyEvent.VK_DOWN) { dy = 1; }
            }
        }
          else if (player.getX() == 170 && player.getY() >= 190 && player.getY() < 230){
                if (key == KeyEvent.VK_UP) { dy = -1; }
                if (key == KeyEvent.VK_DOWN) { dy = 1; }
            }
            else if (player.getX() >= 170 && player.getY() == 230 ){
                if (player.getX() > 170) {
                    if (key == KeyEvent.VK_LEFT) { dx = -1; }
                    if (key == KeyEvent.VK_RIGHT) { dx = 1; }
                }
                else {
                    if (key == KeyEvent.VK_UP) { dy = -1; }
                    if (key == KeyEvent.VK_RIGHT) { dx = 1; }
                }
            }
            else if (player.getX() == 90){
                if (key == KeyEvent.VK_RIGHT) { dx = 1; }
                if (key == KeyEvent.VK_UP) { dy = -1; }
                if (key == KeyEvent.VK_DOWN) { dy = 1; }
            }
            else if (player.getX() == 250){
                if (key == KeyEvent.VK_LEFT) { dx = -1; }
                if (key == KeyEvent.VK_UP) { dy = -1; }
                if (key == KeyEvent.VK_DOWN) { dy = 1; }
            }
            else if (player.getY() == 90){
                if (key == KeyEvent.VK_LEFT) { dx = -1; }
                if (key == KeyEvent.VK_RIGHT) { dx = 1; }
                if (key == KeyEvent.VK_DOWN) { dy = 1; }
            }
            else if (player.getY() == 190){
                if (key == KeyEvent.VK_LEFT) { dx = -1; }
                if (key == KeyEvent.VK_RIGHT) { dx = 1; }
                if (key == KeyEvent.VK_UP) { dy = -1; }
            }
          

        }


        player.move(dx, dy);
        repaint(); // Refresh screen after movement
        playerScore += 1;
    }
    @Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

    public boolean checkRoom1(){
        if (player.getX() > 90 && player.getX() < 250 && player.getY() > 90 && player.getY() < 190 ){
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean checkRoom2(){
        if (player.getX() >= 190 && player.getX() <= 350 && player.getY() >= 290 && player.getY() <= 390 ){
            return true;
        }
        else {
            return false;
        }
    }
}
