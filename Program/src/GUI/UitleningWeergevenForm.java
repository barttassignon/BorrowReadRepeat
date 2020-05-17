package GUI;

import db.UitleenDAO;
import entity.Uitlening;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UitleningWeergevenForm {

    private JFrame uitleningWeergevenFrame = new JFrame();
    private JPanel panel1;

    private JLabel weergevenUitleningLabel;
    private JLabel artikelNummer;
    private JLabel lezerID;

    private JTextField artikelnummerTextField;
    private JTextField lezerIDTextField;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();
    private JTable table1;

    private JButton zoekButton1;
    private JButton zoekButton2;
    private JButton terugButton;

    public UitleningWeergevenForm() {

        uitleningWeergevenFrame.getContentPane().add(panel1);
        uitleningWeergevenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uitleningWeergevenFrame.setVisible(true);
        uitleningWeergevenFrame.setSize(1200, 600);
        uitleningWeergevenFrame.setResizable(false);
        uitleningWeergevenFrame.setLocationRelativeTo(null);

        table1.setAutoCreateRowSorter(true);
        table1.setFillsViewportHeight(true);
        table1.setPreferredScrollableViewportSize(new Dimension(550, 200));
        model.addColumn("BoekID");
        model.addColumn("LezerID");
        model.addColumn("Datum Uitgeleend");
        model.addColumn("Datum Verlengd");
        model.addColumn("Datum Ingeleverd");
        table1.setModel(model);

        terugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                uitleningWeergevenFrame.dispose();
            }
        });

        zoekButton1.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       model.setRowCount(0);
                       try {
                           int boekID = Integer.parseInt(artikelnummerTextField.getText());
                           if (UitleenDAO.uitleengeschiedenisBoek(boekID).size() == 0) {
                               JOptionPane.showMessageDialog(uitleningWeergevenFrame, "Geen boek gevonden met dit artikelnummer.", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                           }
                           for (Uitlening u : UitleenDAO.uitleengeschiedenisBoek(boekID)) {
                               model.addRow(new Object[]{boekID, u.getLezer().getId(), u.getDatumUitgeleend(), u.getDatumVerlengd(), u.getDatumIngeleverd()});
                           }

                       } catch (NumberFormatException nr){
                           JOptionPane.showMessageDialog(uitleningWeergevenFrame, "Gelieve een geldig ID in te vullen!");
                       }
                   }
          });

               zoekButton2.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {

                       model.setRowCount(0);
                       try{
                       int lezerID = Integer.parseInt(lezerIDTextField.getText());
                       if(UitleenDAO.uitleengeschiedenLezer(lezerID).size() == 0) {
                           JOptionPane.showMessageDialog(uitleningWeergevenFrame, "Geen lezer gevonden met deze ID.", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                       }
                       for(Uitlening u : UitleenDAO.uitleengeschiedenLezer(lezerID)){
                           model.addRow(new Object[]{u.getBoek().getArtikelnummer(), u.getLezer().getId(), u.getDatumUitgeleend(), u.getDatumVerlengd(), u.getDatumIngeleverd()});
                       }
                       } catch (NumberFormatException nr){
                               JOptionPane.showMessageDialog(uitleningWeergevenFrame, "Gelieve een geldig ID in te vullen!");
                           }
                }
               });
    }

    public static void main(String[] args) {
        new UitleningWeergevenForm();
    }
}