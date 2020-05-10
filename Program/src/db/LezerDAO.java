package db;

/**
 * @author Katrien Persoons
 */

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
            System.out.println("De lezer kan niet worden toegevoegd. Hij/zij voldoet niet aan de vereiste leeftijdsvoorwaarde (min. 12 jaar).");
            throw new LezerTeJong();
        } else {

            byte[] salt = Security.createSalt();
            try (Connection c = getConn()) {

                // Eerst tabel Lezers updaten (parent)

                PreparedStatement p = c.prepareStatement("insert into Lezers values (NULL, ?, ?, ?, ?, ?, ?, ?)");
                p.setString(1, lezer.getVoornaam());
                p.setString(2, lezer.getNaam());
                p.setObject(3, lezer.getGeboortedatumDB());
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

    public static ArrayList<Lezer> ophalenLezers() {
        ArrayList<Lezer> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Lezers");
            while (rs.next()) {
                lijst.add(new Lezer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getObject(4, LocalDate.class), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijst;
    }

    // Opzoeken lezer op voornaam en naam:

    public static ArrayList<Lezer> opzoekenLezer(String voornaam, String naam) {
        ArrayList<Lezer> lijst = new ArrayList<>();

        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Lezers where voornaam LIKE ? OR naam LIKE ?");
            s.setString(1, "%" + voornaam + "%");
            s.setString(2, "%" + naam + "%");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijst.add(new Lezer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getObject(4, LocalDate.class), rs.getString(5), rs.getString(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }

        return lijst;
    }

    public static void verwijderenLezer(int id) throws SQLIntegrityConstraintViolationException, LezerNietGevonden {
        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("delete from Adressen where Lezer_ID = ?");
            s.setInt(1, id);
            PreparedStatement p = c.prepareStatement("delete from Lezers where Lezer_ID = ?");
            p.setInt(1, id);

            int result1 = s.executeUpdate();
            int result2 = p.executeUpdate();

            if (result1 > 0 && result2 > 0)
                System.out.println("De lezer werd verwijderd!");
            else
                System.out.println("De lezer kon niet worden verwijderd.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
            throw new SQLIntegrityConstraintViolationException();
        }
    }

    public void wijzigenAdresLezer(int lezer_id, Adres adres) {
        try (Connection c = getConn()) {

            PreparedStatement p = c.prepareStatement("update Adressen set Straat = ? and Huisnr = ? and bus = ? and Postcode = ? and Woonplaats = ? where Lezer_ID = ?");
            p.setString(1, adres.getStraatnaam());
            p.setInt(2, adres.getHuisnummer());
            p.setString(3, adres.getBus());
            p.setInt(4, adres.getPostcode());
            p.setString(5, adres.getWoonplaats());
            p.setInt(6, lezer_id);

            int result = p.executeUpdate();

            if (result > 0)
                System.out.println("De adreswijziging werd geregistreerd!");
            else System.out.println("De adreswijziging kon niet worden geregistreerd!");

            // Nog aan te passen: foutmelding laten afhangen van errorcode (bvb.: lezer niet gevonden)

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    public void wijzigenTelLezer(int lezer_id, String telefoon) {
        try (Connection c = getConn()) {

            PreparedStatement p = c.prepareStatement("update Lezers set Telefoon = ? where Lezer_ID = ?");
            p.setString(1, telefoon);
            p.setInt(2, lezer_id);

            int result = p.executeUpdate();

            if (result > 0)
                System.out.println("De wijziging werd geregistreerd!");
            else System.out.println("De wijziging kon niet worden geregistreerd!");

            // Nog aan te passen: foutmelding laten afhangen van errorcode (bvb.: lezer niet gevonden)

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    public void wijzigenWachtwoordLezer(int lezer_id, String wachtwoord) {
        try (Connection c = getConn()) {
            byte[] salt = Security.createSalt();
            PreparedStatement p = c.prepareStatement("update Lezers set Salt = ? and HashCode = ? where Lezer_ID = ?");
            p.setBytes(1, salt);
            p.setString(2, Security.generateHash(wachtwoord, salt));
            p.setInt(3, lezer_id);

            int result = p.executeUpdate();

            if (result > 0)
                System.out.println("De wachtwoordswijziging werd geregistreerd!");
            else System.out.println("De wachtwoordswijziging kon niet worden geregistreerd!");

            // Nog aan te passen: foutmelding laten afhangen van errorcode (bvb.: lezer niet gevonden)

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }
}
