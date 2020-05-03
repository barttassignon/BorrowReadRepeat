/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {

    private JButton Login;
    private JPanel panelMain;
    private JButton Beheerder;
    private JFrame frame = new JFrame("BorrowReadRepeat");

    public LoginScreen() {
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(panelMain);
        Beheerder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderLoginForm();
                frame.dispose();
            }
        });
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}





