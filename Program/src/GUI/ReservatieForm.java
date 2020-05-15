package GUI;

import db.BoekDAO;
import db.LezerDAO;
import db.ReservatieDAO;
import db.SchuldDAO;
import entity.Boek;
import entity.Reservatie;
import entity.Schuld;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ReservatieForm {

    private JFrame reservatieFrame = new JFrame();
    private JPanel panel1;

    private JLabel weergevenBoekenLabel;
    private JLabel lezerLabel;
    private JLabel titelLabel;

    private JTextField lezerTextField;
    private JTextField titelTextField;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();
    private JTable table1;

    private JButton terugButton;
    private JButton zoekButton;
    private JButton alleBoekenButton;
    private JButton reserveerButton;

    public ReservatieForm() {

        reservatieFrame.getContentPane().add(panel1);
        reservatieFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reservatieFrame.setVisible(true);
        reservatieFrame.setSize(1200, 600);
        reservatieFrame.setResizable(false);
        reservatieFrame.setLocationRelativeTo(null);

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

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                reservatieFrame.dispose();
            }
        });

        alleBoekenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                for (Boek b : BoekDAO.ophalenBoeken()) {
                    model.addRow(new Object[]{b.getArtikelnummer(), b.getISBN(), b.getTitel(), b.getAuteur(), b.getUitgeverij(), b.getPaginas(), b.getAankoopdatum()});
                }
            }
        });

        zoekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                String titel = titelTextField.getText();

                if (BoekDAO.opzoekenBoek(titel).size() == 0) {
                    JOptionPane.showMessageDialog(null, "Geen boeken gevonden met deze titel.");
                }
                for (Boek b : BoekDAO.opzoekenBoek(titel)) {
                    model.addRow(new Object[]{b.getArtikelnummer(), b.getISBN(), b.getTitel(), b.getAuteur(), b.getUitgeverij(), b.getPaginas(), b.getAankoopdatum()});
                }
            }
        });

        reserveerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try{
                    int lezerID = Integer.parseInt(lezerTextField.getText());
                    int row = table1.getSelectedRow();
                    int artikelnummer = (((int) table1.getModel().getValueAt(row, 0)));

                    if(LezerDAO.ophalenLezer(lezerID) == null){
                        JOptionPane.showMessageDialog(null, "Geen lezer gevonden met dit lezerID!");
                    } else if(BoekDAO.ophalenBoek(artikelnummer).isGereserveerd()){
                        JOptionPane.showMessageDialog(null, "Boek is reeds gereserveerd door iemand anders!");
                    } else{
                        ReservatieDAO.maakReservatie(new Reservatie(LezerDAO.ophalenLezer(lezerID), BoekDAO.ophalenBoek(artikelnummer)));
                        BoekDAO.isGereserveerd(artikelnummer);
                        SchuldDAO.aanrekenenSchuld(lezerID, new Schuld("Reservatie", 0.5));
                        JOptionPane.showMessageDialog(null, "Reservatie gemaakt!");
                    }
                } catch(NumberFormatException nr){
                    JOptionPane.showMessageDialog(null, "Geef een (geldig) lezerID in!");
                } catch (ArrayIndexOutOfBoundsException oob) {
                    JOptionPane.showMessageDialog(null, "Selecteer een boek in de tabel!");
                }

            }
        });
    }

    public static void main(String[] args) { new ReservatieForm(); }
}

