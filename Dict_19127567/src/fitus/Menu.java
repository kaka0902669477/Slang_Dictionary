package fitus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menu extends JFrame implements ActionListener {
    static JFrame frame;

    DefaultTableModel mod;

    private JLabel lb_menu;
    private JPanel panel;
    private JButton button_find_slangw;
    private JButton button_quiz_slangw;
    private JButton button_reset_slangw;
    private JButton button_add_slangw;
    private JButton button_history_slangw;
    private JButton button_random_slangw;
    private JButton button_quiz_definition;

    Handle slang = new Handle();

    public Menu(String title) throws IOException {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button_find_slangw.addActionListener(this);
        button_quiz_slangw.addActionListener(this);
        button_reset_slangw.addActionListener(this);
        button_add_slangw.addActionListener(this);
        button_history_slangw.addActionListener(this);
        button_random_slangw.addActionListener(this);
        button_quiz_definition.addActionListener(this);

        this.setContentPane(panel);
        this.pack();
    }

    public static void main(String[] arg){
        try {
            frame = new Menu("Slang Word");
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_find_slangw) {
            FindSlangWord find = null;
            try {
                find = new FindSlangWord("My App");
                find.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == button_quiz_slangw) {
            QuizSlangWord find = null;
            try {
                find = new QuizSlangWord("My App");
                find.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == button_reset_slangw) {
            slang.Reset();
            JOptionPane.showMessageDialog(this, "Đã cập nhật lại file!","Reset",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == button_add_slangw) {
            AddSlangWord find = null;
            try {
                find = new AddSlangWord("My App");
                find.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == button_history_slangw) {
            HistorySlangWord find = new HistorySlangWord("My App");
            find.setVisible(true);
        } else if (e.getSource() == button_quiz_definition) {
            QuizDefinition find = null;
            try {
                find = new QuizDefinition("My App");
                find.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == button_random_slangw) {
            JOptionPane.showMessageDialog(this, slang.RandomSlangWord(),"Random",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
