/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BeheerderDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeheerderToevoegenForm extends JFrame {
    private JFrame beheerderToevoegenForm = new JFrame("BorrowReadRepeat");


    private JLabel BoekToevoegenLabel;
    private JLabel ISBNLabel;
    private JTextField ISBNtextField;
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton ToevoegentButton;
    private JButton TerugButton;
    private JLabel GebruikersnaamTextField;

    public BeheerderToevoegenForm() {

        beheerderToevoegenForm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        beheerderToevoegenForm.getContentPane().add(panel1);
        beheerderToevoegenForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        beheerderToevoegenForm.setVisible(true);
        beheerderToevoegenForm.setSize(600, 600);
        beheerderToevoegenForm.setResizable(false);
        beheerderToevoegenForm.setLocationRelativeTo(null);

        TerugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                beheerderToevoegenForm.dispose();
            }
        });
        ToevoegentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public static void main(String[] args) {
        new BeheerderToevoegenForm();
    }
}


