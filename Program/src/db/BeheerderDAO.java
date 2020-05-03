package db;

/**
 * @author Katrien Persoons
 */

import entity.Beheerder;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class BeheerderDAO extends BaseDAO implements Security{

    // Aanmaken van een nieuwe beheerder:

    public void toevoegenBeheerder(Beheerder beheerder)
    {
        byte[] salt = Security.createSalt();

        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("insert into BeheerdersVeilig values (NULL, ?, ?, ?, ?, ?)");
            s.setString(1, beheerder.getVoornaam());
            s.setString(2, beheerder.getNaam());
            s.setString(3, beheerder.getGebruikersnaam());
            s.setBytes(4, salt);
            s.setString(5, Security.generateHash(beheerder.getWachtwoord(), salt));
            
            // Aanpassen zodat CSV-bestand kan worden ingelezen en afgelopen met while-loop om gegevens in batch toe te voegen
            // Foutmelding toevoegen indien gegevens reeds in de databank zitten

            int result = s.executeUpdate();
            if (result > 0)
                System.out.println("GELUKT");
            else System.out.println("MISLUKT!");

            // Nog aan te passen: foutmelding laten afhangen van errorcode (bvb.: beheerder bestaat reeds)

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        } ;
    };

    public ArrayList<Beheerder> ophalenBeheerders()
    {
        ArrayList<Beheerder> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Beheerders");
            while (rs.next())
            {
 //               lijst.add(new Beheerder(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        } ;
        return lijst;
    };

    public Beheerder opzoekenBeheerder(String gebruikersnaam)
    {
        Beheerder b = null;
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Beheerders where gebruikersnaam = ?");
            s.setString(1, "%");
            ResultSet rs = s.executeQuery();
            if (rs.next())
            {
 //               b = new Beheerder(rs.getInt(1), rs.getString(2), rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        } ;
        return b;
    }

    public static void main(String[] args) {
        BeheerderDAO bda = new BeheerderDAO();
        Beheerder b1 = new Beheerder("Virginie", "Ortegat", "vortegat", "12345");
        Beheerder b2 = new Beheerder("Katrien", "Persoons", "kpersoons", "6789P");
        bda.toevoegenBeheerder(b1);
        bda.toevoegenBeheerder(b2);

        // geeft geen foutmelding als gebruikersnaam en wachtwoord reeds in databank zitten.
    }
}

