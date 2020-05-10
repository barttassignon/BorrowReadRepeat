/**
 * @Author Bart Tassignon
 */

package GUI;

import db.LezerDAO;
import entity.LezerNietGevonden;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

public class LezerVerwijderenForm extends JFrame{

    private JPanel panel1;
    private JTextField lezerTextField;
    private JButton TerugButton;
    private JButton VerwijderLezerButton;
    private JFrame lezerVerwijderenFrame = new JFrame("BorrowReadRepeat");

    public LezerVerwijderenForm() {

        lezerVerwijderenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lezerVerwijderenFrame.getContentPane().add(panel1);
        lezerVerwijderenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lezerVerwijderenFrame.setVisible(true);
        lezerVerwijderenFrame.setResizable(false);
        lezerVerwijderenFrame.setSize(600, 600);
        lezerVerwijderenFrame.setLocationRelativeTo(null);

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                lezerVerwijderenFrame.dispose();
            }
        });

        VerwijderLezerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try{
                    int lezer = Integer.parseInt(lezerTextField.getText());
                } catch (NumberFormatException nr){
                    JOptionPane.showMessageDialog(lezerVerwijderenFrame, "Gelieve de lezerID in te geven!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                }

                int lezer = Integer.parseInt(lezerTextField.getText());

                int action = JOptionPane.showConfirmDialog(LezerVerwijderenForm.this, "Ben je zeker dat je deze lezer wilt verwijderen?", "Verwijderen", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    try {
                        LezerDAO.verwijderenLezer(lezer);
                        JOptionPane.showMessageDialog(lezerVerwijderenFrame, "De lezer werd verwijderd!", "Resultaat", JOptionPane.INFORMATION_MESSAGE);
                    } catch (LezerNietGevonden lezerNietGevonden) {
                        JOptionPane.showMessageDialog(lezerVerwijderenFrame, "Geen lezer gevonden met deze ID!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLIntegrityConstraintViolationException schuld){
                        JOptionPane.showMessageDialog(lezerVerwijderenFrame, "De lezer heeft nog schulden en kan bijgevolg niet worden verwijderd!", "Resultaat", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }

    public static void main(String[] args) {
        new LezerVerwijderenForm();
    }
}


