package GUI;

import db.BoekDAO;
import db.LezerDAO;
import db.UitleenDAO;
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
        uitleningToevoegenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
                    UitleenDAO.uitleningToevoegen(new Uitlening(LezerDAO.ophalenLezer(lezerID), BoekDAO.ophalenBoek(boekID)));
                    BoekDAO.isUitgeleend(boekID);
                    JOptionPane.showMessageDialog(uitleningToevoegenFrame, "Uitlening toegevoegd!");
                } catch(NumberFormatException nr){
                    JOptionPane.showMessageDialog(uitleningToevoegenFrame, "Gelieve (enkel) cijfers in te geven!");
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
