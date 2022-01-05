package fitus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class HistorySlangWord extends JFrame implements ActionListener {
    DefaultTableModel mod;

    private JLabel labbel_history;
    private JButton button_back;
    private JPanel panel;
    private JTable table_history;

    public HistorySlangWord(String title) {
        super(title);

        String column[] = {"Slang word", "Nghĩa","Thời gian" };
        table_history.setModel(new DefaultTableModel(column, 0));
        mod = (DefaultTableModel) table_history.getModel();

        Vector<String[]> str = Handle.getHistory();
        for (String[] s : str) {
            mod.addRow(s);
        }

        button_back.addActionListener(this);

        this.setContentPane(panel);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
