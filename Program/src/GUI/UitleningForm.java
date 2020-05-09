package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UitleningForm extends JFrame {

    private JFrame uitleningFrame = new JFrame();
    private JPanel panel1;

    private JLabel uitleningToevoegen;
    private JLabel lezerID;
    private JLabel artikelnummers;

    private JTextField lezerTextField;
    private JTextField artikelTextField1;
    private JTextField artikelTextField2;
    private JTextField artikelTextField3;
    private JTextField artikelTextField4;
    private JTextField artikelTextField5;
    private JTextField artikelTextField6;
    private JTextField artikelTextField7;
    private JTextField artikelTextField8;
    private JTextField artikelTextField9;
    private JTextField artikelTextField10;

    private JButton terugButton;
    private JButton toevoegenButton;

    public UitleningForm() {
        uitleningFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        uitleningFrame.getContentPane().add(panel1);
        uitleningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uitleningFrame.setVisible(true);
        uitleningFrame.setSize(600, 600);
        uitleningFrame.setResizable(false);
        uitleningFrame.setLocationRelativeTo(null);

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                uitleningFrame.dispose();
            }
        });

        toevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                uitleningFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new UitleningForm();
    }
}
