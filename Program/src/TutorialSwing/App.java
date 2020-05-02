/**
 * @Author Bart Tassignon
 */

package TutorialSwing;

import javax.swing.*;

public class App extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            // moest je aan multi-threading doen in Swing applicatie, 'better safe than sorry'
            // meer robuuste manier om swing te gebruiken
            public void run() {
                new MainFrame();



            }
        });



    }

}


