package db;

import entity.*;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class LezerDAO extends BaseDAO {

    // Een lezer toevoegen

    public static void toevoegenLezer(Lezer lezer) throws LezerTeJong, SQLIntegrityConstraintViolationException {

        if (lezer.berekenLeeftijd() < 12) {
            throw new LezerTeJong();
        } else {

            byte[] salt = Security.createSalt();
            try (Connection c = getConn()) {

                // Eerst tabel Lezers updaten (parent)

                c.setAutoCommit(false);

                PreparedStatement p = c.prepareStatement("insert into Lezers values (NULL, ?, ?, ?, ?, ?, ?, ?)");
                p.setString(1, lezer.getVoornaam());
                p.setString(2, lezer.getNaam());
                p.setObject(3, lezer.getGeboortedatum());
                p.setString(4, lezer.getEmail());
                p.setString(5, lezer.getTelefoon());
                p.setBytes(6, salt);
                p.setString(7, Security.generateHash(lezer.getWachtwoord(), salt));

                p.executeUpdate();

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

                s.executeUpdate();

                c.commit();

            } catch (SQLException | NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw new SQLIntegrityConstraintViolationException();
            }
        }
    }

    // Een overzicht van alle lezers:

    public static ArrayList<Lezer> ophalenLezers() {
        ArrayList<Lezer> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Lezers where Voornaam is not null");
            while (rs.next()) {
                lijst.add(new Lezer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getObject(4, LocalDate.class), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    // Ophalen lezer op basis van ID (voor reservatie/uitlening):

    public static Lezer ophalenLezer(int lezerID){
        Lezer lezer = null;
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Lezers where Lezer_ID = ?");
            s.setInt(1, lezerID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lezer = new Lezer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getObject(4, LocalDate.class), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lezer;
    }

    // Opzoeken lezer op voornaam en naam:

    public static ArrayList<Lezer> opzoekenLezer(String voornaam, String naam) {
        ArrayList<Lezer> lijst = new ArrayList<>();

        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Lezers where voornaam LIKE ? AND naam LIKE ?");
            s.setString(1, "%" + voornaam + "%");
            s.setString(2, "%" + naam + "%");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijst.add(new Lezer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getObject(4, LocalDate.class), rs.getString(5), rs.getString(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    // Methode om lezer te verwijderen:

    public static void verwijderenLezer(int lezerID) {
        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("delete from Adressen where Lezer_ID = ?");
            s.setInt(1, lezerID);
            PreparedStatement p = c.prepareStatement("update Lezers set Voornaam = null, Naam = null, Geboortedatum = null, Telefoon = null where Lezer_ID = ?");
            p.setInt(1, lezerID);

            s.executeUpdate();
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methode om na te kijken of er een lezer met een bepaald ID bestaat:

    public static int bestaatLezer(int lezerID){
        int lezer = 0;
        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("select count(Lezer_ID) from Lezers where Lezer_ID = ?");
            s.setInt(1, lezerID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
              lezer = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lezer;
    }

    // Methode om adres van de lezer te wijzigen:

    public static void wijzigenAdresLezer(int lezerID, Adres adres) {
        try (Connection c = getConn()) {

            PreparedStatement p = c.prepareStatement("update Adressen set Straat = ?, Huisnr = ?, Bus = ?, Postcode = ?, Woonplaats = ? where Lezer_ID = ?");
            p.setString(1, adres.getStraatnaam());
            p.setInt(2, adres.getHuisnummer());
            p.setString(3, adres.getBus());
            p.setInt(4, adres.getPostcode());
            p.setString(5, adres.getWoonplaats());
            p.setInt(6, lezerID);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methode om telefoonnummer lezer te wijzigen:

    public static void wijzigenTelLezer(int lezerID, String telefoon) {
        try (Connection c = getConn()) {

            PreparedStatement p = c.prepareStatement("update Lezers set Telefoon = ? where Lezer_ID = ?");
            p.setString(1, telefoon);
            p.setInt(2, lezerID);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methode om emailadres lezer te wijzigen:

    public static void wijzigenEmailLezer(int lezerID, String email) throws SQLIntegrityConstraintViolationException{
        try (Connection c = getConn()) {

            PreparedStatement p = c.prepareStatement("update Lezers set Emailadres = ? where Lezer_ID = ?");
            p.setString(1, email);
            p.setInt(2, lezerID);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException();
        }
    }
}
