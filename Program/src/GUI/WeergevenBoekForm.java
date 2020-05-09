/**
 * @Author Bart Tassignon
 */

package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class WeergevenBoekForm extends JFrame{

    private JFrame weergevenBoekFrame = new JFrame();

    private JLabel weergevenBoekenLabel;
    private JPanel panel1;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();

    private JTable table1;

    private JButton alleBoekenButton;
    private JButton TerugButton;

    public static void main(String[] args) {
        new WeergevenBoekForm();
    }

    public WeergevenBoekForm() {

        weergevenBoekFrame.getContentPane().add(panel1);
        weergevenBoekFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weergevenBoekFrame.setVisible(true);
        weergevenBoekFrame.setSize(600, 600);
        weergevenBoekFrame.setResizable(false);
        weergevenBoekFrame.setLocationRelativeTo(null);
        this.pack();

        TerugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();

            }
        });
        alleBoekenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table1.setAutoCreateRowSorter(true);
                table1.setFillsViewportHeight(true);
                table1.setPreferredScrollableViewportSize(new Dimension(550, 200));
                model.addColumn("BoekID");
                model.addColumn("ISBN");
                model.addColumn("Titel");
                model.addColumn("Auteur");
                model.addColumn("Uitgeverij");
                model.addColumn("AantalBlz");
                model.addColumn("Aankoopdatum");
                table1.setModel(model);

                try {
                    String url = "jdbc:mysql://dt5.ehb.be/1920mobappgr1";
                    String user = "1920mobappgr1";
                    String password = "XNnhDjw";
                    Connection con = DriverManager.getConnection(url, user, password);
                    PreparedStatement pstm = con.prepareStatement("select * from Boeken");
                    ResultSet Rs = pstm.executeQuery();
                    while (Rs.next()) {
                        model.addRow(new Object[]{Rs.getInt(1), Rs.getInt(2), Rs.getString(3), Rs.getString(4), Rs.getString(5), Rs.getInt(9),Rs.getObject(10, LocalDate.class)});
                    }
                } catch (Exception ev) {
                    System.out.println(ev.getMessage());
                }

            }
        });
    }
}


