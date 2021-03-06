/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BeheerderDAO;
import entity.Beheerder;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeheerderToevoegenForm extends JFrame{

    private JFrame beheerderToevoegenForm = new JFrame("BorrowReadRepeat");
    private JPanel panel1;

    private JLabel boekToevoegenLabel;
    private JLabel voornaamLabel;
    private JLabel naamLabel;
    private JLabel gebruikersnaamTextField;
    private JLabel paswoordLabel;
    private JLabel bevestigPwLabel;

    private JTextField voornaamField;
    private JTextField naamField;
    private JTextField gebruikersnaamField;
    private JPasswordField passwordField1;
    private JPasswordField bevestigPasswordField1;

    private JButton toevoegentButton;
    private JButton terugButton;

    public BeheerderToevoegenForm() {

        passwordField1.setEchoChar('*');
        bevestigPasswordField1.setEchoChar('*');

        beheerderToevoegenForm.getContentPane().add(panel1);
        beheerderToevoegenForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        beheerderToevoegenForm.setVisible(true);
        beheerderToevoegenForm.setSize(600, 600);
        beheerderToevoegenForm.setResizable(false);
        beheerderToevoegenForm.setLocationRelativeTo(null);

        terugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                beheerderToevoegenForm.dispose();
            }
        });

        toevoegentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String voornaam = voornaamField.getText();
                String naam = naamField.getText();
                String gebruikersnaam = gebruikersnaamField.getText();
                String wachtwoord = String.valueOf(passwordField1.getPassword());
                String bevestigWachtwoord = String.valueOf(bevestigPasswordField1.getPassword());

                if (voornaam.length() == 0 || naam.length() == 0 || gebruikersnaam.length() == 0) {
                    JOptionPane.showMessageDialog(beheerderToevoegenForm, "Gelieve alle velden in te vullen!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                } else if (wachtwoord.length() < 4) {
                    JOptionPane.showMessageDialog(beheerderToevoegenForm, "Wachtwoord moet minimum 4 tekens bevatten!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                } else {
                if(wachtwoord.equals(bevestigWachtwoord)) {
                    if(BeheerderDAO.toevoegenBeheerder(new Beheerder(voornaam, naam, gebruikersnaam, wachtwoord)))
                    JOptionPane.showMessageDialog(beheerderToevoegenForm, "Beheerder toegevoegd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(beheerderToevoegenForm, "Beheerder kon niet worden toegevoegd. Gebruikersnaam bestaat al.", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    gebruikersnaamField.setText("");
                } else{
                    JOptionPane.showMessageDialog(beheerderToevoegenForm, "Uw wachtwoord komt niet overeen", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    passwordField1.setText("");
                    bevestigPasswordField1.setText("");
                }}
            }
        });
    }
}


