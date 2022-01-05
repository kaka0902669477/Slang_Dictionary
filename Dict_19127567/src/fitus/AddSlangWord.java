package fitus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddSlangWord extends JFrame implements ActionListener {
    private JLabel label_add_slangw;
    private JTextField textf_slangw;
    private JTextField textf_mean;
    private JLabel label_slangw;
    private JLabel label_mean;
    private JPanel panel;
    private JButton button_back;
    private JButton button_add;
    private JButton button_edit;

    Handle slang = new Handle();

    public AddSlangWord(String title) throws IOException {
        super(title);

        button_back.addActionListener(this);
        button_add.addActionListener(this);
        button_edit.addActionListener(this);

        this.setContentPane(panel);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_add) {
            if (textf_mean.getText().equals("") || textf_slangw.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Không được thêm giá trị trống!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (slang.checkExisted(textf_slangw.getText())) {
                int n = JOptionPane.showConfirmDialog(this, "Slang word đã tồn tại, bạn có muốn ghi đè lên nó không?",
                        "Ouestion", JOptionPane.YES_NO_OPTION);

                if (n == 0) {
                    slang.add(textf_slangw.getText(), textf_mean.getText());
                } else {
                    slang.duplicate(textf_slangw.getText(), textf_mean.getText());
                }
            } else {
                slang.add(textf_slangw.getText(), textf_mean.getText());
            }

            JOptionPane.showMessageDialog(this, "Đã thêm!", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else if (e.getSource() == button_edit) {
            if (textf_mean.getText().equals("") || textf_slangw.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Không được để trống giá trị!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!slang.checkExisted(textf_slangw.getText())) {
                JOptionPane.showMessageDialog(this, "Slang word không tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            slang.add(textf_slangw.getText(), textf_mean.getText());
            JOptionPane.showMessageDialog(this, "Đã chỉnh sửa!", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            this.dispose();
        }
    }
}
