/**
 * @Author Bart Tassignon
 */

package GUI;

import db.LezerDAO;
import entity.Lezer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class OpzoekenLezerForm extends JFrame {


    private JLabel OpzoekenLezerJlabel;
    private JLabel voornaamLabel;
    private JTextField voornaamTextField;
    private JLabel naamLabel;
    private JTextField naamTextField;
    private JFrame opzoekenLezerFrame = new JFrame();
    private JPanel panel1;
    private JTable weergevenLezersJtable;

    public OpzoekenLezerForm() {



        opzoekenLezerFrame.getContentPane().add(panel1);
        opzoekenLezerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        opzoekenLezerFrame.setVisible(true);
        opzoekenLezerFrame.setSize(600, 600);
        opzoekenLezerFrame.setResizable(false);
        opzoekenLezerFrame.setLocationRelativeTo(null);
        showUser();






    }
    public ArrayList<Lezer> lezersList(){
        LezerDAO l1 = new LezerDAO();


        return l1.ophalenLezers();
    }

    public void showUser(){
        ArrayList<Lezer> lijst = lezersList();
        DefaultTableModel model = (DefaultTableModel)weergevenLezersJtable.getModel();
        Object[] row = new Object[5];
        for (int i=0; i<lijst.size(); i++) {
            row[0] = lijst.get(i).getId();
            row[1] = lijst.get(i).getVoornaam();
            row[2] = lijst.get(i).getNaam();
            row[3] = lijst.get(i).getGeboortedatum();
            row[4] = lijst.get(i).getEmail();
            row[5] = lijst.get(i).getTelefoon();
            model.addRow(row);


        }
        System.out.println(row[0]);

    }






    public static void main(String[] args) {
        new OpzoekenLezerForm();



    }
}


