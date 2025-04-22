package User_Interface;

import Logic.MachineLogic.StateMachine;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import java.awt.*;
import java.util.ArrayList;

public class PANEL_table extends JPanel {
    //?ADDONS
    private String[] columnNames = {"State","Symbol","Action","Next State"};
    private Object[][] data;
    private ArrayList<StateMachine> states;
    private JTable table;
    //?FORMAT

    public PANEL_table(ArrayList<StateMachine> states) {
        this.states = states;
        setLayout(new BorderLayout());
        setBackground(Color.white);
        createTable();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

    }
    private void createTable() {
        data = new Object[states.size()][4];
        for (int i = 0; i < states.size(); i++) {
            try {
                StateMachine state = states.get(i);
                data[i][0] = state.getCurrentState() != null ? state.getCurrentState() : "";
                data[i][1] = state.getInstructionToReplace() != null ? state.getInstructionToReplace() : "";
                data[i][2] = state.getInstructionMovement() != null ? state.getInstructionMovement() : "";
                data[i][3] = state.getNextState() != null ? state.getNextState() : "";
            } catch (Exception e) {
                System.err.println("Error processing state at index " + i + ": " + e.getMessage());
                data[i][0] = data[i][1] = data[i][2] = data[i][3] = "Error";
            }
        }
    }
    public void highLightRow(int rowIndex){
        table.setRowSelectionInterval(rowIndex, rowIndex);
        table.scrollRectToVisible(table.getCellRect(rowIndex, 0, true));
        table.changeSelection(rowIndex, 0, false, false);

        table.repaint();
        table.revalidate();
    }

}
