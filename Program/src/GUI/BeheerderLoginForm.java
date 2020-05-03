/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;

public class BeheerderLoginForm {

    private JPanel BeheerderLoginPanel;
    private JTextField userNaam;
    private JPasswordField passwordField;
    private JFrame frame = new JFrame("BorrowReadRepeat");

    public BeheerderLoginForm() {

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(BeheerderLoginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new BeheerderLoginForm();
    }
}


