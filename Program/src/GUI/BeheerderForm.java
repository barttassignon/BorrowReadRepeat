/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeheerderForm extends JFrame{


    private JPanel panel1;
    private JButton BoekWijzigen;
    private JButton boekToevoegen;
    private JButton BoekVerwijderen;
    private JButton Uitlening;
    private JButton reservatieButton;
    private JButton placeholderButton;
    private JButton toevoegenButton;
    private JButton verwijderenButton;
    private JButton wijzigenButton;
    private JButton BoekOpzoeken;
    private JButton opzoekenButton;
    private JButton placeholderButton1;
    private JLabel labelBoek;
    private JButton UitloggenButton;
    private JFrame beheerderFormFrame = new JFrame("BorrowReadRepeat");


    public BeheerderForm() {
        beheerderFormFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        beheerderFormFrame.getContentPane().add(panel1);
        beheerderFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        beheerderFormFrame.setVisible(true);
        beheerderFormFrame.setSize(600,600);
        beheerderFormFrame.setMinimumSize(new Dimension(600,600));
        beheerderFormFrame.setMaximumSize(new Dimension(600,600));
        beheerderFormFrame.setLocationRelativeTo(null);

        UitloggenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
                beheerderFormFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new BeheerderForm();

    }


}


