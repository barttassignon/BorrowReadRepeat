package db;

import entity.Boek;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BoekDAO extends BaseDAO {

    // Toevoegen van boek aan database:

    public static void toevoegenBoek(Boek boek) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("insert into Boeken values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false, false, false)");
            s.setLong(1, boek.getISBN());
            s.setString(2, boek.getTitel());
            s.setString(3, boek.getAuteur());
            s.setString(4, boek.getUitgeverij());
            s.setString(5, boek.getTaal1());
            s.setString(6, boek.getGenre1());
            s.setBoolean(7, boek.isKinderboek());
            s.setInt(8, boek.getPaginas());
            s.setObject(9, boek.getAankoopdatum());
            s.setDouble(10, boek.getPrijs());
            s.setString(11, boek.getPlaatsInBib());
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Een overzicht van alle boeken in stock:

    public static ArrayList<Boek> ophalenBoeken() {
        ArrayList<Boek> lijstBoeken = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Boeken where UitStock = false");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijstBoeken.add(new Boek(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(9), rs.getObject(10, LocalDate.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijstBoeken;
    }

    // Ophalen boek op basis van ID (voor uitlening/reservatie):

    public static Boek ophalenBoek(int boekID){
        Boek boek = null;
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Boeken where Boek_ID = ?");
            s.setInt(1, boekID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                boek = new Boek(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(9), rs.getObject(10, LocalDate.class), rs.getDouble(11), rs.getString(12), rs.getBoolean(13), rs.getBoolean(14), rs.getBoolean(15), rs.getBoolean(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boek;
    }

    // Boek opzoeken op titel :

    public static ArrayList<Boek> opzoekenBoek(String titel){
        ArrayList<Boek> lijst = new ArrayList<>();
        try (Connection c = getConn()){
            PreparedStatement s = c.prepareStatement("select * from Boeken where Titel LIKE ? and UitStock = false");
            s.setString(1, "%"+titel+"%");
            ResultSet rs = s.executeQuery();
            while (rs.next()){
                lijst.add(new Boek(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(9), rs.getObject(10, LocalDate.class), rs.getDouble(11), rs.getString(12), rs.getBoolean(13), rs.getBoolean(14), rs.getBoolean(15), rs.getBoolean(8)));
            }

            } catch (SQLException e){
            e.printStackTrace();
        }
        return lijst;
    }

    // Boek uit stock halen  :

    public static void verwijderenBoek(int boekID) {
        try {
            Connection c = getConn();
            PreparedStatement s = c.prepareStatement("update Boeken set Uitstock = true where Boek_ID = ?");
            s.setInt(1, boekID);
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // methode om status boek te wijzigen naar gereserveerd:

    public static void isGereserveerd(int boekID) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("update Boeken set isGereserveerd = true where Boek_ID = ?");
            s.setInt(1, boekID);
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // methode om status boek te wijzigen naar niet gereserveerd:

    public static void nietGereserveerd(int boekID) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("update Boeken set isGereserveerd = false where Boek_ID = ?");
            s.setInt(1, boekID);
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // methode om status boek te wijzigen naar uitgeleend:

    public static void isUitgeleend(int boekID) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("update Boeken set isUitgeleend = true where Boek_ID = ?");
            s.setInt(1, boekID);
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // methode om status boek te wijzigen naar niet uitgeleend:

    public static void nietUitgeleend(int boekID) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("update Boeken set isUitgeleend = false where Boek_ID = ?");
            s.setInt(1, boekID);
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
