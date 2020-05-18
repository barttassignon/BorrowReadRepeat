/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BeheerderDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeheerderLoginForm extends JFrame{

    private JFrame beheerdersLoginFormFrame = new JFrame("BorrowReadRepeat");


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

        beheerdersLoginFormFrame.getContentPane().add(BeheerderLoginPanel);
        beheerdersLoginFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        beheerdersLoginFormFrame.setVisible(true);
        beheerdersLoginFormFrame.setSize(600,600);
        beheerdersLoginFormFrame.setResizable(false);
        beheerdersLoginFormFrame.setLocationRelativeTo(null);

        inloggen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gebruikersnaam = userNaam.getText();
                String paswoord = String.valueOf(passwordField.getPassword());
                if(BeheerderDAO.inloggen(gebruikersnaam, paswoord)){
                    new BeheerderForm();
                    beheerdersLoginFormFrame.dispose();
                }else{
                    JOptionPane.showMessageDialog(beheerdersLoginFormFrame,"Verkeerde logingegevens", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    userNaam.setText("");
                    passwordField.setText("");
                }
            }
        });
    }
}


