package db;

/**
 * @author Katrien Persoons
 */

import entity.Adres;
import entity.Beheerder;
import entity.Lezer;
import entity.LezerTeJong;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class LezerDAO extends BaseDAO {

    // Een lezer toevoegen

    public static void toevoegenLezer(Lezer lezer) throws LezerTeJong, SQLIntegrityConstraintViolationException {

        if (lezer.berekenLeeftijd() < 12) {
            System.out.println("De lezer kan niet worden toegevoegd. Hij/zij voldoet niet aan de vereiste leeftijdsvoorwaarde (min. 12 jaar).");
            throw new LezerTeJong();
        } else {

            byte[] salt = Security.createSalt();
            try (Connection c = getConn()) {

                // Eerst tabel Lezers updaten (parent)

                PreparedStatement p = c.prepareStatement("insert into Lezers values (NULL, ?, ?, ?, ?, ?, ?, ?)");
                p.setString(1, lezer.getVoornaam());
                p.setString(2, lezer.getNaam());
                p.setObject(3, lezer.getGeboortedatum());
                p.setString(4, lezer.getEmail());
                p.setString(5, lezer.getTelefoon());
                p.setBytes(6, salt);
                p.setString(7, Security.generateHash(lezer.getWachtwoord(), salt));

                int result1 = p.executeUpdate();

                //Gegenereerde Lezer_ID (door middel van auto increment) hergebruiken in tabel Adressen (om te voldoen aan foreign key constraint)

                int autoIncKeyFromFunc = -1;
                ResultSet rs = p.executeQuery("SELECT LAST_INSERT_ID()");

                if (rs.next())
                    autoIncKeyFromFunc = rs.getInt(1);
                else
                    System.out.println("Lezer_ID niet gevonden");

                PreparedStatement s = c.prepareStatement("insert into Adressen values (NULL, ?, ?, ?, ?, ?, ?)");
                s.setInt(1, autoIncKeyFromFunc);
                s.setString(2, lezer.getAdres().getStraatnaam());
                s.setInt(3, lezer.getAdres().getHuisnummer());
                s.setString(4, lezer.getAdres().getBus());
                s.setInt(5, lezer.getAdres().getPostcode());
                s.setString(6, lezer.getAdres().getWoonplaats());

                int result2 = s.executeUpdate();

                if (result1 > 0 && result2 > 0)
                    System.out.println("De lezer werd toegevoegd!");
                else System.out.println("De lezer kon niet worden toegevoegd!");

                // Nog aan te passen: foutmelding laten afhangen van errorcode (bvb.: lezer bestaat reeds)

            } catch (SQLException | NoSuchAlgorithmException e) {
                e.printStackTrace();
                System.out.println("MISLUKT!");
                throw new SQLIntegrityConstraintViolationException();
            }
        }
    }

    // Nog uit te werken: toevoegen lezers: via PrepareStatement

    // Een overzicht van alle lezers:



     public ArrayList<Lezer> ophalenLezers() {
        ArrayList<Lezer> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Lezers");
            while (rs.next()) {
                //lijst.add(new Lezer(rs.getString(2), rs.getString(3), rs.getObject(4, LocalDate.class), rs.getString(6), rs.getString(7)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijst;
    }

    // Opzoeken lezer

     ArrayList<Lezer> opzoekenLezer (String voornaam, String naam) {
      ArrayList<Lezer> lijst = new ArrayList<>();

         try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select Lezer_ID, Voornaam, Naam, Geboortedatum, Emailadres from Lezers where voornaam = ? AND naam = ?");
            s.setString(1, voornaam);
            s.setString(2, naam);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijst.add(new Lezer(rs.getString(2), rs.getString(3), rs.getInt(1), rs.getObject(4, LocalDate.class), rs.getString(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }

         return lijst;
    }

    // Verwijderen lezer: werkt maar kan enkel als er geen schulden meer zijn -> nog toe te voegen

        public void verwijderenLezer(int id) {
        try (Connection c = getConn()) {
        PreparedStatement s = c.prepareStatement("delete from Adressen where Lezer_ID = ?");
            s.setInt(1, id);
        PreparedStatement p = c.prepareStatement("delete from Lezers where Lezer_ID = ?");
            p.setInt(1, id);

            int result1 = s.executeUpdate();
            int result2 = p.executeUpdate();

            if (result1 > 0 && result2 > 0)
                System.out.println("De lezer werd verwijderd!");
            else System.out.println("Lezer niet gevonden");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

        public static void main(String[] args) {
        LezerDAO lda = new LezerDAO();
        //Lezer l1 = new Lezer("Maxim", "De Boeck", LocalDate.of(1980, Month.JUNE, 5), "maxim20@gmail.com", "0479/25.18.13", "voornaam");
        //l1.setAdres(new Adres("Dorp", 255, "", 9300, "Aalst"));
        //lda.toevoegenLezer(l1);
        //lda.ophalenLezers();

        //for(Lezer l : lda.ophalenLezers()){
        //    System.out.println(l.toString());
        //}
        //lda.verwijderenLezer(16);

            if(lda.opzoekenLezer("Helena", "De Munck").size() == 0) {
                System.out.println("Geen lezers gevonden met deze naam");
            }
            else {
                for (Lezer l : lda.opzoekenLezer("Helena", "De Munck")) {
                    System.out.println(l.toString());
                }
            }
    }
}
