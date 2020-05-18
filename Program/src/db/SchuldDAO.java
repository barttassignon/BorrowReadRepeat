package db;

import entity.Lezer;
import entity.Schuld;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SchuldDAO extends BaseDAO {

    public static void overtijdSchuld(int lezerID, double schuld){

        LocalDateTime datum = LocalDate.now().atTime(02,00,00);

        try (Connection c = getConn()) {

            PreparedStatement p = c.prepareStatement("insert into Schulden values (NULL, ?, ?, ?, ?, NULL)");
            p.setInt(1, lezerID);
            p.setString(2, "Overtijd");
            p.setDouble(3, schuld);
            p.setObject(4, datum);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void aanrekenenSchuld(int lezerID, Schuld schuld) {

        LocalDateTime datum = LocalDate.now().atTime(02,00,00);

        try (Connection c = getConn()) {

            PreparedStatement p = c.prepareStatement("insert into Schulden values (NULL, ?, ?, ?, ?, NULL)");
            p.setInt(1, lezerID);
            p.setString(2, schuld.getOorsprong());
            p.setDouble(3, schuld.getBedrag());
            p.setObject(4, datum);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void betalenSchuld(int schuldID) {

        LocalDateTime datum = LocalDate.now().atTime(02,00,00);

        try (Connection c = getConn()) {

            PreparedStatement p = c.prepareStatement("update Schulden set Bedrag = ?, Betaaldatum = ? where Schuld_ID = ?");
            p.setDouble(1, 0);
            p.setObject(2, datum);
            p.setInt(3, schuldID);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Schuld> overzichtOpenstaandeSchulden() {
        ArrayList<Schuld> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Schulden where Betaaldatum is null");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijst.add(new Schuld(new Lezer(rs.getInt(2)), rs.getInt(1), rs.getString(3), rs.getDouble(4), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    public static ArrayList<Schuld> overzichtSchuldenLezer(int lezerID)
    {
        ArrayList<Schuld> lijst = new ArrayList<>();
        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("select * from Schulden where Lezer_ID = ? and Betaaldatum is null");
            s.setInt(1, lezerID);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                lijst.add(new Schuld(new Lezer(rs.getInt(2)), rs.getInt(1), rs.getString(3), rs.getDouble(4), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    // Methode om na te gaan of een lezer nog openstaande schulden heeft (want dan mag hij niet verwijderd worden uit de DB!):

    public static int aantalOpenstaandeSchulden(int lezerID){
        int schuld = 0;
        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("select count(Schuld_ID) from Schulden where Lezer_ID = ? and Betaaldatum is null");
            s.setInt(1, lezerID);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                schuld = rs.getInt(1);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schuld;
    }
}
