package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UitleningForm {

    private JFrame uitleningFrame = new JFrame();
    private JPanel panel1;

    private JLabel uitleningToevoegen;

    private JButton toevoegenButton;
    private JButton binnenbrengenButton;
    private JButton verlengButton;
    private JButton terugButton;

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
            @Override
            public void actionPerformed(ActionEvent e) {
                new UitleningToevoegenForm();
                uitleningFrame.dispose();
            }
        });

        binnenbrengenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UitleningBinnenbrengenForm();
                uitleningFrame.dispose();
            }
        });

        verlengButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UitleningVerlengenForm();
                uitleningFrame.dispose();
            }
        });

    }

    public static void main(String[] args) {
        new UitleningForm();
    }

}
