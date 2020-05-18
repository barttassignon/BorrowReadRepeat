/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeheerderForm extends JPanel {

    private JFrame beheerderFormFrame = new JFrame("BorrowReadRepeat");
    private JPanel beheerderPanel;

    private JLabel labelLezer;
    private JLabel labelBib;
    private JLabel labelTitel;
    private JLabel labelBoek;

    private JButton boekToevoegenButton;
    private JButton weergevenBoekButton;
    private JButton schuldToevoegenButton;

    private JButton lezertoevoegenButton;
    private JButton wijzigenLezerButton;
    private JButton weergevenLezerButton;
    private JButton schuldOverzichtButton;

    private JButton uitleningButton;
    private JButton reservatieButton;
    private JButton beheerderToevoegenButton;
    private JButton uitloggenButton;

    public BeheerderForm() {

        beheerderFormFrame.getContentPane().add(beheerderPanel);
        beheerderFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        beheerderFormFrame.setVisible(true);
        beheerderFormFrame.setSize(700, 600);
        beheerderFormFrame.setResizable(false);
        beheerderFormFrame.setLocationRelativeTo(null);

        uitloggenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(BeheerderForm.this, "Ben je zeker dat je wilt uitloggen?", "Uitloggen", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    new BeheerderLoginForm();
                    beheerderFormFrame.dispose();
                }
            }
        });

        lezertoevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LezerToevoegenForm();
                beheerderFormFrame.dispose();
            }
        });

        boekToevoegenButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  new BoekToevoegenForm();
                  beheerderFormFrame.dispose();
            }
        });
        beheerderToevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    new BeheerderToevoegenForm();
                    beheerderFormFrame.dispose();

            }
        });
        weergevenLezerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WeergevenLezerForm();
                beheerderFormFrame.dispose();
            }
        });
        weergevenBoekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WeergevenBoekForm();
                beheerderFormFrame.dispose();
            }
        });
        uitleningButton.addActionListener(new ActionListener() {
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

        schuldOverzichtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SchuldForm();
                beheerderFormFrame.dispose();
            }
        });

        wijzigenLezerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WijzigLezerForm();
                beheerderFormFrame.dispose();
            }
        });

        schuldToevoegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SchuldToevoegenForm();
                beheerderFormFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new BeheerderForm();
    }

}

