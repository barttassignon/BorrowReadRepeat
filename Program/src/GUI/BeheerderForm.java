/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeheerderForm extends JFrame {

    private JPanel panel1;
    private JButton BoekWijzigen;
    private JButton BoekToevoegenButton;
    private JButton BoekVerwijderen;
    private JButton Uitlening;
    private JButton reservatieButton;
    private JButton placeholderButton;
    private JButton LezertoevoegenButton;
    private JButton LezerverwijderenButton;
    private JButton wijzigenButton;
    private JButton BoekOpzoeken;
    private JButton opzoekenButton;
    private JButton beheerderToevoegenButton;
    private JLabel labelBoek;
    private JButton UitloggenButton;
    private JFrame beheerderFormFrame = new JFrame("BorrowReadRepeat");

    public BeheerderForm() {

        beheerderFormFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        beheerderFormFrame.getContentPane().add(panel1);
        beheerderFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        beheerderFormFrame.setVisible(true);
        beheerderFormFrame.setSize(600, 600);
        beheerderFormFrame.setResizable(false);
        beheerderFormFrame.setLocationRelativeTo(null);

        UitloggenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(BeheerderForm.this, "Ben je zeker dat je wilt uitloggen?", "Uitloggen", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    new BeheerderLoginForm();
                    beheerderFormFrame.dispose();
                }
            }
        });



        LezertoevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LezerToevoegenForm();
                beheerderFormFrame.dispose();
            }
        });

        LezerverwijderenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LezerVerwijderenForm();
                beheerderFormFrame.dispose();
            }
        });
        BoekVerwijderen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BoekVerwijderenForm();
                beheerderFormFrame.dispose();
            }
        });
        BoekToevoegenButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  new BoekToevoegenForm();
                  beheerderFormFrame.dispose();
            }
        });
        beheerderToevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(BeheerderForm.this, "Ben je zeker?", "Opgepast!", JOptionPane.WARNING_MESSAGE);
                new BeheerderToevoegenForm();
                beheerderFormFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new BeheerderForm();
    }



}


