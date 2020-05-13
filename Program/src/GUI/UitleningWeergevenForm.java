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


        //       zoekButton1.addActionListener(new ActionListener() {
        //           @Override
        //           public void actionPerformed(ActionEvent e) {
        //               int artikelNummer = artikelnummerTextField.getText();

        //               model.setRowCount(0);

        //               for(Uitlening b: UitleenDAO.uitleengeschiedenisBoek(artikelNummer)){
        //                   model.addRow(new Object[]{b.getArtikelnummer(), b.lezerID(), b.getTitel(), b.getAuteur(), b.getUitgeverij(), b.getPaginas(), b.getAankoopdatum()});
        //               }

        //               if(UitleningDAO.opzoekenBoek(artikelNummer).size() == 0)
        //               {
        //                   JOptionPane.showMessageDialog(uitleningWeergevenFrame, "Geen boek gevonden met dit artikelnummer.", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
        //               }
        //           }
//        });

        //       zoekButton2.addActionListener(new ActionListener() {
        //           public void actionPerformed(ActionEvent e) {
        //               model.setRowCount(0);

        //               String titel = titelTextField.getText();

        //               for(Uitlening b : UitleenDAO.uitleengeschiedenisLezer(titel)){
        //                   model.addRow(new Object[]{b.getArtikelnummer(), b.lezerID(), b.getTitel(), b.getAuteur(), b.getUitgeverij(), b.getPaginas(), b.getAankoopdatum()});
        //               }

        //               if(UitleningDAO.opzoekenBoek(titel).size() == 0)
//                {
//                    JOptionPane.showMessageDialog(uitleningWeergevenFrame, "Geen lezer gevonden met deze ID.", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
//                }
//            }
        //       });

//    }
    }
}