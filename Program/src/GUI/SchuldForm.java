package GUI;

import db.SchuldDAO;
import entity.Schuld;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SchuldForm {

    private JFrame schuldFrame = new JFrame();
    private JPanel panel1;

    private JLabel schuldLabel;
    private JLabel lezerLabel;

    private JTextField lezerTextField;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();
    private JTable table1;

    private JButton terugButton;
    private JButton alleSchuldenWeergevenButton;
    private JButton zoekButton;
    private JButton betaalDatum;

    public SchuldForm() {

        schuldFrame.getContentPane().add(panel1);
        schuldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        schuldFrame.setVisible(true);
        schuldFrame.setSize(1200, 600);
        schuldFrame.setResizable(false);
        schuldFrame.setLocationRelativeTo(null);

        table1.setAutoCreateRowSorter(true);
        table1.setFillsViewportHeight(true);
        table1.setPreferredScrollableViewportSize(new Dimension(550, 200));
        model.addColumn("LezerID");
        model.addColumn("SchuldID");
        model.addColumn("Oorsprong");
        model.addColumn("Bedrag");
        model.addColumn("Creatiedatum");
        model.addColumn("Betaaldatum");
        table1.setModel(model);

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                schuldFrame.dispose();
            }
        });

        alleSchuldenWeergevenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                for (Schuld s : SchuldDAO.overzichtOpenstaandeSchulden()) {
                    model.addRow(new Object[]{s.getLezer().getId(), s.getId(), s.getOorsprong(), s.getBedrag(), s.getDatumAangemaakt(), s.getDatumBetaald()});
                }
            }
        });

           zoekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                try {
                    int lezerID = Integer.parseInt(lezerTextField.getText());

                    if (SchuldDAO.overzichtSchuldenLezer(lezerID).size() == 0)
                        JOptionPane.showMessageDialog(schuldFrame, "Deze lezer heeft geen schulden", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                    for (Schuld s : SchuldDAO.overzichtSchuldenLezer(lezerID)) {
                        model.addRow(new Object[]{lezerID, s.getId(), s.getOorsprong(), s.getBedrag(), s.getDatumAangemaakt(), s.getDatumBetaald()});
                    }
                }
                catch (NumberFormatException nr) {
                    JOptionPane.showMessageDialog(schuldFrame, "Gelieve een (geldig) LezerID in te geven", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        betaalDatum.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    int row = table1.getSelectedRow();
                    int lezerID = (((int) table1.getModel().getValueAt(row, 0)));
                    int schuldID = (((int) table1.getModel().getValueAt(row, 1)));

                    SchuldDAO.betalenSchuld(schuldID);

                    model.setRowCount(0);
                    for (Schuld s : SchuldDAO.overzichtSchuldenLezer(lezerID)) {
                        model.addRow(new Object[]{lezerID, s.getId(), s.getOorsprong(), s.getBedrag(), s.getDatumAangemaakt(), s.getDatumBetaald()});
                    }
                    JOptionPane.showMessageDialog(schuldFrame, "De betaling werd geregistreerd.", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                } catch (ArrayIndexOutOfBoundsException a) {
                    JOptionPane.showMessageDialog(schuldFrame, "Gelieve een schuld te selecteren", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) { new SchuldForm(); }
}
