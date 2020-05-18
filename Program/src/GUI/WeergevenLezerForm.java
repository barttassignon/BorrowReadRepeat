/**
 * @Author Bart Tassignon
 */

package GUI;

import db.LezerDAO;
import db.SchuldDAO;
import db.UitleenDAO;
import entity.Lezer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeergevenLezerForm extends JFrame {

    private JFrame opzoekenLezerFrame = new JFrame();
    private JPanel panel1;

    private JLabel opzoekenLezerJlabel;
    private JLabel voornaamLabel;
    private JLabel naamLabel;

    private JTextField voornaamTextField;
    private JTextField naamTextField;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();
    private JTable table1;

    private JButton terugButton;
    private JButton alleLezerButton;
    private JButton zoekButton;
    private JButton verwijderButton;

    public WeergevenLezerForm() {

        opzoekenLezerFrame.getContentPane().add(panel1);
        opzoekenLezerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        opzoekenLezerFrame.setVisible(true);
        opzoekenLezerFrame.setSize(600, 600);
        opzoekenLezerFrame.setResizable(false);
        opzoekenLezerFrame.setLocationRelativeTo(null);

        table1.setAutoCreateRowSorter(true);
        table1.setFillsViewportHeight(true);
        table1.setPreferredScrollableViewportSize(new Dimension(850, 200));
        model.addColumn("LezerId");
        model.addColumn("Voornaam");
        model.addColumn("Naam");
        model.addColumn("Geboortedatum");
        model.addColumn("Emailadres");
        model.addColumn("Telefoon");
        table1.setModel(model);

        this.pack();

        terugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                opzoekenLezerFrame.dispose();
            }
        });

        alleLezerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
              for(Lezer l : LezerDAO.ophalenLezers()){
                  model.addRow(new Object[]{l.getId(), l.getVoornaam(), l.getNaam(), l.getGeboortedatum(), l.getEmail(), l.getTelefoon()});
              }
            }
        });

        zoekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String voornaam = voornaamTextField.getText();
                String naam = naamTextField.getText();

                model.setRowCount(0);

                for(Lezer l : LezerDAO.opzoekenLezer(voornaam, naam)){
                    model.addRow(new Object[]{l.getId(), l.getVoornaam(), l.getNaam(), l.getGeboortedatum(), l.getEmail(), l.getTelefoon()});
                }

                if(LezerDAO.opzoekenLezer(voornaam, naam).size() == 0) {
                    JOptionPane.showMessageDialog(opzoekenLezerFrame, "Geen lezers gevonden met deze naam.", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        verwijderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                int lezerID = (((int) table1.getModel().getValueAt(row, 0)));

                if(SchuldDAO.aantalOpenstaandeSchulden(lezerID) > 0){
                    JOptionPane.showMessageDialog(opzoekenLezerFrame,"Lezer kan niet worden verwijderd want hij heeft nog " + SchuldDAO.aantalOpenstaandeSchulden(lezerID) + " openstaande schuld(en)!");
                } else if(UitleenDAO.aantalUitleningenPerLezer(lezerID) > 0){
                    JOptionPane.showMessageDialog(opzoekenLezerFrame,"Lezer kan niet worden verwijderd want hij heeft nog " + UitleenDAO.aantalUitleningenPerLezer(lezerID) + " uitlening(en)!");
                } else {
                    LezerDAO.verwijderenLezer(lezerID);
                    JOptionPane.showMessageDialog(opzoekenLezerFrame, "Lezer verwijderd");
                    model.setRowCount(0);
                    String voornaam = voornaamTextField.getText();
                    String naam = naamTextField.getText();
                    for (Lezer l : LezerDAO.opzoekenLezer(voornaam, naam)) {
                        model.addRow(new Object[]{l.getId(), l.getVoornaam(), l.getNaam(), l.getGeboortedatum(), l.getEmail(), l.getTelefoon()});
                    }
                }
            }
        });
    }
}







