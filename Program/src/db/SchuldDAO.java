package db;

import entity.Schuld;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SchuldDAO extends BaseDAO {

    public void aanrekenenSchuld(int lezer_id, Schuld schuld)
    {
        try (Connection c = getConn()) {

            PreparedStatement p = c.prepareStatement("insert into Schulden values (NULL, ?, ?, ?, ?, NULL)");
            p.setInt(1, lezer_id);
            p.setString(2, schuld.getOorsprong().name());
            p.setDouble(3, schuld.getBedrag());
            p.setObject(4, schuld.getDatumAangemaakt());

            int result = p.executeUpdate();

            if (result > 0)
                System.out.println("De schuld werd toegevoegd!");
            else System.out.println("De schuld kon niet worden toegevoegd!");

            // Nog aan te passen: foutmelding laten afhangen van errorcode (bvb.: lezer niet gevonden)

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    public static void betalenSchuld(int lezer_id, double bedrag, LocalDate betaaldatum) {
        try (Connection c = getConn()) {

            PreparedStatement p = c.prepareStatement("update Schulden set Bedrag = ? and Betaaldatum = ? where Lezer_ID = ?");
            p.setDouble(1, -(bedrag));
            if (betaaldatum.isBefore(LocalDate.now().plusDays(1)))
                p.setObject(2, betaaldatum);
            p.setObject(3, lezer_id);

            int result = p.executeUpdate();

            if (result > 0)
                System.out.println("De betaling werd geregistreerd!");
            else System.out.println("De betaling kon niet worden geregistreerd!");

            // Nog aan te passen: foutmelding laten afhangen van errorcode (bvb.: lezer niet gevonden)

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    public static ArrayList<Schuld> overzichtOpenstaandeSchulden() {
        ArrayList<Schuld> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Schulden where Bedrag > 0");
            while (rs.next()) {
                lijst.add(rs.getInt(1), new Schuld(rs.getInt(1), Schuld.Oorsprong.valueOf(rs.getString(2)), rs.getDouble(3), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijst;
    }

    public static ArrayList<Schuld> overzichtSchuldenLezer(int lezerid)
    {
        ArrayList<Schuld> lijst = new ArrayList<>();
        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("select * from Schulden where Lezer_ID = ? and Bedrag > 0");
            s.setInt(1, lezerid);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                //lijst.add(new Schuld(rs.getInt(1), Schuld.Oorsprong.valueOf(rs.getString(2)), rs.getDouble(3), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class)));
                lijst.add(new Schuld(rs.getInt(1), rs.getDouble(4), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijst;
    }

    public static int aantalOpenstaandeSchulden(int lezer){
        int schuld = 0;
        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("select count(Schuld_ID) from Schulden where Lezer_ID = ? and Betaaldatum is null");
            s.setInt(1, lezer);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                schuld = rs.getInt(1);
                }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return schuld;
    }

    public static void main(String[] args) {
        SchuldDAO schuldda = new SchuldDAO();
        //Schuld schuld = new Schuld("Overtijd", 0.5, LocalDate.of(2020, 2, 5));
        //schuldda.aanrekenenSchuld(37, schuld);
        //schuldda.betalenSchuld(37, 0.5, LocalDate.of(2020, 1, 11));
        for(Schuld s: schuldda.overzichtSchuldenLezer(37))
        {
            System.out.println(s);
        }
    }
}
