package GUI;

import db.BoekDAO;
import db.SchuldDAO;
import entity.Boek;
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

    private JLabel SchuldLabel;
    private JLabel lezerLabel;

    private JTextField lezerTextField;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();
    private JTable table1;

    private JButton TerugButton;
    private JButton alleSchuldenWeergevenButton;
    private JButton zoekButton;
    private JButton betaalDatum;
    private JButton alleSchuldenButton;

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

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                schuldFrame.dispose();
            }
        });

        //Werkt nog niet: probleem met enum + hoe LezerID toevoegen?

        alleSchuldenWeergevenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                for (Schuld s : SchuldDAO.overzichtOpenstaandeSchulden()) {
                    model.addRow(new Object[]{s.getId(), s.getOorsprong(), s.getBedrag(), s.getDatumAangemaakt(), s.getDatumBetaald()});
                }
            }
        });

        betaalDatum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int lezerID = Integer.parseInt(lezerTextField.getText());


                //SchuldDAO.betalenSchuld(lezerID, );
            }
        });

        zoekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                try {
                    int lezerID = Integer.parseInt(lezerTextField.getText());

                    if (SchuldDAO.overzichtSchuldenLezer(lezerID).size() == 0)
                        JOptionPane.showMessageDialog(schuldFrame, "LezerID niet gevonden", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                    for (Schuld s : SchuldDAO.overzichtSchuldenLezer(lezerID)) {
                        model.addRow(new Object[]{lezerID, s.getId(), s.getOorsprong(), s.getBedrag(), s.getDatumAangemaakt(), s.getDatumBetaald()});
                    }
                }

                catch (NumberFormatException nr) {
                    JOptionPane.showMessageDialog(schuldFrame, "Gelieve een (geldig) LezerID in te geven", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        betaalDatum.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                int lezerID = (((int) table1.getModel().getValueAt(row, 0)));
                double bedrag = (((double) table1.getModel().getValueAt(row, 3)));

                SchuldDAO.betalenSchuld(lezerID, bedrag, LocalDate.now());
            }
        });
    }

    public static void main(String[] args) { new SchuldForm(); }


}
