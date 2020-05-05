/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LezerToevoegenForm extends JFrame {

    private JFrame lezerToevoegenFrame = new JFrame("BorrowReadRepeat");
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField testTextField;
    private JTextField textField4;
    private JTextField textField6;
    private JTextField textField7;
    private JButton ToevoegenButton;
    private JButton TerugButton;
    private JLabel VoornaamLabel;
    private JLabel NaamLabel;
    private JLabel AdresLabel;
    private JLabel StraatLabel;
    private JTextField NummerLabel;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JTextField textField5;

    public LezerToevoegenForm() {
        lezerToevoegenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lezerToevoegenFrame.getContentPane().add(panel1);
        lezerToevoegenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lezerToevoegenFrame.setVisible(true);
        lezerToevoegenFrame.setSize(600, 600);
        lezerToevoegenFrame.setMinimumSize(new Dimension(600, 600));
        lezerToevoegenFrame.setMaximumSize(new Dimension(600, 600));
        lezerToevoegenFrame.setLocationRelativeTo(null);
        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                lezerToevoegenFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new LezerToevoegenForm();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


