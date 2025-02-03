package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window {

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Rogue");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(800, 500));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        frame.getContentPane().setBackground(Color.black);
        frame.pack();
        frame.setVisible(true);
    }


}
