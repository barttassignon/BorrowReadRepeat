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
    private JLabel AdresLabel;
    private JLabel StraatLabel;
    private JLabel NrLabel;
    private JLabel BusLabel;
    private JLabel PostcodeLabel;
    private JLabel WoonplaatsLabel;
    private JLabel EmailLabel;
    private JLabel TelefoonLabel;

    private JTextField lezerTextField;
    private JTextField straatTextfield;
    private JTextField nummerTextField;
    private JTextField busTextField;
    private JTextField postcodeTextField;
    private JTextField woonplaatsTextField;
    private JTextField emailtextField;
    private JTextField telefoonTextField;

    private JButton wijzigButton;
    private JButton TerugButton;

    public WijzigLezerForm(){
        wijzigLezerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        wijzigLezerFrame.getContentPane().add(panel1);
        wijzigLezerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        wijzigLezerFrame.setVisible(true);
        wijzigLezerFrame.setSize(600, 600);
        wijzigLezerFrame.setResizable(false);
        wijzigLezerFrame.setLocationRelativeTo(null);

        TerugButton.addActionListener(new ActionListener() {
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
                        int lezer = Integer.parseInt(lezerTextField.getText());
                        String email = emailtextField.getText();
                        String telefoon = telefoonTextField.getText();
                        if(LezerDAO.bestaatLezer(lezer) == 0){
                            JOptionPane.showMessageDialog(wijzigLezerFrame, "Er bestaat geen lezer met dit ID!");
                        }
                        else {
                            if (telefoon.matches("^0[1-9]\\d{7,8}$")){
                                LezerDAO.wijzigenTelLezer(lezer, telefoon);
                                JOptionPane.showMessageDialog(wijzigLezerFrame, "Telefoon gewijzigd!");
                            }

                            if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
                                    LezerDAO.wijzigenEmailLezer(lezer, email);
                                    JOptionPane.showMessageDialog(wijzigLezerFrame, "Email gewijzigd!");
                            }

                            String straat = straatTextfield.getText();
                            if (!straat.equals("")){
                                int nummer = Integer.parseInt(nummerTextField.getText());
                                String bus = busTextField.getText();
                                int postcode = Integer.parseInt(postcodeTextField.getText());
                                String woonplaats = woonplaatsTextField.getText();
                                LezerDAO.wijzigenAdresLezer(lezer, new Adres(straat, nummer, bus, postcode, woonplaats));
                                JOptionPane.showMessageDialog(wijzigLezerFrame, "Adres gewijzigd!");
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
    public static void main(String[] args) { new WijzigLezerForm(); }
}
