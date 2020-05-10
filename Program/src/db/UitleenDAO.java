package db;

import entity.Boek;
import entity.Uitlening;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UitleenDAO extends BaseDAO {


    // Een overzicht van alle uitleningen:

    public static ArrayList<Uitlening> ophalenUitleningen() {
        ArrayList<Uitlening> lijstUitlening = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Uitlening");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijstUitlening.add(new Uitlening(rs.getObject(1, LocalDate.class), rs.getObject(2, LocalDate.class), rs.getObject(3, LocalDate.class)));
            }
            System.out.println("GELUKT!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijstUitlening;
    }

}
