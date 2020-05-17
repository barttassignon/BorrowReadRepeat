package GUI;

import db.*;
import entity.Uitlening;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UitleningToevoegenForm extends JFrame {

    private JFrame uitleningToevoegenFrame = new JFrame();
    private JPanel panel1;

    private JLabel uitleningToevoegen;
    private JLabel lezerID;
    private JLabel artikelnummers;

    private JTextField lezerTextField;
    private JTextField artikelTextField1;

    private JButton terugButton;
    private JButton toevoegenButton;

    public UitleningToevoegenForm() {

        uitleningToevoegenFrame.getContentPane().add(panel1);
        uitleningToevoegenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uitleningToevoegenFrame.setVisible(true);
        uitleningToevoegenFrame.setSize(600, 600);
        uitleningToevoegenFrame.setResizable(false);
        uitleningToevoegenFrame.setLocationRelativeTo(null);

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UitleningForm();
                uitleningToevoegenFrame.dispose();
            }
        });

        toevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    int lezerID = Integer.parseInt(lezerTextField.getText());
                    int boekID = Integer.parseInt(artikelTextField1.getText());

                    if(SchuldDAO.aantalOpenstaandeSchulden(lezerID) > 0){
                        JOptionPane.showMessageDialog(uitleningToevoegenFrame, "Niet mogelijk om uitleningen te doen. De persoon heeft nog schulden!");
                    } else if((BoekDAO.ophalenBoek(boekID).isGereserveerd() == true) && (lezerID != ReservatieDAO.ophalenReservatie(boekID))){
                        JOptionPane.showMessageDialog(uitleningToevoegenFrame, "Boek gereserveerd door iemand anders!");
                    } else {
                        if(UitleenDAO.aantalUitleningenPerLezer(lezerID) >= 10){
                            JOptionPane.showMessageDialog(uitleningToevoegenFrame, "Max. totaal aantal boeken bereikt!");
                        } else if((UitleenDAO.aantalVolwassenboekenPerLezer(lezerID) >= 5) && (BoekDAO.ophalenBoek(boekID).isKinderboek() == false)) {
                            JOptionPane.showMessageDialog(uitleningToevoegenFrame, "Max. aantal boeken voor volwassenen bereikt!");
                        } else{
                            UitleenDAO.uitleningToevoegen(new Uitlening(LezerDAO.ophalenLezer(lezerID), BoekDAO.ophalenBoek(boekID)));
                            BoekDAO.isUitgeleend(boekID);
                            if(BoekDAO.ophalenBoek(boekID).isGereserveerd() == true){
                                BoekDAO.nietGereserveerd(boekID);
                                ReservatieDAO.eindeReservatie(boekID);
                            }

                            JOptionPane.showMessageDialog(uitleningToevoegenFrame, "Uitlening toegevoegd!");
                        }
                    }
                } catch(NumberFormatException nr){
                    JOptionPane.showMessageDialog(uitleningToevoegenFrame, "Gelieve (enkel) cijfers in te geven bij LezerID en artikelnummer!");
                } catch(NullPointerException npe){
                    JOptionPane.showMessageDialog(uitleningToevoegenFrame, "Boek en/of lezer bestaan niet!");
                }
            }
        });
    }

    public static void main(String[] args) {
        new UitleningToevoegenForm();
    }
}
