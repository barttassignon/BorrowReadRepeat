/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BeheerderDAO;
import db.LezerDAO;
import entity.Adres;
import entity.Beheerder;
import entity.Lezer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                String dag = dagTextField.getText();
                String maand = maandTextfield.getText();
                String jaar = jaarTextfield.getText();
                LocalDate geboortedatum = LocalDate.parse(jaar + "-" + maand + "-" + dag);
                String straatnaam = straatTextField.getText();
                int nummer = Integer.parseInt(nummerTextField.getText());
                String bus = busTextField.getText();
                int postcode = Integer.parseInt(postcodeTextField.getText());
                String woonplaats = woonplaatsTextField.getText();
                String email = emailtextField.getText();
                String telefoon = telefoonTextField.getText();

                String paswoord = String.valueOf(passwordField.getPassword());
                String bevestigWachtwoord = String.valueOf(bevestigPaswoord.getPassword());

                /* Problemen:
                - Er zit 1 dag verschil tussen de geboortedatum die ingetikt wordt in de GUI en tussen degene die opgeslagen wordt in de DB?!
                - Indien leeftijd < 12 jaar => in Java krijgt men de melding dat de lezer werd toegevoegd (NOK), maar in werkelijkheid is dit niet het geval (OK) => catch exception?
                - Indien er een 2de lezer met identiek mailadres wordt toegevoegd => in Java melding dat lezer toegevoegd (NOK), maar in werkelijkheid niet het geval (OK) => catch exception?
                - In GUI intikken: dag en maand met 2 cijfers => anders krijgt men een Exception (vb. 8-6-1986 mag niet, moet 08-06-1986 zijn) => opvangen?
                 */

                if(paswoord.length() == 0){
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Geen wachtwoord ingevuld!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                } else{
                if (paswoord.equals(bevestigWachtwoord)) {
                    LezerDAO.toevoegenLezer(new Lezer(voornaam, naam, geboortedatum, email, telefoon, paswoord, new Adres(straatnaam, nummer, bus, postcode, woonplaats)));
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Lezer toegevoegd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(lezerToevoegenFrame, "Uw wachtwoord komt niet overeen", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    bevestigPaswoord.setText("");
                }
            }}

    });
}

    public static void main(String[] args) { new LezerToevoegenForm(); }
}


