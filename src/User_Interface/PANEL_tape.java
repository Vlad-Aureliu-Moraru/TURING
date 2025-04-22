package User_Interface;

import Logic.MachineLogic.Band;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class PANEL_tape extends JPanel {
    //?ADOONS
    private Object data[][];
    private Band band;
    private String[] columnNames;
    private JTable table;
    //?FORMATS
    private DefaultTableCellRenderer defaultFormat= new DefaultTableCellRenderer();
    private DefaultTableCellRenderer highlightFormat= new DefaultTableCellRenderer();
    //?COLORS
    private Color highlitght= new Color(41, 71, 151);
    private TableColumn column;
    public PANEL_tape(Band band) {
        this.band = band;
        setLayout(new BorderLayout());
        setBackground(Color.white);
        int tapeLength = band.getToCheckArray().length;
        columnNames = new String[tapeLength];
        for (int i = 0; i < tapeLength; i++) {
            columnNames[i] = String.valueOf(i); // Index as column header
        }
        initFormats();
        createTable();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table = new JTable(model);
        table.setFont(new Font("Monospaced", Font.BOLD, 20));
        table.setDefaultRenderer(Object.class,defaultFormat);
        table.getTableHeader().setFont(new Font("Monospaced", Font.PLAIN, 12));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(78);
        table.setShowGrid(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createTable() {
        data = new Object[1][band.getToCheckArray().length];
        for (int i = 0; i < band.getToCheckArray().length; i++) {
            data[0][i] = band.getToCheckArray()[i];
        }


    }
    public void updateTape(Band newBand) {
        if (newBand != null && newBand.getToCheckArray() != null) {
            this.band = newBand;
            int tapeLength = band.getToCheckArray().length;
            columnNames = new String[tapeLength];
            for (int i = 0; i < tapeLength; i++) {
                columnNames[i] = String.valueOf(i); // Index as column header
            }
            createTable();
            DefaultTableModel model = (DefaultTableModel) ((JTable) ((JScrollPane) getComponent(0)).getViewport().getView()).getModel();
            model.setDataVector(data, columnNames);
            model.fireTableDataChanged();
        }
    }
    private void initFormats(){
        //?DEFAULT
        defaultFormat.setHorizontalAlignment(JLabel.CENTER); // Center text
        //?HIGHLIGHT
        highlightFormat.setBackground(highlitght);
        highlightFormat.setForeground(Color.white);
        highlightFormat.setHorizontalAlignment(JLabel.CENTER); // Center text
    }
    public void highlightHead(int rowIndex){
        column = table.getColumnModel().getColumn(rowIndex);
        column.setCellRenderer(highlightFormat);
    }

}
