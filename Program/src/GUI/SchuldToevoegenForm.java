package GUI;

import db.BoekDAO;
import db.SchuldDAO;
import db.UitleenDAO;
import entity.Schuld;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                    int lezerID = Integer.parseInt(lezerTextField.getText());
                    int boekID = Integer.parseInt(boekTextField.getText());

                    if (UitleenDAO.uitleengeschiedenisLezer(lezerID) == null)
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Geen lezer gevonden met dit ID");
                    else if (UitleenDAO.uitleengeschiedenisBoek(boekID) == null)
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Geen boek gevonden met dit ID.");
                    else {
                        double prijs = BoekDAO.ophalenBoek(boekID).getPrijs();
                        SchuldDAO.aanrekenenSchuld(lezerID, new Schuld("Verlies", prijs));
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Schuld toegevoegd.");
                        UitleenDAO.binnenbrengenUitlening(boekID);
                        BoekDAO.nietUitgeleend(boekID);
                        BoekDAO.verwijderenBoek(boekID);
                    }
                } catch (NumberFormatException nr) {
                    JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Gelieve een (geldig) ID in te geven.");
                } catch (NullPointerException npe) {
                    JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Deze lezer heeft dit boek niet in zijn bezit.");
                }
            }
        });

        beschadigdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int lezerID = Integer.parseInt(lezerTextField.getText());
                    int boekID = Integer.parseInt(boekTextField.getText());

                    if (UitleenDAO.uitleengeschiedenisLezer(lezerID) == null)
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Geen lezer gevonden met dit ID");
                    else if (UitleenDAO.uitleengeschiedenisBoek(boekID) == null)
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Geen boek gevonden met dit ID.");
                    else {
                        double prijs = BoekDAO.ophalenBoek(boekID).getPrijs()/2;
                        SchuldDAO.aanrekenenSchuld(lezerID, new Schuld("Beschadiging", prijs));
                        JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Schuld toegevoegd.");
                        UitleenDAO.binnenbrengenUitlening(boekID);
                    }
                } catch (NumberFormatException nr) {
                    JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Gelieve een (geldig) ID in te geven.");
                } catch (NullPointerException npe) {
                    JOptionPane.showMessageDialog(schuldToevoegenFormFrame, "Deze lezer heeft dit boek niet in zijn bezit.");
                }
            }
        });
    }

    public static void main(String[] args) { new SchuldToevoegenForm(); }
}
