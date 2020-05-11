package db;

import entity.Beheerder;
import entity.Boek;
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

        try (Connection c = getConn()) {

            // Tabel uitlening updaten:

            PreparedStatement s = c.prepareStatement("insert into Uitleningen values (NULL, ?, ?, ?, NULL, NULL)");
            s.setInt(1, uitlening.getLezer().getId());
            s.setInt(2, uitlening.getBoek().getArtikelnummer());
            s.setObject(3, uitlening.getDatumUitgeleend());

            int result1 = s.executeUpdate();

            if (result1 > 0)
                System.out.println("Uitlening toegevoegd!");
            else System.out.println("De uitlening kon niet worden toegevoegd!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    // Werkt nog niet => LocalDate.now().

    public static void verlengUitlening(int uitleenID){
        try (Connection c = getConn()) {

            // Tabel uitlening updaten:

            PreparedStatement s = c.prepareStatement("update Uitleningen set Verlengdatum = LocalDate.now() where Uitleen_ID = ?");
            s.setInt(1, uitleenID);

            int result1 = s.executeUpdate();

            if (result1 > 0)
                System.out.println("Verlenging toegevoegd!");
            else System.out.println("De verlenging kon niet worden toegevoegd!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    // Een overzicht van alle uitleningen:

    public static ArrayList<Uitlening> ophalenUitleningen() {
        ArrayList<Uitlening> lijstUitlening = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Uitleningen");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijstUitlening.add(new Uitlening(new Lezer(rs.getInt(2)), new Boek(rs.getInt(3)), rs.getInt(1), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class)));
            }
            System.out.println("GELUKT!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijstUitlening;
    }

// Ophalen alle uitleningen van lezer:

    public static ArrayList<Uitlening> uitleengeschiedenisLezer(int lezerID) {
        ArrayList<Uitlening> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Uitleningen where Lezer_ID = ?");
            s.setInt(1, lezerID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijst.add(new Uitlening(new Lezer(rs.getInt(2)), new Boek(rs.getInt(3)), rs.getInt(1), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }

        return lijst;
    }


}
