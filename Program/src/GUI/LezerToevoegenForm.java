/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BeheerderDAO;
import db.LezerDAO;
import entity.Beheerder;
import entity.Lezer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LezerToevoegenForm extends JFrame {

    private JFrame lezerToevoegenFrame = new JFrame("BorrowReadRepeat");
    private JPanel panel1;
    private JTextField voornaamTextField;
    private JTextField naamTextField;
    private JTextField straatTextField;
    private JTextField woonplaatsTextField;
    private JTextField emailtextField;
    private JButton ToevoegenButton;
    private JButton TerugButton;
    private JLabel VoornaamLabel;
    private JLabel NaamLabel;
    private JLabel AdresLabel;
    private JLabel StraatLabel;
    private JTextField nummerTextField;
    private JTextField postcodeTextField;
    private JPasswordField passwordField;
    private JTextField geboortedatumTextField;
    private JLabel GeboortedatumLabel;
    private JLabel WoonplaatsLabel;
    private JLabel NrLabel;
    private JLabel PostcodeLabel;
    private JLabel EmailLabel;
    private JLabel PaswoordLabel;
    private JLabel BusLabel;
    private JTextField busTextField;
    private JLabel TelefoonLabel;
    private JTextField telefoonTextField;
    private JPasswordField bevestigPaswoord;


    public LezerToevoegenForm() {

        passwordField.setEchoChar('*');
        bevestigPaswoord.setEchoChar('*');

        lezerToevoegenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lezerToevoegenFrame.getContentPane().add(panel1);
        lezerToevoegenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lezerToevoegenFrame.setVisible(true);
        lezerToevoegenFrame.setSize(600, 600);
        lezerToevoegenFrame.setResizable(false);
        lezerToevoegenFrame.setLocationRelativeTo(null);

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                lezerToevoegenFrame.dispose();
            }
        });
        ToevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String voornaam = voornaamTextField.getText();
                String naam = naamTextField.getText();
                String geboortedatum = geboortedatumTextField.getText();
                String straatnaam = straatTextField.getText();
                String nummer = nummerTextField.getText();
                String bus = busTextField.getText();
                String postcode = postcodeTextField.getText();
                String woonplaats = woonplaatsTextField.getText();
                String email = emailtextField.getText();
                String telefoon = telefoonTextField.getText();

                String paswoord = String.valueOf(passwordField.getPassword());
                String bevestigWachtwoord = String.valueOf(bevestigPaswoord.getPassword());


                if (paswoord.equals(bevestigWachtwoord)) {
                    LezerDAO.toevoegenLezer(new Lezer(voornaam, naam, geboortedatum, straatnaam, nummer, bus, postcode, woonplaats, email, telefoon, paswoord, bevestigWachtwoord));
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Lezer toegevoegd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Uw wachtwoord komt niet overeen", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    bevestigPaswoord.setText("");
                }
            }

    });
}

    public static void main(String[] args) {
        new LezerToevoegenForm();
    }
}


