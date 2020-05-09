/**
 * @Author Bart Tassignon
 */

package GUI;

import db.LezerDAO;
import entity.Lezer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class OpzoekenLezerForm extends JFrame {

    private JLabel OpzoekenLezerJlabel;
    private JLabel voornaamLabel;
    private JTextField voornaamTextField;
    private JLabel naamLabel;
    private JTextField naamTextField;
    private JFrame opzoekenLezerFrame = new JFrame();
    private JPanel panel1;


    private JButton TerugButton;

    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    private JTable jtbl = new JTable(model);


    public static void main(String[] args) {
        new OpzoekenLezerForm();
    }

    public void OpzoekenLezers() {
        cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("LezerId");
        model.addColumn("Voornaam");
        model.addColumn("Naam");
        model.addColumn("Geboortedatum");
        model.addColumn("Emailadres");
        model.addColumn("Telefoon");
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
        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
        this.pack();
    }

    public OpzoekenLezerForm() {


        opzoekenLezerFrame.getContentPane().add(panel1);
        opzoekenLezerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        opzoekenLezerFrame.setVisible(true);
        opzoekenLezerFrame.setSize(600, 600);
        opzoekenLezerFrame.setResizable(false);
        opzoekenLezerFrame.setLocationRelativeTo(null);

        cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("LezerId");
        model.addColumn("Voornaam");
        model.addColumn("Naam");
        model.addColumn("Geboortedatum");
        model.addColumn("Emailadres");
        model.addColumn("Telefoon");
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
        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
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







