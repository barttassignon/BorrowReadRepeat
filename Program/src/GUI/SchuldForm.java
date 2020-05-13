package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        model.addColumn("SchuldID");
        model.addColumn("LezerID");
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

        /*alleSchuldenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                for (Boek b : BoekDAO.ophalenBoeken()) {
                    model.addRow(new Object[]{b.getArtikelnummer(), b.getISBN(), b.getTitel(), b.getAuteur(), b.getUitgeverij(), b.getPaginas(), b.getAankoopdatum()});
                }
            }
        });*/

        zoekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                String titel = lezerTextField.getText();

                /*for (Boek b : BoekDAO.opzoekenBoek(titel)) {
                    model.addRow(new Object[]{b.getArtikelnummer(), b.getISBN(), b.getTitel(), b.getAuteur(), b.getUitgeverij(), b.getPaginas(), b.getAankoopdatum()});
                }*/
            }
        });
    }



    public static void main(String[] args) { new SchuldForm(); }
}
