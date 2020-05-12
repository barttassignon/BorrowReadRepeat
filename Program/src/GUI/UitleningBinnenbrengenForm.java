package GUI;

import db.BoekDAO;
import db.UitleenDAO;
import entity.Uitlening;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UitleningBinnenbrengenForm {

    private JFrame uitleningBinnenbrengenFrame = new JFrame();
    private JPanel panel1;

    private JLabel uitleningBinnenbrengen;
    private JLabel artikelnummers;

    private JTextField artikelTextField1;

    private JButton binnenbrengenButton;
    private JButton terugButton;


    public UitleningBinnenbrengenForm() {
        uitleningBinnenbrengenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        uitleningBinnenbrengenFrame.getContentPane().add(panel1);
        uitleningBinnenbrengenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uitleningBinnenbrengenFrame.setVisible(true);
        uitleningBinnenbrengenFrame.setSize(600, 300);
        uitleningBinnenbrengenFrame.setResizable(false);
        uitleningBinnenbrengenFrame.setLocationRelativeTo(null);

        terugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UitleningForm();
                uitleningBinnenbrengenFrame.dispose();
            }
        });

        binnenbrengenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int boeknummer = Integer.parseInt(artikelTextField1.getText());
                    UitleenDAO.binnenbrengenUitlening(boeknummer);
                    BoekDAO.nietUitgeleend(boeknummer);
                    JOptionPane.showMessageDialog(null, "Het boek is terug binnengebracht!");
                } catch (NumberFormatException nr){
                    JOptionPane.showMessageDialog(null, "Gelieve een geldig boeknummer in te geven!");
                }
            }
        });

    }

    public static void main(String[] args) {
        new UitleningBinnenbrengenForm();
    }
}
