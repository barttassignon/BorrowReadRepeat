package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchuldToevoegenForm {

    private JFrame schuldToevoegenFormFrame = new JFrame();
    private JPanel panel1;

    private JLabel uitleningToevoegen;
    private JLabel lezerID;
    private JLabel boekID;

    private JTextField lezerTextField;
    private JTextField boekTextField;

    private JButton terugButton;
    private JButton verlorenButton;
    private JButton beschadigdButton;

    public SchuldToevoegenForm() {
        schuldToevoegenFormFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        schuldToevoegenFormFrame.getContentPane().add(panel1);
        schuldToevoegenFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        schuldToevoegenFormFrame.setVisible(true);
        schuldToevoegenFormFrame.setSize(700, 600);
        schuldToevoegenFormFrame.setResizable(false);
        schuldToevoegenFormFrame.setLocationRelativeTo(null);

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                schuldToevoegenFormFrame.dispose();
            }
        });


    }

    public static void main(String[] args) { new SchuldToevoegenForm(); }
}
