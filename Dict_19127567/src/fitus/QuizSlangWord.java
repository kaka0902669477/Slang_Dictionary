package fitus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class QuizSlangWord extends JFrame implements ActionListener {

    private JLabel label_slangw;
    private JLabel label_quiz;
    private JButton button_a;
    private JButton button_c;
    private JButton button_b;
    private JButton button_d;
    private JButton button_back;
    private JPanel panel;

    private String answer;
    Handle slang = new Handle();

    public QuizSlangWord(String title) throws IOException {
        super(title);

        int rand = (0 + (int) (Math.random() * 3));
        String[][] quiz = slang.quiz();
        answer = quiz[rand][1];

        label_quiz.setText("\"" + quiz[rand][0] + "\" nghĩa là gì?");

        button_a.setText(quiz[0][1]);
        button_b.setText(quiz[1][1]);
        button_c.setText(quiz[2][1]);
        button_d.setText(quiz[3][1]);

        button_a.addActionListener(this);
        button_b.addActionListener(this);
        button_c.addActionListener(this);
        button_d.addActionListener(this);
        button_back.addActionListener(this);

        this.setContentPane(panel);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_a) {
            if (button_a.getText().equals(answer)) {
                JOptionPane.showMessageDialog(this, "Chính xác!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sai! Đáp án chính xác là " + answer, "Thông báo",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == button_b) {
            if (button_b.getText().equals(answer)) {
                JOptionPane.showMessageDialog(this, "Chính xác!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sai! Đáp án chính xác là " + answer, "Thông báo",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == button_c) {
            if (button_c.getText().equals(answer)) {
                JOptionPane.showMessageDialog(this, "Chính xác!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sai! Đáp án chính xác là " + answer, "Thông báo",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == button_d) {
            if (button_d.getText().equals(answer)) {
                JOptionPane.showMessageDialog(this, "Chính xác!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sai! Đáp án chính xác là " + answer, "Thông báo",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        this.dispose();
    }
}
