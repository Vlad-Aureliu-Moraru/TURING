package User_Interface;

import Logic.Connecter;
import Logic.MachineLogic.Band;
import Logic.MachineLogic.Instruction;

import javax.swing.*;
import java.util.ArrayList;

public class FRAME_main extends JFrame {
    private int WIDTH = 1000;
    private int HEIGHT = 600;
    //?ADDONS
    private int index = 0;
    private Connecter connecter;
    private Timer timer;
    private INPUT_toCheck input_toCheck = new INPUT_toCheck();
    private PANEL_tape tape;
    private PANEL_table table ;
    private JButton button = new JButton("START");
    private boolean running = false;
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
            if (!running) {
                running = true;
                String inputText = input_toCheck.getText();
                ArrayList<String> Alphabet = connecter.getMachine().getAlphabet();
                char[] input = inputText.toCharArray();
                for (char c : input) {
                    if (!Alphabet.contains(String.valueOf(c))) {
                        JOptionPane.showMessageDialog(null, "Inputul Contine Caractere Ce Nu Sunt in Alfabet", "Error", JOptionPane.ERROR_MESSAGE);
                        input_toCheck.setText("");
                        running = false;
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
                System.out.println(connecter.getInstructions());
                initTimer(connecter.getInstructions());
            }
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

    private void initTimer(ArrayList<Instruction> instructions){
        index = 0;
        timer = new Timer(1000, e -> {
            if(index<instructions.size()){
                System.out.println(instructions.get(index));
                String newText = instructions.get(index).getTape();
                newText = newText.substring(1,newText.length()-1);
                Band newBand = new Band();
                newBand.setToCheck(newText);
                int newHead = instructions.get(index).getHeadPosition();
                int newTransitionIndex = instructions.get(index).getFoundTransitionIndex();
                tape.updateTape(newBand);
                tape.highlightHead(newHead);
                table.highLightRow(newTransitionIndex);
                index++;
            }else{
                if (connecter.isAccepted()){
                    JOptionPane.showMessageDialog(null, "Limbajul a fost acceptat", "Success", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Limbajul nu a fost acceptat", "Error", JOptionPane.ERROR_MESSAGE);
                }
                connecter.resetMachine();
                running = false;
                timer.stop();

            }
        });
        timer.start();
    }
}
