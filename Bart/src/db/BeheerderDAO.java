package db;

/**
 * @author Katrien Persoons
 */

import entity.Beheerder;

import java.sql.*;
import java.util.ArrayList;

public class BeheerderDAO extends BaseDAO {

    public void saveBeheerder(Beheerder beheerder)
    {
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("insert into Beheerders values (NULL, ?, ?)");
            s.setString(1, beheerder.getGebruikersnaam());
            s.setString(2, beheerder.getWachtwoord());

            // Aanpassen zodat CSV-bestand kan worden ingelezen en afgelopen met while-loop om gegevens in batch toe te voegen
            // Foutmelding toevoegen indien gegevens reeds in de databank zitten

            int result = s.executeUpdate();
            if (result > 0)
                System.out.println("GELUKT");
            else System.out.println("MISLUKT!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        } ;
    };

    public ArrayList<Beheerder> readBeheerders ()
    {
        ArrayList<Beheerder> lijst = new ArrayList<>();
        try (Connection c = getConn()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Beheerders");
            while (rs.next())
            {
                lijst.add(new Beheerder(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        } ;
        return lijst;
    };

    public Beheerder searchBeheerderByName(String gebruikersnaam)
    {
        Beheerder b = null;
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Beheerders where gebruikersnaam like ?");
            s.setString(1, "%");
            ResultSet rs = s.executeQuery();
            if (rs.next())
            {
                b = new Beheerder(rs.getInt(1), rs.getString(2), rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        } ;
        return b;
    }
}

