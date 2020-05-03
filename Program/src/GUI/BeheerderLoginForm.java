/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BeheerderDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeheerderLoginForm {

    private JPanel BeheerderLoginPanel;
    private JTextField userNaam;
    private JPasswordField passwordField;
    private JButton Inloggen;
    private JFrame frame = new JFrame("BorrowReadRepeat");

    public BeheerderLoginForm() {

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(BeheerderLoginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Inloggen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gebruikersnaam = userNaam.getText();
                String paswoord = String.valueOf(passwordField.getPassword());
                if(BeheerderDAO.inloggen(gebruikersnaam, paswoord)){
                    JOptionPane.showMessageDialog(frame, "Je bent ingelogd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(frame, "Verkeerde logingegevens", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    userNaam.setText("");
                    passwordField.setText("");
                }
            }
        });

        frame.setVisible(true);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new BeheerderLoginForm();
    }
}


