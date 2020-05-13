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
    private JButton Uitlening;
    private JButton reservatieButton;
    private JButton SchuldButton;
    private JButton LezertoevoegenButton;
    private JButton wijzigenButton;
    private JButton weergevenBoek;
    private JButton weergevenButton;
    private JButton beheerderToevoegenButton;
    private JLabel labelBoek;
    private JButton UitloggenButton;
    private JTabbedPane tabbedPane1;
    private JButton schuldenButton;
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

        BoekToevoegenButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  new BoekToevoegenForm();
                  beheerderFormFrame.dispose();
            }
        });
        beheerderToevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(BeheerderForm.this, "Wil je een beheerder toevoegen?", "Opgepast!", JOptionPane.WARNING_MESSAGE);
                new BeheerderToevoegenForm();
                beheerderFormFrame.dispose();
            }
        });
        weergevenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WeergevenLezerForm();
                beheerderFormFrame.dispose();
            }
        });
        weergevenBoek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WeergevenBoekForm();
                beheerderFormFrame.dispose();
            }
        });
        Uitlening.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UitleningForm();
                beheerderFormFrame.dispose();
            }
        });

        reservatieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservatieForm();
                beheerderFormFrame.dispose();
            }
        });

        SchuldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SchuldForm();
                beheerderFormFrame.dispose();
            }
        });

        wijzigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WijzigLezerForm();
                beheerderFormFrame.dispose();
            }
        });

    }

    public static void main(String[] args) {
        new BeheerderForm();
    }

}


