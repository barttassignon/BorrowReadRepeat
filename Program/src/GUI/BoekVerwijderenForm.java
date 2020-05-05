/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoekVerwijderenForm extends JFrame{
    private JPanel panel1;
    private JFrame boekVerwijderenFrame = new JFrame("BorrowReadRepeat");
    private JTextField textField1;
    private JButton VerwijderBoekButton;
    private JButton TerugButton;

    public BoekVerwijderenForm() {

        boekVerwijderenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        boekVerwijderenFrame.getContentPane().add(panel1);
        boekVerwijderenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boekVerwijderenFrame.setVisible(true);
        boekVerwijderenFrame.setResizable(false);
        boekVerwijderenFrame.setSize(600, 600);
        boekVerwijderenFrame.setLocationRelativeTo(null);

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            new BeheerderForm();
            boekVerwijderenFrame.dispose();
            }
        });
        VerwijderBoekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    int action = JOptionPane.showConfirmDialog(BoekVerwijderenForm.this, "Ben je zeker dat je dit boek wilt verwijderen?", "Verwijderen", JOptionPane.OK_CANCEL_OPTION);
                    if (action == JOptionPane.OK_OPTION) {
                    }
                }
        });
    }

    public static void main(String[] args) {
        new BoekVerwijderenForm();
    }
}



