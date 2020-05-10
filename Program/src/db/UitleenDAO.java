package db;

import entity.Uitlening;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UitleenDAO extends BaseDAO {

    // Een uitlening toevoegen:

    public static void uitleningToevoegen(Uitlening uitlening) {

        byte[] salt = Security.createSalt();
        try (Connection c = getConn()) {

            // Tabel uitlening updaten:

            PreparedStatement s = c.prepareStatement("insert into Uitleningen values (?, ?, ?, NULL, NULL)");
            s.setInt(1, uitlening.getUitleen_ID());
            s.setInt(2, uitlening.getLezer().getId());
            s.setObject(3, uitlening.getDatumUitgeleend());

            int result1 = s.executeUpdate();

            // Tabel winkelmandje updaten:

            PreparedStatement p = c.prepareStatement("insert into Winkelmandje values (?, ?)");
            p.setInt(1, uitlening.getUitleen_ID());
            p.setInt(2, uitlening.getBoek().getArtikelnummer());

            int result2 = p.executeUpdate();

            if (result1 > 0 && result2 > 0)
                System.out.println("Uitlening toegevoegd!");
            else System.out.println("De uitlening kon niet worden toegevoegd!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }


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
