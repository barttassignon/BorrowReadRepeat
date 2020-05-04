/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoekToevoegenForm extends JFrame{


    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JButton ToevoegenButton;
    private JButton TerugButton;
    private JFrame boekToevoegenFrame = new JFrame("BorrowReadRepeat");

    public BoekToevoegenForm() {
        boekToevoegenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        boekToevoegenFrame.getContentPane().add(panel1);
        boekToevoegenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        boekToevoegenFrame.setVisible(true);
        boekToevoegenFrame.setSize(600, 600);
        boekToevoegenFrame.setMinimumSize(new Dimension(600, 600));
        boekToevoegenFrame.setMaximumSize(new Dimension(600, 600));
        boekToevoegenFrame.setLocationRelativeTo(null);

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            new BeheerderForm();
            boekToevoegenFrame.dispose();
            }
        });
        ToevoegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new BoekToevoegenForm();

    }
}


