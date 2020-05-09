/**
 * @Author Bart Tassignon
 */

package GUI;

import db.LezerDAO;
import entity.*;
import db.BoekDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BoekToevoegenForm extends JFrame {

    private JFrame boekToevoegenFrame = new JFrame("BorrowReadRepeat");
    private JPanel panel1;

    private JLabel BoekToevoegen;
    private JLabel ISBNLabel;
    private JLabel TitelLabel;
    private JLabel AuteurLabel;
    private JLabel UitgeverijLabel;
    private JLabel TaalLabel;
    private JLabel KinderLabel;
    private JLabel GenreJlabel;
    private JLabel PaginaLabel;
    private JLabel AankoopdatumLabel;
    private JLabel PrijsTextLabel;
    private JLabel PlaatsInBibLabel;

    private JTextField ISBNtextField;
    private JTextField TiteltextField;
    private JTextField AuteurtextField;
    private JTextField UitgeverijtextField;
    private JTextField PaginatextField;
    private JTextField AankoopdatumtextField;
    private JTextField PrijstextField;
    private JTextField PlaatsInBibtextField;

    private JButton ToevoegenButton;
    private JButton TerugButton;

    private ButtonGroup taalGroep;
    private JRadioButton nederlandsRadioButton;
    private JRadioButton fransRadioButton;
    private JRadioButton engelsRadioButton;

    private ButtonGroup kinderGroep;
    private JRadioButton NeeradioButton1;
    private JRadioButton JaRadioButton;

    private JComboBox GenreComboBox1;
    String[] genreStrings = {"Biografie", "Fantasy", "Geschiedenis", "Gezondheid", "Prentenboek", "Kookboek", "Roman", "Thriller", "Technologie"};

    public BoekToevoegenForm() {

        boekToevoegenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        boekToevoegenFrame.getContentPane().add(panel1);
        boekToevoegenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boekToevoegenFrame.setVisible(true);
        boekToevoegenFrame.setSize(600, 600);
        boekToevoegenFrame.setResizable(false);
        boekToevoegenFrame.setLocationRelativeTo(null);

        // enkel 1 selectie mogelijk bij keuze taal
        taalGroep = new ButtonGroup();
        taalGroep.add(nederlandsRadioButton);
        taalGroep.add(fransRadioButton);
        taalGroep.add(engelsRadioButton);
        nederlandsRadioButton.setSelected(true);

        // enkel 1 selectie mogelijk bij keuze taal
        kinderGroep = new ButtonGroup();
        kinderGroep.add(NeeradioButton1);
        kinderGroep.add(JaRadioButton);
        NeeradioButton1.setSelected(true);

        DefaultComboBoxModel GenreModel = new DefaultComboBoxModel(genreStrings);
        GenreModel.getElementAt(0);
        GenreModel.getElementAt(1);
        GenreModel.getElementAt(2);
        GenreModel.getElementAt(3);
        GenreModel.getElementAt(4);
        GenreModel.getElementAt(5);
        GenreModel.getElementAt(6);
        GenreModel.getElementAt(7);
        GenreModel.getElementAt(8);
        GenreComboBox1.setModel(GenreModel);
        GenreComboBox1.setSelectedIndex(0);
        GenreComboBox1.setEditable(true);

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                boekToevoegenFrame.dispose();
            }
        });
        ToevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Long ISBN = Long.parseLong(ISBNtextField.getText());
                    int paginas = Integer.parseInt(PaginatextField.getText());
                } catch (NumberFormatException nr){
                    JOptionPane.showMessageDialog(boekToevoegenFrame, "Gelieve (enkel) cijfers in te geven bij ISBN en paginas", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }

                Long ISBN = Long.parseLong(ISBNtextField.getText());
                String titel = TiteltextField.getText();
                String auteur = AuteurtextField.getText();
                String uitgeverij = UitgeverijtextField.getText();

                String taal = null;
                if(nederlandsRadioButton.isSelected()) {
                    taal = "Nederlands";
                } else if(fransRadioButton.isSelected()) {
                    taal = "Frans";
                }else if(engelsRadioButton.isSelected()) {
                    taal = "Engels";
                }

                Boolean kind = false;
                if (JaRadioButton.isSelected()) {kind = true;}

                String genre = GenreModel.getSelectedItem().toString();

                int paginas = Integer.parseInt(PaginatextField.getText());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                try {
                    LocalDate aankoopdatum = LocalDate.parse(AankoopdatumtextField.getText(), formatter);
                } catch(DateTimeParseException datum){
                    JOptionPane.showMessageDialog(boekToevoegenFrame, "Vul datum als volgt in: dd/mm/jjjj", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }

                LocalDate aankoopdatum = LocalDate.parse(AankoopdatumtextField.getText(), formatter);

                try{
                    double aankoopprijs = Double.parseDouble(PrijstextField.getText());
                } catch(NumberFormatException nr){
                    JOptionPane.showMessageDialog(boekToevoegenFrame, "Gelieve (enkel) cijfers in te geven bij aankoopprijs en een punt te gebruiken in plaats van een komma", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }

                double aankoopprijs = Double.parseDouble(PrijstextField.getText());
                String plaats = PlaatsInBibtextField.getText();

                if (titel.length() == 0 || auteur.length() == 0 || uitgeverij.length() == 0 || plaats.length() == 0) {
                    JOptionPane.showMessageDialog(boekToevoegenFrame, "Gelieve alle velden in te vullen!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                } else{
                        if(kind) {
                            BoekDAO.toevoegenBoek(new Kinderboek(ISBN, titel, auteur, uitgeverij, taal, genre, paginas, aankoopdatum, aankoopprijs, plaats));
                            JOptionPane.showMessageDialog(boekToevoegenFrame, "Boek toegevoegd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            BoekDAO.toevoegenBoek(new Boek(ISBN, titel, auteur, uitgeverij, taal, genre, paginas, aankoopdatum, aankoopprijs, plaats));
                            JOptionPane.showMessageDialog(boekToevoegenFrame, "Lezer toegevoegd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                        }
                }

            }
        });
    }

    public static void main(String[] args) {
        new BoekToevoegenForm();
    }
}


