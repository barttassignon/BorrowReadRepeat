/**
 * @Author Bart Tassignon
 */

package TutorialSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private TextPanel textpanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;

    public MainFrame(){
        super("BorrowReadRepeat");

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        //btn = new JButton("ADMIN LOGIN");
        textpanel = new TextPanel();
        formPanel = new FormPanel();

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

        toolbar.setStringListener(new StringListener() {
            public void textEmitted(String text) {
                textpanel.appendText(text);
            }
        });

        formPanel.setFormListener(new FormListener() {
            public void FormEventOccured(FormEvent e) {
                String name = e.getName();
                String voornaam = e.getVoornaam();
                String occupation = e.getOccupation();
                int ageCat = e.getAgeCategory();
                String empCat = e.getEmpCat();
                String gender = e.getGender();

                textpanel.appendText(name + ", " + voornaam + ", " + occupation + ", " + ageCat + ", " + empCat + ", " + gender + "\n");
            }
        });

       /* btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textpanel.appendText("Hello\n");
            }
        });*/

        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(textpanel, BorderLayout.CENTER);
        //add(btn, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(650, 500));
        setSize(700,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");


        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();

                formPanel.setVisible(menuItem.isSelected());
            }
        });

        fileMenu.setMnemonic(KeyEvent.VK_F); // als je alt-F intypt opent je File menu
        exitItem.setMnemonic(KeyEvent.VK_X); // als je alt-X intypt exit je

        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK)); //CTRL-X toevoegen om programma te exitten

        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    System.out.println(fileChooser.getSelectedFile());
                }
            }
        });

        exportDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    System.out.println(fileChooser.getSelectedFile());
                }
            }
        });

        // ok om te exitten, bij cancel w er niets uitgevoerd
        exitItem.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {

//                 String text = JOptionPane.showInputDialog(MainFrame.this, "Enter your user name.",
//                         "Enter User Name", JOptionPane.OK_OPTION|JOptionPane.QUESTION_MESSAGE); // om icoon te veranderen gebruik van |

                int action =  JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
               if (action == JOptionPane.OK_OPTION) {
                   System.exit(0);
               }
            }
        });

        return menuBar;
    }
}


