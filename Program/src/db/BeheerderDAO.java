package db;

import entity.Beheerder;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class BeheerderDAO extends BaseDAO implements Security {

    // Inloggen beheerder:

    public static boolean inloggen(String gebruikersnaam, String paswoord){
        try (Connection c = getConn()){
            PreparedStatement s1 = c.prepareStatement("select salt from Beheerders where gebruikersnaam = ?");
            s1.setString(1, gebruikersnaam);
            ResultSet rs1 = s1.executeQuery();

            if(rs1.next()){
            PreparedStatement s2 =c.prepareStatement("select * from Beheerders where gebruikersnaam = ? and hashcode = ?");
            s2.setString(1, gebruikersnaam);
            s2.setString(2, Security.generateHash(paswoord, rs1.getBytes(1)));
            ResultSet rs2 = s2.executeQuery();
                if(rs2.next()) return true;
                else return false;}
        }catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Aanmaken van een nieuwe beheerder:

    public static boolean toevoegenBeheerder(Beheerder beheerder) {
        byte[] salt = Security.createSalt();

        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("insert into Beheerders values (?, ?, ?, ?, ?)");
            s.setString(1, beheerder.getVoornaam());
            s.setString(2, beheerder.getNaam());
            s.setString(3, beheerder.getGebruikersnaam());
            s.setBytes(4, salt);
            s.setString(5, Security.generateHash(beheerder.getWachtwoord(), salt));

            // Er wordt geen batch-functie voorzien voor het toevoegen of verwijderen van beheerders omdat dit een beperkt aantal personen betreft.

            int result = s.executeUpdate();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
}