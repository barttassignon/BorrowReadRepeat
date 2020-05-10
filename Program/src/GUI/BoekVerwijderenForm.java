/**
 * @Author Bart Tassignon
 */

package GUI;

import db.BoekDAO;
import db.LezerDAO;
import entity.BoekNietGevonden;
import entity.LezerNietGevonden;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

public class BoekVerwijderenForm extends JFrame{
    private JPanel panel1;
    private JFrame boekVerwijderenFrame = new JFrame("BorrowReadRepeat");
    private JTextField boekTextField;
    private JButton VerwijderBoekButton;
    private JButton TerugButton;

    public BoekVerwijderenForm() {

        boekVerwijderenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        boekVerwijderenFrame.getContentPane().add(panel1);
        boekVerwijderenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boekVerwijderenFrame.setVisible(true);
        boekVerwijderenFrame.setResizable(false);
        boekVerwijderenFrame.setSize(600, 600);
        boekVerwijderenFrame.setLocationRelativeTo(null);

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            new BeheerderForm();
            boekVerwijderenFrame.dispose();
            }
        });
        VerwijderBoekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try{
                    int boek = Integer.parseInt(boekTextField.getText());
                } catch (NumberFormatException nr){
                    JOptionPane.showMessageDialog(boekVerwijderenFrame, "Gelieve het artikelnummer in te geven!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }

                int boek = Integer.parseInt(boekTextField.getText());

                    int action = JOptionPane.showConfirmDialog(BoekVerwijderenForm.this, "Ben je zeker dat je dit boek wilt verwijderen?", "Verwijderen", JOptionPane.OK_CANCEL_OPTION);
                    if (action == JOptionPane.OK_OPTION) {
                            try {
                                BoekDAO.verwijderenBoek(boek);
                                JOptionPane.showMessageDialog(boekVerwijderenFrame, "Het boek werd verwijderd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                            } catch (BoekNietGevonden boekNietGevonden) {
                                JOptionPane.showMessageDialog(boekVerwijderenFrame, "Geen boek gevonden met dit artikelnummer!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
        });
    }

    public static void main(String[] args) {
        new BoekVerwijderenForm();
    }
}



