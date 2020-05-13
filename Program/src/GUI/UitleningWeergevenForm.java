package GUI;

import db.BoekDAO;
import db.LezerDAO;
import db.UitleenDAO;
import entity.Boek;
import entity.Uitlening;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class UitleningWeergevenForm {

    private JFrame uitleningWeergevenFrame = new JFrame();

    private JLabel weergevenUitleningLabel;
    private JPanel panel1;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();

    private JTable table1;

    private JButton terugButton;
    private JLabel artikelNummer;
    private JTextField artikelnummerTextField;
    private JButton zoekButton1;
    private JLabel lezerID;
    private JTextField lezerIDTextField;
    private JButton zoekButton2;


    public static void main(String[] args) {
        new UitleningWeergevenForm();
    }

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
        model.addColumn("UitleenID");
        model.addColumn("LezerID");
        model.addColumn("Artikelnummer");
        model.addColumn("Titel");
        model.addColumn("Uitleendatum");
        model.addColumn("Verlengdatum");
        model.addColumn("Inleverdatum");
        table1.setModel(model);


        terugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                uitleningWeergevenFrame.dispose();
            }
        });

        zoekButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                try{
                    int artikelnummer = Integer.parseInt(artikelnummerTextField.getText());
                    for(Uitlening u : UitleenDAO.uitleengeschiedenisBoek(artikelnummer)){
                        model.addRow(new Object[]{u.getUitleen_ID(), u.getLezer().getId(), u.getBoek().getArtikelnummer(), BoekDAO.ophalenBoek(u.getBoek().getArtikelnummer()).getTitel(), u.getDatumUitgeleend(), u.getDatumVerlengd(), u.getDatumIngeleverd()});
                    }
                    if(UitleenDAO.uitleengeschiedenisBoek(artikelnummer).size() == 0)
                    {
                        JOptionPane.showMessageDialog(uitleningWeergevenFrame, "Geen uitlening gevonden met dit artikelnummer.");
                    }
                } catch (NumberFormatException nr){
                    JOptionPane.showMessageDialog(uitleningWeergevenFrame, "Gelieve enkel cijfers in te vullen!");
                }
            }
        });


        zoekButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                try{
                    int lezerID = Integer.parseInt(lezerIDTextField.getText());
                    for(Uitlening u : UitleenDAO.uitleengeschiedenisLezer(lezerID)){
                        model.addRow(new Object[]{u.getUitleen_ID(), u.getLezer().getId(), u.getBoek().getArtikelnummer(), BoekDAO.ophalenBoek(u.getBoek().getArtikelnummer()).getTitel(), u.getDatumUitgeleend(), u.getDatumVerlengd(), u.getDatumIngeleverd()});
                    }
                    if(UitleenDAO.uitleengeschiedenisLezer(lezerID).size() == 0)
                    {
                        JOptionPane.showMessageDialog(uitleningWeergevenFrame, "Geen uitlening gevonden met dit lezerID.");
                    }
                } catch (NumberFormatException nr){
                    JOptionPane.showMessageDialog(uitleningWeergevenFrame, "Gelieve enkel cijfers in te vullen!");
                }
            }
        });


    }
    }
