/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LezerVerwijderenForm extends JFrame{

    private JPanel panel1;
    private JTextField textField1;
    private JButton TerugButton;
    private JButton VerwijderLezerButton;
    private JFrame lezerVerwijderenFrame = new JFrame("BorrowReadRepeat");

    public LezerVerwijderenForm() {

        lezerVerwijderenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lezerVerwijderenFrame.getContentPane().add(panel1);
        lezerVerwijderenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lezerVerwijderenFrame.setVisible(true);
        lezerVerwijderenFrame.setSize(600, 600);
        lezerVerwijderenFrame.setMinimumSize(new Dimension(600, 600));
        lezerVerwijderenFrame.setMaximumSize(new Dimension(600, 600));
        lezerVerwijderenFrame.setLocationRelativeTo(null);

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                lezerVerwijderenFrame.dispose();
            }
        });

        VerwijderLezerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(LezerVerwijderenForm.this, "Ben je zeker dat je deze lezer wilt verwijderen?", "Verwijderen", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                }
            }
        });
    }

    public static void main(String[] args) {
        new LezerVerwijderenForm();
    }
}


