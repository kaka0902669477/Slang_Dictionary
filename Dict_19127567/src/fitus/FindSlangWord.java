package fitus;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class FindSlangWord extends JFrame implements ActionListener, ListSelectionListener {
    DefaultTableModel mod;

    private JLabel labbel_find_slangw;
    private JLabel label_enter_slangw;
    private JTextField textf_enter_slangw;
    private JButton button_enter_slangw;
    private JButton button_back;
    private JComboBox cbox_find_slangw;
    private JPanel panel;
    private JTable table_find_slangw;
    private JLabel label_delete;

    private Handle slang = new Handle();

    public FindSlangWord(String title) throws IOException {
        super(title);

        String column[] = {"Slang word", "Nghĩa" };
        table_find_slangw.setModel(new DefaultTableModel(column, 0));
        mod = (DefaultTableModel) table_find_slangw.getModel();

        ListSelectionModel selection = table_find_slangw.getSelectionModel();
        selection.addListSelectionListener(this);

        button_back.addActionListener(this);
        button_enter_slangw.addActionListener(this);

        this.setContentPane(panel);
        this.pack();
    }

    void clearTable() {
        int rowCount = mod.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            mod.removeRow(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_enter_slangw) {
            this.clearTable();

            Vector<String[]> str = new Vector<String[]>();
            if (cbox_find_slangw.getSelectedItem().equals("Slang word")) {
                str = slang.SearchbySlang(textf_enter_slangw.getText());
            } else {
                str = slang.SearchbyMeaning(textf_enter_slangw.getText());
            }

            for (String[] s : str) {
                mod.addRow(s);
            }

            slang.WriteHistory(str);
        } else {
            this.dispose();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int row = table_find_slangw.getSelectedRow();
            int col = table_find_slangw.getSelectedColumn();
            if (row == -1 || col == -1)
                return;

            int n = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa slang word này?", "Question", JOptionPane.YES_NO_OPTION);

            if (n == 1) {
                return;
            }

            slang.delete((String) table_find_slangw.getValueAt(row, 0));
            JOptionPane.showMessageDialog(this, "Xóa thành công!","Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
