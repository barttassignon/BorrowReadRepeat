package db;

import entity.Boek;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoekDAO extends BaseDAO {

    public void toevoegenBoek(Boek boek) throws SQLException {

        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("insert into Boek values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            s.setInt(2, boek.getISBN());
            s.setString(3, boek.getTitel());
            s.setString(4, boek.getAuteur());
            s.setString(5, boek.getUitgeverij());
            s.setString(6, boek.getTaal());
            s.setInt(7, boek.getPaginas());
            s.setDate(8, boek.getAankoopdatum());
            s.setDouble(9, boek.getPrijs());
            s.setString(10, boek.getPlaatsInBib());
            s.setBoolean(11, boek.isGereserveerd());
            s.setInt(12, boek.getAantalKeerUitgeleend());

            // Aanpassen zodat CSV-bestand kan worden ingelezen en afgelopen met while-loop om gegevens in batch toe te voegen
            // Foutmelding toevoegen indien gegevens reeds in de databank zitten

            int result = s.executeUpdate();
            if (result > 0)
                System.out.println("GELUKT");
            else System.out.println("MISLUKT!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }
};
