package User_Interface;

import javax.swing.*;

public class FRAME_main extends JFrame {
    private int WIDTH = 1000;
    private int HEIGHT = 600;
    public FRAME_main() {
        super("Main");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
