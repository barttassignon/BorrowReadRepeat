/**
 * @Author Bart Tassignon
 */

package GUI;

import entity.*;
import db.BoekDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BoekToevoegenForm extends JFrame {

    private JFrame boekToevoegenFrame = new JFrame("BorrowReadRepeat");
    private JPanel panel1;

    private JLabel boekToevoegen;
    private JLabel ISBNLabel;
    private JLabel titelLabel;
    private JLabel auteurLabel;
    private JLabel uitgeverijLabel;
    private JLabel taalLabel;
    private JLabel genreJlabel;
    private JLabel kinderLabel;
    private JLabel paginaLabel;
    private JLabel aankoopdatumLabel;
    private JLabel prijsTextLabel;
    private JLabel plaatsInBibLabel;

    private JTextField ISBNtextField;
    private JTextField titeltextField;
    private JTextField auteurtextField;
    private JTextField uitgeverijtextField;
    private JTextField paginatextField;
    private JTextField aankoopdatumtextField;
    private JTextField prijstextField;
    private JTextField plaatsInBibtextField;

    private JButton toevoegenButton;
    private JButton bestandToevoegen;
    private JButton terugButton;

    private ButtonGroup taalGroep;
    private JRadioButton nederlandsRadioButton;
    private JRadioButton fransRadioButton;
    private JRadioButton engelsRadioButton;

    private ButtonGroup kinderGroep;
    private JRadioButton neeRadioButton1;
    private JRadioButton jaRadioButton;

    private JComboBox genreComboBox1;

    String[] genreStrings = {"Biografie", "Fantasy", "Geschiedenis", "Gezondheid", "Prentenboek", "Kookboek", "Roman", "Thriller", "Technologie"};

    public BoekToevoegenForm() {

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
        kinderGroep.add(neeRadioButton1);
        kinderGroep.add(jaRadioButton);
        neeRadioButton1.setSelected(true);

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
        genreComboBox1.setModel(GenreModel);
        genreComboBox1.setSelectedIndex(0);
        genreComboBox1.setEditable(true);

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                boekToevoegenFrame.dispose();
            }
        });
        toevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Long ISBN = Long.parseLong(ISBNtextField.getText());
                    String titel = titeltextField.getText();
                    String auteur = auteurtextField.getText();
                    String uitgeverij = uitgeverijtextField.getText();
                    String taal = null;
                    if(nederlandsRadioButton.isSelected()) {
                        taal = "Nederlands";
                    } else if(fransRadioButton.isSelected()) {
                        taal = "Frans";
                    }else if(engelsRadioButton.isSelected()) {
                        taal = "Engels";
                    }
                    Boolean kind = false;
                    if (jaRadioButton.isSelected()) {kind = true;}
                    String genre = GenreModel.getSelectedItem().toString();
                    int paginas = Integer.parseInt(paginatextField.getText());
                    double aankoopprijs = Double.parseDouble(prijstextField.getText());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate aankoopdatum = LocalDate.parse(aankoopdatumtextField.getText(), formatter);
                    if(aankoopdatum.isAfter(LocalDate.now())){
                        JOptionPane.showMessageDialog(boekToevoegenFrame, "Je kan geen aankoopdatum in de toekomst vermelden!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    }
                    String plaats = plaatsInBibtextField.getText();

                    if (titel.length() == 0 || auteur.length() == 0 || uitgeverij.length() == 0 || plaats.length() == 0) {
                        JOptionPane.showMessageDialog(boekToevoegenFrame, "Gelieve alle velden in te vullen!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    } else if(ISBNtextField.getText().length() != 10 && ISBNtextField.getText().length() != 13) {
                        JOptionPane.showMessageDialog(boekToevoegenFrame, "Geef een geldig ISBN.", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    } else{
                        BoekDAO.toevoegenBoek(new Boek(ISBN, titel, auteur, uitgeverij, taal, genre, paginas, aankoopdatum, aankoopprijs, plaats, kind));
                        JOptionPane.showMessageDialog(boekToevoegenFrame, "Boek toegevoegd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException nr){
                    JOptionPane.showMessageDialog(boekToevoegenFrame, "Gelieve (enkel) cijfers in te geven bij ISBN, pagina's en aankoopprijs", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }catch(DateTimeParseException datum){
                    JOptionPane.showMessageDialog(boekToevoegenFrame, "Vul datum als volgt in: dd/mm/jjjj", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bestandToevoegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selecteer = new JFileChooser();
                selecteer.addChoosableFileFilter(new BestandFilter());
                int keuze = selecteer.showOpenDialog(boekToevoegenFrame);
                if(keuze == JFileChooser.APPROVE_OPTION){
                    File bestand = selecteer.getSelectedFile();
                    try{
                        BoekDAO.toevoegenBoeken(bestand);
                        JOptionPane.showMessageDialog(boekToevoegenFrame, "Boeken toegevoegd.");
                    } catch (ArrayIndexOutOfBoundsException error){
                        JOptionPane.showMessageDialog(boekToevoegenFrame, "Verkeerd bestand geselecteerd. Selecteer een CSV-bestand!");
                    } catch(NumberFormatException formaat){
                        JOptionPane.showMessageDialog(boekToevoegenFrame, "Een of meerdere boeken kon niet worden toegevoegd wegens een ongeldig formaat!");
                    }
                } else
                    JOptionPane.showMessageDialog(boekToevoegenFrame, "Geen bestand geselecteerd.");
            }
        });
    }

    public static void main(String[] args) {
        new BoekToevoegenForm();
    }
}


