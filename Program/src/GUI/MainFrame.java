/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JFrame mainFrame = new JFrame("Borrow Read Repeat");
    private JPanel frame2 = new JPanel();

    public MainFrame() {


        mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainFrame.getContentPane().add(frame2);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setSize(700, 600);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);


    }

    public void AddFrame(JPanel frame) {
        this.frame2 = frame;


    }

    public static void main(String[] args) {
        new MainFrame();
    }


}


