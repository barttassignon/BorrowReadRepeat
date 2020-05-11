package db;

import entity.Beheerder;
import entity.Lezer;
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

            PreparedStatement s = c.prepareStatement("insert into Uitleningen values (NULL, ?, ?, NULL, NULL)");
            s.setInt(1, uitlening.getLezer().getId());
            s.setObject(2, uitlening.getDatumUitgeleend());

            int result1 = s.executeUpdate();

            // Tabel winkelmandje updaten:

            int autoIncKeyFromFunc = -1;
            ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");

            if (rs.next())
                autoIncKeyFromFunc = rs.getInt(1);
            else
                System.out.println("Lezer_ID niet gevonden");

            PreparedStatement p = c.prepareStatement("insert into Winkelmandje values (?, ?)");
            p.setInt(1, autoIncKeyFromFunc);
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

// Ophalen alle uitleningen van lezer:

    public ArrayList<Uitlening> uitleenGeschiedenisLezer(String id) {
        ArrayList<Uitlening> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Uitleningen where Lezer_ID = ?");
            s.setString(1, id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijst.add(new Uitlening(rs.getInt(1), rs.getInt(2), rs.getObject(3, LocalDate.class), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }

        return lijst;
    }


}
