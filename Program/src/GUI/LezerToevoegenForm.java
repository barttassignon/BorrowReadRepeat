/**
 * @Author Bart Tassignon
 */

package GUI;

import db.LezerDAO;
import entity.Adres;
import entity.Lezer;
import entity.LezerTeJong;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class LezerToevoegenForm extends JFrame {

    private JFrame lezerToevoegenFrame = new JFrame("BorrowReadRepeat");
    private JPanel panel1;

    private JLabel titelLabel;
    private JLabel voornaamLabel;
    private JLabel naamLabel;
    private JLabel geboortedatumLabel;
    private JLabel dagLabel;
    private JLabel maandLabel;
    private JLabel jaarLabel;
    private JLabel adresLabel;
    private JLabel straatLabel;
    private JLabel nummerLabel;
    private JLabel busLabel;
    private JLabel postcodeLabel;
    private JLabel woonplaatsLabel;
    private JLabel emailLabel;
    private JLabel telefoonLabel;
    private JLabel paswoordLabel;
    private JLabel bevestigPaswoordLabel;

    private JTextField voornaamTextField;
    private JTextField naamTextField;
    private JTextField dagTextField;
    private JTextField maandTextfield;
    private JTextField jaarTextfield;
    private JTextField straatTextField;
    private JTextField nummerTextField;
    private JTextField busTextField;
    private JTextField postcodeTextField;
    private JTextField woonplaatsTextField;
    private JTextField emailtextField;
    private JTextField telefoonTextField;
    private JPasswordField passwordField;
    private JPasswordField bevestigPaswoord;

    private JButton toevoegenButton;
    private JButton terugButton;

    public LezerToevoegenForm() {

        passwordField.setEchoChar('*');
        bevestigPaswoord.setEchoChar('*');

        lezerToevoegenFrame.getContentPane().add(panel1);
        lezerToevoegenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lezerToevoegenFrame.setVisible(true);
        lezerToevoegenFrame.setSize(600, 600);
        lezerToevoegenFrame.setResizable(false);
        lezerToevoegenFrame.setLocationRelativeTo(null);

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                lezerToevoegenFrame.dispose();
            }
        });

        toevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try{
                    String voornaam = voornaamTextField.getText();
                    String naam = naamTextField.getText();
                    int dag = Integer.parseInt(dagTextField.getText());
                    int maand = Integer.parseInt(maandTextfield.getText());
                    int jaar = Integer.parseInt(jaarTextfield.getText());
                    LocalDate geboortedatum = LocalDate.of(jaar, maand, dag);
                    String straatnaam = straatTextField.getText();
                    int nummer = Integer.parseInt(nummerTextField.getText());
                    String bus = busTextField.getText();
                    int postcode = Integer.parseInt(postcodeTextField.getText());
                    String woonplaats = woonplaatsTextField.getText();
                    String email = emailtextField.getText();
                    String telefoon = telefoonTextField.getText();
                    String paswoord = String.valueOf(passwordField.getPassword());
                    String bevestigWachtwoord = String.valueOf(bevestigPaswoord.getPassword());

                    if (voornaam.length() == 0 || naam.length() == 0 || straatnaam.length() == 0 || woonplaats.length() == 0 || email.length() == 0 || telefoon.length() == 0) {
                        JOptionPane.showMessageDialog(lezerToevoegenFrame, "Gelieve alle velden in te vullen!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    } else if (postcode < 1000 || postcode > 9999) {
                        JOptionPane.showMessageDialog(lezerToevoegenFrame, "Postcode moet uit 4 cijfers bestaan!");
                    } else if (jaar < 1900 || jaar > 2050) {
                        JOptionPane.showMessageDialog(lezerToevoegenFrame, "Gelieve een geldig jaartal in te geven!");
                    } else if (paswoord.length() < 4) {
                        JOptionPane.showMessageDialog(lezerToevoegenFrame, "Wachtwoord moet minimum 4 tekens bevatten!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    } else if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                        JOptionPane.showMessageDialog(lezerToevoegenFrame, "Gelieve een geldig e-mailadres in te voeren!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    } else if (!telefoon.matches("^0[1-9]\\d{7,8}$")) {
                        JOptionPane.showMessageDialog(lezerToevoegenFrame, "Gelieve een geldig telefoonnummer in te geven!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (paswoord.equals(bevestigWachtwoord)) {
                            try {
                                LezerDAO.toevoegenLezer(new Lezer(voornaam, naam, geboortedatum, email, telefoon, paswoord, new Adres(straatnaam, nummer, bus, postcode, woonplaats)));
                                JOptionPane.showMessageDialog(lezerToevoegenFrame, "Lezer toegevoegd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                            } catch (LezerTeJong lezerTeJong) {
                                JOptionPane.showMessageDialog(lezerToevoegenFrame, "Lezer te jong!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                            } catch (SQLIntegrityConstraintViolationException dubbel) {
                                JOptionPane.showMessageDialog(lezerToevoegenFrame, "Er bestaat reeds een lezer met dit emailadres!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                                emailtextField.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(lezerToevoegenFrame, "Uw wachtwoord komt niet overeen", "Resultaat", JOptionPane.ERROR_MESSAGE);
                            passwordField.setText("");
                            bevestigPaswoord.setText("");
                        }
                    }
                } catch(NumberFormatException nr){
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Gelieve (enkel) cijfers in te geven bij dag, maand, jaar, huisnummer en postcode!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                } catch(DateTimeException dte){
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Ongeldige datum. Gelieve een geldige datum in te geven!");
                }
            }

    });
}

    public static void main(String[] args) { new LezerToevoegenForm(); }
}


