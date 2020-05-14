package GUI;

import db.BoekDAO;
import db.LezerDAO;
import db.SchuldDAO;
import db.UitleenDAO;
import entity.Schuld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

public class SchuldToevoegenForm {

    private JFrame schuldToevoegenFormFrame = new JFrame();
    private JPanel panel1;

    private JLabel uitleningToevoegen;
    private JLabel lezerID;
    private JLabel boekID;

    private JTextField lezerTextField;
    private JTextField boekTextField;

    private JButton terugButton;
    private JButton verlorenButton;
    private JButton beschadigdButton;

    public SchuldToevoegenForm() {
        schuldToevoegenFormFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        schuldToevoegenFormFrame.getContentPane().add(panel1);
        schuldToevoegenFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        schuldToevoegenFormFrame.setVisible(true);
        schuldToevoegenFormFrame.setSize(700, 600);
        schuldToevoegenFormFrame.setResizable(false);
        schuldToevoegenFormFrame.setLocationRelativeTo(null);

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                schuldToevoegenFormFrame.dispose();
            }
        });

        verlorenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int lezerid = Integer.parseInt(lezerTextField.getText());

                    int artikelnummer = Integer.parseInt(boekTextField.getText());

                    if (UitleenDAO.uitleengeschiedenisLezer(lezerid) == null)
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Geen lezer gevonden met dit ID");
                    else if (UitleenDAO.uitleengeschiedenisBoek(artikelnummer) == null)
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Geen boek gevonden met dit ID.");
                    else {
                        double prijs = BoekDAO.ophalenBoek(artikelnummer).getPrijs();
                        SchuldDAO.aanrekenenSchuld(lezerid, new Schuld("Verlies", prijs, LocalDate.now()));
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Schuld toegevoegd.");
                        // zorgen dat boek nadien niet meer kan worden uitgeleend
                    }
                }
                catch (NumberFormatException nr)
                {
                    JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Gelieve een (geldig) ID in te geven.");
                }
                catch (NullPointerException npe) {
                    JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Deze lezer heeft dit boek niet in zijn bezit.");
                }
            }
        });

        beschadigdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int lezerid = Integer.parseInt(lezerTextField.getText());

                    int artikelnummer = Integer.parseInt(boekTextField.getText());

                    if (UitleenDAO.uitleengeschiedenisLezer(lezerid) == null)
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Geen lezer gevonden met dit ID");
                    else if (UitleenDAO.uitleengeschiedenisBoek(artikelnummer) == null)
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Geen boek gevonden met dit ID.");
                    else {
                        double prijs = BoekDAO.ophalenBoek(artikelnummer).getPrijs()/2;
                        SchuldDAO.aanrekenenSchuld(lezerid, new Schuld("Beschadiging", prijs, LocalDate.now()));
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Schuld toegevoegd.");
                        // zorgen dat boek nadien niet meer kan worden uitgeleend (?)
                    }
                }
                catch (NumberFormatException nr)
                {
                    JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Gelieve een (geldig) ID in te geven.");
                }
                catch (NullPointerException npe) {
                    JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Deze lezer heeft dit boek niet in zijn bezit.");
                }
            }
        });
    }

    public static void main(String[] args) { new SchuldToevoegenForm(); }
}
