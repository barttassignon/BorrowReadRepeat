package db;

/**
 * @author Katrien Persoons
 */

import entity.Adres;
import entity.Beheerder;
import entity.Lezer;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class LezerDAO extends BaseDAO {

    // Een lezer toevoegen

    public static void toevoegenLezer(Lezer lezer) {


        if (lezer.berekenLeeftijd() < 12) {
            System.out.println("De lezer kan niet worden toegevoegd. Hij/zij voldoet niet aan de vereiste leeftijdsvoorwaarde (min. 12 jaar).");
        } else {

            byte[] salt = Security.createSalt();
            try (Connection c = getConn()) {

                // Eerst tabel Adressen updaten (parent)

                PreparedStatement s = c.prepareStatement("insert into Adressen values (NULL, ?, ?, ?, ?, ?)");
                s.setString(1, lezer.getAdres().getStraatnaam());
                s.setInt(2, lezer.getAdres().getHuisnummer());
                s.setString(3, lezer.getAdres().getBus());
                s.setInt(4, lezer.getAdres().getPostcode());
                s.setString(5, lezer.getAdres().getWoonplaats());

                // Gegenereerde Adres_ID (door middel van auto increment) hergebruiken in tabel Lezers (om te voldoen aan foreign key constraint)

                PreparedStatement p = c.prepareStatement("insert into Lezers values (NULL, ?, ?, ?, LAST_INSERT_ID(), ?, ?, ?, ?)");
                p.setString(1, lezer.getVoornaam());
                p.setString(2, lezer.getNaam());
                p.setObject(3, lezer.getGeboortedatum());
                p.setString(4, lezer.getEmail());
                p.setString(5, lezer.getTelefoon());
                p.setBytes(6, salt);
                p.setString(7, Security.generateHash(lezer.getWachtwoord(), salt));

                // Aanpassen zodat CSV-bestand kan worden ingelezen en afgelopen met while-loop om gegevens in batch toe te voegen

                int result1 = s.executeUpdate();
                int result2 = p.executeUpdate();
                if (result1 > 0 && result2 > 0)
                    System.out.println("De lezer werd toegevoegd!");
                else System.out.println("De lezer kon niet worden toegevoegd!");

                // Nog aan te passen: foutmelding laten afhangen van errorcode (bvb.: lezer bestaat reeds)

            } catch (SQLException | NoSuchAlgorithmException e) {
                e.printStackTrace();
                System.out.println("MISLUKT!");
            }
        }
    }

    // Een overzicht van alle lezers:

     public ArrayList<Lezer> ophalenLezers() {
        ArrayList<Lezer> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Lezers");
            while (rs.next()) {
                lijst.add(new Lezer(rs.getString(2), rs.getString(3), rs.getObject(4, LocalDate.class), rs.getString(6), rs.getString(7)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijst;
    }

    // Opzoeken lezer

//    public ArrayList<Lezer> opzoekenLezer (String voornaam, String naam) {
//        ArrayList<Lezer> lijst = new ArrayList<>();
//        try (Connection c = getConn()) {
//            PreparedStatement s = c.prepareStatement("select * from Lezers where voornaam = ? AND naam = ?");
//            s.setString(1, voornaam);
//            s.setString(2, naam);
//            ResultSet rs = s.executeQuery();
//            while (rs.next()) {
//                lijst.add(new Lezer(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getObject(4, LocalDate.class), rs.getString(5)));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("MISLUKT!");
//        }
//
//        return lijst;
//    }

    // Verwijderen lezer: werkt nog niet
   // opgelet: kan enkel als er geen schulden meer zijn
    //Eerst adres verwijderen en dan pas lezer zelf!

//    public void verwijderenLezer(int id) {
//        try (Connection c = getConn()) {
//            //PreparedStatement s = c.prepareStatement("delete Adressen where Lezers.Adres_ID = Adressen.Adres_ID");
//            PreparedStatement s = c.prepareStatement("delete from Lezers where Lezer_ID = ?");
//
//            // Mag wrs ook een gewoon statement zijn ipv een prepared statement
//            // Eerst adres verwijderen, dan pas lezer
//
//            s.setInt(1, id);
//
//            int result = s.executeUpdate();
//            if (result > 0)
//                System.out.println("De lezer werd verwijderd!");
//            else System.out.println("Lezer niet gevonden");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("MISLUKT!");
//        }
    //}

        public static void main(String[] args) {
        LezerDAO lda = new LezerDAO();

        //Lezer l1 = new Lezer("Jan", "Modaal", LocalDate.of(2000, Month.MAY, 15), "jan.modaal@hotmail.com", "0485/15.12.24", "hallo");
        //l1.setAdres(new Adres("Schoolstraat", 15, "", 1000, "Brussel"));
        //lda.toevoegenLezer(l1);
        //lda.ophalenLezers();

        //for(Lezer l : lda.ophalenLezers()){
        //    System.out.println(l.toString());
        //}
        //lda.verwijderenLezer(6);

           // for (Lezer l: lda.opzoekenLezer("Jan", "Modaal"))
               // System.out.println(l.toString());

    }
}
