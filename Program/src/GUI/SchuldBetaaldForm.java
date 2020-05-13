/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchuldBetaaldForm {


    private JLabel SchuldLabel;
    private JTextField lezerNaamField;
    private JLabel lezerLabel;
    private JLabel datumLabel;
    private JTextField datumField;
    private JButton betaalDatum;
    private JLabel bedragLabel;
    private JTextField bedragField;
    private JButton TerugButton;
    private JPanel panel1;
    private JFrame schuldBetaaldFrame = new JFrame();

    public SchuldBetaaldForm() {

        schuldBetaaldFrame.getContentPane().add(panel1);
        schuldBetaaldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        schuldBetaaldFrame.setVisible(true);
        schuldBetaaldFrame.setSize(600, 600);
        schuldBetaaldFrame.setResizable(false);
        schuldBetaaldFrame.setLocationRelativeTo(null);

        betaalDatum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SchuldForm();
                schuldBetaaldFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new SchuldBetaaldForm();
    }
}



