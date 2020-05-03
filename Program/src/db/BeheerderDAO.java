package db;

/**
 * @author Katrien Persoons
 */

import entity.Beheerder;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class BeheerderDAO extends BaseDAO implements Security {

    // Aanmaken van een nieuwe beheerder:

    public void toevoegenBeheerder(Beheerder beheerder) {
        byte[] salt = Security.createSalt();

        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("insert into Beheerders values (NULL, ?, ?, ?, ?, ?)");
            s.setString(1, beheerder.getVoornaam());
            s.setString(2, beheerder.getNaam());
            s.setString(3, beheerder.getGebruikersnaam());
            s.setBytes(4, salt);
            s.setString(5, Security.generateHash(beheerder.getWachtwoord(), salt));

            // Aanpassen zodat CSV-bestand kan worden ingelezen en afgelopen met while-loop om gegevens in batch toe te voegen
            // Opmerking Virginie: niet nodig voor het aanmaken van beheerders (aangezien het er niet veel zouden moeten zijn)?
            // Foutmelding toevoegen indien gegevens reeds in de databank zitten

            int result = s.executeUpdate();
            if (result > 0)
                System.out.println("GELUKT");
            else System.out.println("MISLUKT!");

            // Nog aan te passen: foutmelding laten afhangen van errorcode (bvb.: beheerder bestaat reeds)

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    // Een overzicht van alle beheerders:

    public ArrayList<Beheerder> ophalenBeheerders() {
        ArrayList<Beheerder> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Beheerders");
            while (rs.next()) {
                lijst.add(new Beheerder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijst;
    }

    // Een beheerder opzoeken op voornaam:

    public ArrayList<Beheerder> opzoekenBeheerder(String voornaam) {
        ArrayList<Beheerder> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Beheerders where voornaam = ?");
            s.setString(1, voornaam);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijst.add(new Beheerder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }

        return lijst;
    }

    // Een beheerder opzoeken op voornaam en naam:

    public ArrayList<Beheerder> opzoekenBeheerder(String voornaam, String naam) {
        ArrayList<Beheerder> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Beheerders where voornaam = ? AND naam = ?");
            s.setString(1, voornaam);
            s.setString(2, naam);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijst.add(new Beheerder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }

        return lijst;
    }

    public static void main(String[] args) {

        // Toevoegen van beheerder:

        BeheerderDAO bda = new BeheerderDAO();

 /*       Beheerder b1 = new Beheerder("Virginie", "Blabla", "vblabla", "ksjdfalke");
        bda.toevoegenBeheerder(b1);

        // Weergeven van de beheerders:

        for(Beheerder b : bda.ophalenBeheerders()){
            System.out.println(b.getId() + " - " + b.getVoornaam() + " " + b.getNaam() + " - " + b.getGebruikersnaam());
        }
*/
        // Opzoeken van een beheerder:

        for (Beheerder b : bda.opzoekenBeheerder("Virginie", "Blabla")) {
            System.out.println(b.getId() + " - " + b.getVoornaam() + " " + b.getNaam() + " - " + b.getGebruikersnaam());
        }

    }
}