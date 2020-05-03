package db;

/**
 * @author Katrien Persoons
 */

import entity.Beheerder;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class BeheerderDAO extends BaseDAO implements Security {

    // Inloggen beheerder:

    public void inloggen(String gebruikersnaam, String paswoord){

        try (Connection c = getConn()){
            PreparedStatement s1 = c.prepareStatement("select salt from Beheerders where gebruikersnaam = ?");
            s1.setString(1, gebruikersnaam);
            ResultSet rs1 = s1.executeQuery();

            if(rs1.next()){
            PreparedStatement s2 =c.prepareStatement("select * from Beheerders where gebruikersnaam = ? and hashcode = ?");
            s2.setString(1, gebruikersnaam);
            s2.setString(2, Security.generateHash(paswoord, rs1.getBytes(1)));
            ResultSet rs2 = s2.executeQuery();
                if(rs2.next()){
                    System.out.println("Ingelogd!");}
                else System.out.println("Verkeerd paswoord!");}
            else System.out.println("Geen beheerder met deze gebruikersnaam!");

        }catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }

    }

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
                System.out.println("De beheerder werd toegevoegd!");
            else System.out.println("De beheerder kon niet worden toegevoegd!");

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

    // Een beheerder verwijderen:

    public void verwijderenBeheerder(String gebruikersnaam) {
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("delete from Beheerders where gebruikersnaam = ?");
            s.setString(1, gebruikersnaam);

            int result = s.executeUpdate();
            if (result > 0)
                System.out.println("De beheerder werd verwijderd!");
            else System.out.println("Er bestaat geen beheerder met deze gebruikersnaam!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }

    }

    public static void main(String[] args) {

        // Toevoegen van beheerder:

        BeheerderDAO bda = new BeheerderDAO();
/*
       Beheerder b1 = new Beheerder("Virginie", "Ortegat", "vortegat", "12345");
        bda.toevoegenBeheerder(b1);

        // Weergeven van de beheerders:

        for(Beheerder b : bda.ophalenBeheerders()){
            System.out.println(b.getId() + " - " + b.getVoornaam() + " " + b.getNaam() + " - " + b.getGebruikersnaam());
        }

        // Opzoeken van een beheerder:

        for (Beheerder b : bda.opzoekenBeheerder("Virginie", "Blabla")) {
            System.out.println(b.getId() + " - " + b.getVoornaam() + " " + b.getNaam() + " - " + b.getGebruikersnaam());
        }

        // Verwijderen van een beheerder:

        bda.verwijderenBeheerder("vblabla");
 */

        // Inloggen beheerder:
        bda.inloggen("vortegat", "12345");

    }
}