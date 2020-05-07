/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BeheerderDAO;
import entity.Beheerder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

public class BeheerderToevoegenForm extends JFrame{
    private JFrame beheerderToevoegenForm = new JFrame("BorrowReadRepeat");


    private JLabel BoekToevoegenLabel;
    private JLabel voornaam;
    private JTextField voornaamField;
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton ToevoegentButton;
    private JButton TerugButton;
    private JLabel gebruikersnaamTextField;
    private JLabel bevestigPwLabel;
    private JPasswordField bevestigPasswordField1;
    private JTextField naamField;
    private JTextField gebruikersnaamField;
    private JLabel naam;
    private JLabel paswoord;

    public BeheerderToevoegenForm() {

        passwordField1.setEchoChar('*');
        bevestigPasswordField1.setEchoChar('*');


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

        // Probleem: indien reeds een beheerder met zelfde gebruikersnaam in DB => beheerder wordt niet toegevoegd aan de DB (SQLIntegrityConstraintViolationException),
        // maar men krijgt wel een melding in Java dat het (onterecht) gelukt is => nog op te lossen door catch exception te integreren in code?

        ToevoegentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String voornaam = voornaamField.getText();
                String naam = naamField.getText();
                String gebruikersnaam = gebruikersnaamField.getText();
                String wachtwoord = String.valueOf(passwordField1.getPassword());
                String bevestigWachtwoord = String.valueOf(bevestigPasswordField1.getPassword());

                if(wachtwoord.length() == 0){
                    JOptionPane.showMessageDialog(beheerderToevoegenForm, "Geen wachtwoord ingevuld!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                } else{
                if(wachtwoord.equals(bevestigWachtwoord)) {
                    BeheerderDAO.toevoegenBeheerder(new Beheerder(voornaam, naam, gebruikersnaam, wachtwoord));
                    JOptionPane.showMessageDialog(beheerderToevoegenForm, "Beheerder toegevoegd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(beheerderToevoegenForm, "Uw wachtwoord komt niet overeen", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    passwordField1.setText("");
                    bevestigPasswordField1.setText("");
                }}
            }
        });
    }


    public static void main(String[] args) {
        new BeheerderToevoegenForm();
    }
}


