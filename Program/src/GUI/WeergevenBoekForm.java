/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BoekDAO;
import entity.Boek;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeergevenBoekForm extends JFrame{

    private JFrame weergevenBoekFrame = new JFrame();
    private JPanel panel1;

    private JLabel weergevenBoekenLabel;
    private JLabel titelLabel;

    private JTextField titelTextField;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();
    private JTable table1;

    private JButton zoekButton;
    private JButton alleBoekenButton;
    private JButton verwijderBoek;
    private JButton terugButton;

    public WeergevenBoekForm() {

        weergevenBoekFrame.getContentPane().add(panel1);
        weergevenBoekFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weergevenBoekFrame.setVisible(true);
        weergevenBoekFrame.setSize(1200, 600);
        weergevenBoekFrame.setResizable(false);
        weergevenBoekFrame.setLocationRelativeTo(null);

        table1.setAutoCreateRowSorter(true);
        table1.setFillsViewportHeight(true);
        table1.setPreferredScrollableViewportSize(new Dimension(550, 200));
        model.addColumn("BoekID");
        model.addColumn("ISBN");
        model.addColumn("Titel");
        model.addColumn("Auteur");
        model.addColumn("Uitgeverij");
        model.addColumn("AantalBlz");
        model.addColumn("Aankoopdatum");
        table1.setModel(model);
        this.pack();

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                weergevenBoekFrame.dispose();
            }
        });

        alleBoekenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                for(Boek b : BoekDAO.ophalenBoeken()){
                    model.addRow(new Object[]{b.getArtikelnummer(), b.getISBN(), b.getTitel(), b.getAuteur(), b.getUitgeverij(), b.getPaginas(), b.getAankoopdatum()});
                }
            }
        });

        zoekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                String titel = titelTextField.getText();

                if(BoekDAO.opzoekenBoek(titel).size() == 0) {
                    JOptionPane.showMessageDialog(weergevenBoekFrame, "Geen boeken gevonden met deze titel.", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                }

                for(Boek b : BoekDAO.opzoekenBoek(titel)){
                    model.addRow(new Object[]{b.getArtikelnummer(), b.getISBN(), b.getTitel(), b.getAuteur(), b.getUitgeverij(), b.getPaginas(), b.getAankoopdatum()});
                }

            }
        });

        verwijderBoek.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                int boekID = (((int) table1.getModel().getValueAt(row, 0)));

                BoekDAO.verwijderenBoek(boekID);
                JOptionPane.showMessageDialog(null,"Boek verwijderd");
                model.setRowCount(0);
                String titel = titelTextField.getText();
                for(Boek b : BoekDAO.opzoekenBoek(titel)){
                    model.addRow(new Object[]{b.getArtikelnummer(), b.getISBN(), b.getTitel(), b.getAuteur(), b.getUitgeverij(), b.getPaginas(), b.getAankoopdatum()});
                }
            }
        });
    }
}


