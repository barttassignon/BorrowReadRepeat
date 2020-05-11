package db;

import entity.Boek;
import entity.Reservatie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservatieDAO extends BaseDAO {

    // methode om nieuwe reservatie toe te voegen aan database:

    public static void maakReservatie(Reservatie reservatie) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("insert into Reservaties values (NULL, ?, ?, ?)");
            s.setInt(1, reservatie.getLezer().getId());
            s.setInt(2, reservatie.getBoek().getArtikelnummer());
            s.setObject(3, reservatie.getReservatieDatum());

            int result = s.executeUpdate();
            if (result > 0)
                System.out.println("Reservatie werd toegevoegd!");
            else System.out.println("Reservatie werd niet toegevoegd!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    // methode om status boek te wijzigen naar gereserveerd:



}
