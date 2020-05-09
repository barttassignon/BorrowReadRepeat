/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BeheerderDAO;
import db.LezerDAO;
import entity.Adres;
import entity.Beheerder;
import entity.Lezer;
import entity.LezerTeJong;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

public class LezerToevoegenForm extends JFrame {

    private JFrame lezerToevoegenFrame = new JFrame("BorrowReadRepeat");
    private JPanel panel1;

    private JLabel TitelLabel;
    private JLabel VoornaamLabel;
    private JLabel NaamLabel;
    private JLabel GeboortedatumLabel;
    private JLabel dagLabel;
    private JLabel maandLabel;
    private JLabel jaarLabel;
    private JLabel AdresLabel;
    private JLabel StraatLabel;
    private JLabel NrLabel;
    private JLabel BusLabel;
    private JLabel PostcodeLabel;
    private JLabel WoonplaatsLabel;
    private JLabel EmailLabel;
    private JLabel TelefoonLabel;
    private JLabel PaswoordLabel;
    private JLabel BevestigPaswoordLabel;

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

    private JButton ToevoegenButton;
    private JButton TerugButton;

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

                // Zorgt ervoor dat er enkel cijfers kunnen worden ingegeven:

                try{
                    int dag = Integer.parseInt(dagTextField.getText());
                    int maand = Integer.parseInt(maandTextfield.getText());
                    int jaar = Integer.parseInt(jaarTextfield.getText());
                    int nummer = Integer.parseInt(nummerTextField.getText());
                    int postcode = Integer.parseInt(postcodeTextField.getText());
                } catch(NumberFormatException nr){
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Gelieve (enkel) cijfers in te geven bij dag, maand, jaar, huisnummer en postcode!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }

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

                if(paswoord.length() < 4) {
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Wachtwoord moet mininmum 4 tekens bevatten!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                } else if (voornaam.length() == 0 || naam.length() == 0 || straatnaam.length() == 0 || woonplaats.length() == 0 || email.length() == 0 || telefoon.length() == 0) {
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Gelieve alle velden in te vullen!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                } else{
                if (paswoord.equals(bevestigWachtwoord)) {
                    try {
                        LezerDAO.toevoegenLezer(new Lezer(voornaam, naam, geboortedatum, email, telefoon, paswoord, new Adres(straatnaam, nummer, bus, postcode, woonplaats)));
                        JOptionPane.showMessageDialog(lezerToevoegenFrame, "Lezer toegevoegd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                    } catch (LezerTeJong lezerTeJong) {
                        JOptionPane.showMessageDialog(lezerToevoegenFrame, "Lezer te jong!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLIntegrityConstraintViolationException dubbel){
                        JOptionPane.showMessageDialog(lezerToevoegenFrame, "Er bestaat reeds een lezer met dit emailadres!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                        emailtextField.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Uw wachtwoord komt niet overeen", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    bevestigPaswoord.setText("");
                }
            }}

    });
}

     /* Op te lossen:
          - Jaar en postcode => verplichting toevoegen dat dit 4 cijfers moeten zijn
          - Vermijden dat er bij voornaam, naam, straat en woonplaats cijfers kan worden ingevuld.
     */

    public static void main(String[] args) { new LezerToevoegenForm(); }
}


