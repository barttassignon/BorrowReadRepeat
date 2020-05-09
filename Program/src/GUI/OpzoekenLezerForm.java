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

public class OpzoekenLezerForm extends JFrame {

    private JFrame opzoekenLezerFrame = new JFrame();
    private JPanel panel1;

    private JLabel OpzoekenLezerJlabel;
    private JLabel voornaamLabel;
    private JLabel naamLabel;

    private JTextField voornaamTextField;
    private JTextField naamTextField;

    private JScrollPane scrollpane;
    DefaultTableModel model = new DefaultTableModel();
    private JTable table1;

    private JButton TerugButton;

    public static void main(String[] args) {
        new OpzoekenLezerForm();
    }

    public OpzoekenLezerForm() {

        opzoekenLezerFrame.getContentPane().add(panel1);
        opzoekenLezerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        opzoekenLezerFrame.setVisible(true);
        opzoekenLezerFrame.setSize(600, 600);
        opzoekenLezerFrame.setResizable(false);
        opzoekenLezerFrame.setLocationRelativeTo(null);

        table1.setAutoCreateRowSorter(true);
        table1.setFillsViewportHeight(true);
        table1.setPreferredScrollableViewportSize(new Dimension(550, 200));
        model.addColumn("LezerId");
        model.addColumn("Voornaam");
        model.addColumn("Naam");
        model.addColumn("Geboortedatum");
        model.addColumn("Emailadres");
        model.addColumn("Telefoon");
        table1.setModel(model);

        try {
            String url = "jdbc:mysql://dt5.ehb.be/1920mobappgr1";
            String user = "1920mobappgr1";
            String password = "XNnhDjw";
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pstm = con.prepareStatement("select * from Lezers");
            ResultSet Rs = pstm.executeQuery();
            while (Rs.next()) {
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getObject(4, LocalDate.class), Rs.getString(5), Rs.getString(6)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        this.pack();

        TerugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BeheerderForm();
                opzoekenLezerFrame.dispose();
            }
        });
    }



}







