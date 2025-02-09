import UI.*;
import Levels.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Launching Rogue game...");
        Window w = new Window();
        Level1 l1 = new Level1();

        w.add(l1);
        w.pack();
        w.setVisible(true);

        System.out.println("Level1 panel added successfully.");
    }
}
