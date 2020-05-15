/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BeheerderDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeheerderLoginForm {

    private JFrame frame = new JFrame("BorrowReadRepeat");
    private JPanel BeheerderLoginPanel;

    private JLabel labelTitel;
    private JLabel labelHoofdtitel;
    private JLabel labelGebruikersnaam;
    private JLabel labelPaswoord;

    private JTextField userNaam;
    private JPasswordField passwordField;

    private JButton inloggen;

    public BeheerderLoginForm() {

        passwordField.setEchoChar('*');

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(BeheerderLoginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(600,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        inloggen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gebruikersnaam = userNaam.getText();
                String paswoord = String.valueOf(passwordField.getPassword());
                if(BeheerderDAO.inloggen(gebruikersnaam, paswoord)){
                    new BeheerderForm();
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(frame, "Verkeerde logingegevens", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    userNaam.setText("");
                    passwordField.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        new BeheerderLoginForm();
    }
}


