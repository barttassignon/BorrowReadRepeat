/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LezerToevoegenForm extends JFrame {

    private JFrame lezerToevoegenFrame = new JFrame("BorrowReadRepeat");
    private JPanel panel1;
    private JTextField VoornaamTextField;
    private JTextField NaamTextField;
    private JTextField StraatTextField;
    private JTextField WoonplaatsTextField;
    private JTextField EmailtextField;
    private JTextField GebruikersnaamTextField;
    private JButton ToevoegenButton;
    private JButton TerugButton;
    private JLabel VoornaamLabel;
    private JLabel NaamLabel;
    private JLabel AdresLabel;
    private JLabel StraatLabel;
    private JTextField NummerTextField;
    private JTextField PostcodeTextField;
    private JPasswordField passwordField;
    private JTextField GeboortedatumTextField;
    private JLabel GeboortedatumLabel;
    private JLabel WoonplaatsLabel;
    private JLabel NrLabel;
    private JLabel PostcodeLabel;
    private JLabel EmailLabel;
    private JLabel GebruikersnaamLabel;
    private JLabel PaswoordLabel;
    private JLabel BusLabel;
    private JTextField BusTextField;

    public LezerToevoegenForm() {
        lezerToevoegenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lezerToevoegenFrame.getContentPane().add(panel1);
        lezerToevoegenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lezerToevoegenFrame.setVisible(true);
        lezerToevoegenFrame.setSize(600, 600);
        lezerToevoegenFrame.setResizable(false);
        lezerToevoegenFrame.setLocationRelativeTo(null);

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                lezerToevoegenFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new LezerToevoegenForm();
    }
}


