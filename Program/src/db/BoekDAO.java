package db;

import entity.Boek;
import entity.BoekNietGevonden;
import entity.Kinderboek;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class BoekDAO extends BaseDAO {

    // Toevoegen van boek

    public static void toevoegenBoek(Boek boek) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("insert into Boeken values (NULL, ?, ?, ?, ?, ?, ?, false, ?, ?, ?, ?, false, false, 0)");
            s.setLong(1, boek.getISBN());
            s.setString(2, boek.getTitel());
            s.setString(3, boek.getAuteur());
            s.setString(4, boek.getUitgeverij());
            s.setString(5, boek.getTaal1());
            s.setString(6, boek.getGenre1());
            s.setInt(7, boek.getPaginas());
            s.setObject(8, boek.getAankoopdatumDB());
            s.setDouble(9, boek.getPrijs());
            s.setString(10, boek.getPlaatsInBib());

            // Aanpassen zodat CSV-bestand kan worden ingelezen en afgelopen met while-loop om gegevens in batch toe te voegen
            // Foutmelding toevoegen indien gegevens reeds in de databank zitten ("boek bestaat reeds")

            int result = s.executeUpdate();
            if (result > 0)
                System.out.println("Boek werd toegevoegd!");
            else System.out.println("Boek werd niet toegevoegd!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }
    // Toevoegen kinderboek:

    public static void toevoegenBoek(Kinderboek boek) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("insert into Boeken values (NULL, ?, ?, ?, ?, ?, ?, true, ?, ?, ?, ?, false, false, 0)");
            s.setLong(1, boek.getISBN());
            s.setString(2, boek.getTitel());
            s.setString(3, boek.getAuteur());
            s.setString(4, boek.getUitgeverij());
            s.setString(5, boek.getTaal1());
            s.setString(6, boek.getGenre1());
            s.setInt(7, boek.getPaginas());
            s.setObject(8, boek.getAankoopdatumDB());
            s.setDouble(9, boek.getPrijs());
            s.setString(10, boek.getPlaatsInBib());

            // Aanpassen zodat CSV-bestand kan worden ingelezen en afgelopen met while-loop om gegevens in batch toe te voegen
            // Foutmelding toevoegen indien gegevens reeds in de databank zitten ("boek bestaat reeds")

            int result = s.executeUpdate();
            if (result > 0)
                System.out.println("Kinderboek werd toegevoegd!");
            else System.out.println("Kinderboek werd niet toegevoegd!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    // geef alle boeken weer

    public ArrayList<Boek> ophalenBoeken() {
        ArrayList<Boek> lijstBoeken = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Boeken");
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                lijstBoeken.add(new Boek(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4)));
            }
            System.out.println("GELUKT!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijstBoeken;
    }

    // zoek boek  :

    public ArrayList<Boek> opzoekenBoek (String titel){
        ArrayList<Boek> lijst = new ArrayList<>();
        try (Connection c = getConn()){
            PreparedStatement s = c.prepareStatement("select + from Boek where titel = ?");
            s.setString(2, titel);
            ResultSet rs = s.executeQuery();
            while (rs.next()){
                lijst.add(new Boek(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4)));
            }

            } catch (SQLException e){
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijst;
    }

    // verwijder boek :

    public static void verwijderenBoek(int artikelnummer) throws BoekNietGevonden {
        try {
            Connection c = getConn();
            PreparedStatement s = c.prepareStatement("delete from Boeken where Boek_ID = ?");
            s.setInt(1, artikelnummer);
            int result = s.executeUpdate();
            if (result > 0) {
                System.out.println("Het boek werd verwijderd");
            } else {
                System.out.println("Er bestaat geen boek met dit artikelnummer");
                throw new BoekNietGevonden();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

        public static void main(String[] args)  {

     //   BoekDAO bda = new BoekDAO();
     //   Kinderboek b1 = new Kinderboek (65498232L, "Titel", "Auteur", "Uitgeverij", Boek.Taal.NEDERLANDS, Boek.Genre.GEZONDHEID, 123, LocalDate.of(2000, 11, 17), 12.35, "AUT" );
     //   bda.toevoegenBoek(b1);

    }

}
