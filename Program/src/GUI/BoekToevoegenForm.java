/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoekToevoegenForm extends JFrame {

    private JPanel panel1;
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

    private JRadioButton nederlandsRadioButton;
    private JRadioButton fransRadioButton;
    private JRadioButton engelsRadioButton;
    private JLabel ISBNLabel;
    private JLabel TitelLabel;
    private JLabel AuteurLabel;
    private JLabel UitgeverijLabel;
    private JLabel TaalLabel;
    private JLabel PaginaLabel;
    private JLabel AankoopdatumLabel;
    private JLabel PrijsTextLabel;
    private JLabel KinderLabel;
    private JLabel PlaatsInBibLabel;
    private JRadioButton NeeradioButton1;
    private JRadioButton JaRadioButton;
    private ButtonGroup taalGroep;
    private ButtonGroup kinderGroep;

    private JFrame boekToevoegenFrame = new JFrame("BorrowReadRepeat");

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

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                boekToevoegenFrame.dispose();
            }
        });
        ToevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    public static void main(String[] args) {
        new BoekToevoegenForm();
    }
}


