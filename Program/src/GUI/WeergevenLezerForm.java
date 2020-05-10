/**
 * @Author Bart Tassignon
 */

package GUI;

import db.LezerDAO;
import entity.Lezer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class WeergevenLezerForm extends JFrame {

    private JFrame opzoekenLezerFrame = new JFrame();
    private JPanel panel1;

    private JLabel OpzoekenLezerJlabel;
    private JLabel voornaamLabel;
    private JLabel naamLabel;

    private JTextField voornaamTextField;
    private JTextField naamTextField;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();
    private JTable table1;

    private JButton TerugButton;
    private JButton AlleLezerButton;
    private JButton zoekButton;
    private JButton verwijderButton;

    public static void main(String[] args) {
        new WeergevenLezerForm();
    }

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

        TerugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                opzoekenLezerFrame.dispose();
            }
        });
        AlleLezerButton.addActionListener(new ActionListener() {
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
            }
        });

        verwijderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                System.out.println(row);
                int value = (((int) table1.getModel().getValueAt(row, 0)));
                System.out.println(value);

                try {
                    LezerDAO.verwijderenLezer(value);
                    JOptionPane.showMessageDialog(opzoekenLezerFrame,"Lezer verwijderd");
                } catch (SQLIntegrityConstraintViolationException ex) {
                    JOptionPane.showMessageDialog(opzoekenLezerFrame, "De lezer heeft nog schulden en kan bijgevolg niet worden verwijderd!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }



}







