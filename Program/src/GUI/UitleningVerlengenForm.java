package GUI;

import db.BoekDAO;
import db.UitleenDAO;
import entity.Boek;
import entity.Uitlening;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UitleningVerlengenForm {

    private JFrame uitleningverlengenFrame = new JFrame();
    private JPanel panel1;

    private JLabel uitleningVerlengen;
    private JLabel lezerID;

    private JTextField lezerTextField;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();
    private JTable table1;

    private JButton zoekButton;
    private JButton terugButton;
    private JButton alleUitleningenButton;
    private JButton verlengButton;


    public UitleningVerlengenForm() {
        uitleningverlengenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        uitleningverlengenFrame.getContentPane().add(panel1);
        uitleningverlengenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uitleningverlengenFrame.setVisible(true);
        uitleningverlengenFrame.setSize(1000, 600);
        uitleningverlengenFrame.setResizable(false);
        uitleningverlengenFrame.setLocationRelativeTo(null);

        table1.setAutoCreateRowSorter(true);
        table1.setFillsViewportHeight(true);
        table1.setPreferredScrollableViewportSize(new Dimension(550, 200));
        model.addColumn("UitleenID");
        model.addColumn("LezerID");
        model.addColumn("Titel");
        model.addColumn("Uitleendatum");
        model.addColumn("Verlengdatum");
        model.addColumn("Inleverdatum");
        table1.setModel(model);

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UitleningForm();
                uitleningverlengenFrame.dispose();
            }
        });

        alleUitleningenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                for(Uitlening u : UitleenDAO.ophalenUitleningen()){
                    model.addRow(new Object[]{u.getUitleen_ID(), u.getLezer().getId(), u.getBoek().getArtikelnummer(), u.getDatumUitgeleend(), u.getDatumVerlengd(), u.getDatumIngeleverd()});
                }
            }
        });

        zoekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                try{
                    int lezerID = Integer.parseInt(lezerTextField.getText());
                    for(Uitlening u : UitleenDAO.uitleengeschiedenisLezer(lezerID)){
                        model.addRow(new Object[]{u.getUitleen_ID(), u.getLezer().getId(), u.getBoek().getArtikelnummer(), u.getDatumUitgeleend(), u.getDatumVerlengd(), u.getDatumIngeleverd()});
                    }
                } catch (NumberFormatException nr){
                    JOptionPane.showMessageDialog(uitleningverlengenFrame, "Gelieve enkel cijfers in te vullen!");
                }
            }
        });

        verlengButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                int uitleenID = (((int) table1.getModel().getValueAt(row, 0)));

                UitleenDAO.verlengUitlening(uitleenID);
                JOptionPane.showMessageDialog(null,"Uitlening verlengd!");
                model.setRowCount(0);
                int lezerID = Integer.parseInt(lezerTextField.getText());
                for(Uitlening u : UitleenDAO.uitleengeschiedenisLezer(lezerID)){
                    model.addRow(new Object[]{u.getUitleen_ID(), u.getLezer().getId(), u.getBoek().getArtikelnummer(), u.getDatumUitgeleend(), u.getDatumVerlengd(), u.getDatumIngeleverd()});
                }
            }
        });

    }

    public static void main(String[] args) {
        new UitleningVerlengenForm();
    }
}
