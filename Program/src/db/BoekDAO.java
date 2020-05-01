package db;

import entity.Boek;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class BoekDAO extends BaseDAO {

    public void toevoegenBoek(Boek boek) throws SQLException {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("insert into Boeken values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            s.setLong(1, boek.getISBN());
            s.setString(2, boek.getTitel());
            s.setString(3, boek.getAuteur());
            s.setString(4, boek.getUitgeverij());
            s.setString(5, boek.getTaal());
            s.setInt(6, boek.getPaginas());
            s.setObject(7, boek.getAankoopdatum());
            s.setDouble(8, boek.getPrijs());
            s.setString(9, boek.getPlaatsInBib());
            s.setBoolean(10, boek.isGereserveerd());
            s.setInt(11, boek.getAantalKeerUitgeleend());

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

    public static void main(String[] args) {

        //Boek b = new Boek(9789029586665L, "De alchemist", "Paolo Coelho", "de Arbeiderspers", "Nederlands", LocalDate.now(), 25.45, "COE135.2", 0, 5);
        //BoekDAO bda = new BoekDAO();
        //bda.toevoegenBoek(b);
        // "-L" toevoegen aan ISBN zodat dit wordt aanzien als een long in plaats van een int
        // Werkt nog niet: Hoe datum doorgeven?
    }
};
