package User_Interface;

import Logic.Connecter;
import Logic.MachineLogic.Band;

import javax.swing.*;
import java.util.ArrayList;

public class FRAME_main extends JFrame {
    private int WIDTH = 1000;
    private int HEIGHT = 600;
    //?ADDONS
    private Connecter connecter;

    private INPUT_toCheck input_toCheck = new INPUT_toCheck();
    private PANEL_tape tape;
    private PANEL_table table ;
    private JButton button = new JButton("START");
    public FRAME_main() {
        super("Main");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        //?ADDONS
        connecter = new Connecter();
        connecter.readMachine();
        table = new PANEL_table(connecter.getMachine().getStates());

        tape = new PANEL_tape(connecter.getBand());

        button.addActionListener(e -> {
            String inputText = input_toCheck.getText();
            ArrayList<String> Alphabet = connecter.getMachine().getAlphabet();
            char[] input = inputText.toCharArray();
            for (char c : input) {
                if (!Alphabet.contains(String.valueOf(c))) {
                    JOptionPane.showMessageDialog(null, "Inputul Contine Caractere Ce Nu Sunt in Alfabet", "Error", JOptionPane.ERROR_MESSAGE);                    input_toCheck.setText("");
                    return;
                }
            }
          connecter.getBand().setToCheck(inputText);
          tape.updateTape(connecter.getBand());
          tape.highlightHead(connecter.getBand().getHead());
          table.highLightRow(3);
          table.highLightRow(0);
          table.repaint();
          connecter.Touring();
        });

        //?CONFIG
        table.setBounds(20,20,WIDTH-40,HEIGHT/2);
        add(table);
        tape.setBounds(20,HEIGHT-120,WIDTH-40,100);
        add(tape);
        input_toCheck.setBounds(20,HEIGHT-200,WIDTH-300,30);
        add(input_toCheck);
        button.setBounds(WIDTH-200,HEIGHT-200,100,30);
        add(button);

        this.revalidate();
        this.repaint();
    }
}
