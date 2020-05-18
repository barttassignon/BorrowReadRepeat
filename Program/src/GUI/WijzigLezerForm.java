package GUI;

import db.LezerDAO;
import entity.Adres;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

public class WijzigLezerForm {

    private JFrame wijzigLezerFrame = new JFrame();
    private JPanel panel1;

    private JLabel lezerWijzigen;
    private JLabel lezerLabel;
    private JLabel adresLabel;
    private JLabel straatLabel;
    private JLabel nummerLabel;
    private JLabel busLabel;
    private JLabel postcodeLabel;
    private JLabel woonplaatsLabel;
    private JLabel emailLabel;
    private JLabel telefoonLabel;

    private JTextField lezerTextField;
    private JTextField straatTextfield;
    private JTextField nummerTextField;
    private JTextField busTextField;
    private JTextField postcodeTextField;
    private JTextField woonplaatsTextField;
    private JTextField emailtextField;
    private JTextField telefoonTextField;

    private JButton wijzigButton;
    private JButton terugButton;

    public WijzigLezerForm(){

        wijzigLezerFrame.getContentPane().add(panel1);
        wijzigLezerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        wijzigLezerFrame.setVisible(true);
        wijzigLezerFrame.setSize(600, 600);
        wijzigLezerFrame.setResizable(false);
        wijzigLezerFrame.setLocationRelativeTo(null);

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                wijzigLezerFrame.dispose();
            }
        });

        wijzigButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (lezerTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(wijzigLezerFrame, "Geef een lezerID in!");
                } else {
                    try {
                        int lezerID = Integer.parseInt(lezerTextField.getText());
                        String email = emailtextField.getText();
                        String telefoon = telefoonTextField.getText();
                        if(LezerDAO.bestaatLezer(lezerID) == 0){
                            JOptionPane.showMessageDialog(wijzigLezerFrame, "Er bestaat geen lezer met dit ID!");
                        }
                        else {
                            if (telefoon.matches("^0[1-9]\\d{7,8}$")){
                                LezerDAO.wijzigenTelLezer(lezerID, telefoon);
                                JOptionPane.showMessageDialog(wijzigLezerFrame, "Telefoon gewijzigd!");
                            } else if (telefoon.length() != 0) {
                                JOptionPane.showMessageDialog(wijzigLezerFrame, "Geef een geldig telefoonnummer in!");
                            }

                            if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
                                    LezerDAO.wijzigenEmailLezer(lezerID, email);
                                    JOptionPane.showMessageDialog(wijzigLezerFrame, "Email gewijzigd!");
                            } else if(email.length() != 0){
                                JOptionPane.showMessageDialog(wijzigLezerFrame, "Geef een geldig emailadres in!");
                            }

                            String straat = straatTextfield.getText();
                            String woonplaats = woonplaatsTextField.getText();
                            if (!straat.equals("") && !woonplaats.equals("")){
                                int nummer = Integer.parseInt(nummerTextField.getText());
                                String bus = busTextField.getText();
                                int postcode = Integer.parseInt(postcodeTextField.getText());
                                LezerDAO.wijzigenAdresLezer(lezerID, new Adres(straat, nummer, bus, postcode, woonplaats));
                                JOptionPane.showMessageDialog(wijzigLezerFrame, "Adres gewijzigd!");
                            }
                            if(telefoon.length() == 0 && email.length() == 0 && straat.equals("")){
                                JOptionPane.showMessageDialog(wijzigLezerFrame, "Geen wijzigingen doorgevoerd!");
                            }
                        }
                    } catch (NumberFormatException nr) {
                        JOptionPane.showMessageDialog(wijzigLezerFrame, "Gelieve (enkel) cijfers in te geven bij huisnummer, postcode en lezerID!");
                    } catch (SQLIntegrityConstraintViolationException dubbel){
                        JOptionPane.showMessageDialog(wijzigLezerFrame, "Er bestaat reeds een lezer met dit emailadres!");
                        emailtextField.setText("");
                    }
                }
            }
        });

    }
}
