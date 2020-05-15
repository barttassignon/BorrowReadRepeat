package db;


import entity.Reservatie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservatieDAO extends BaseDAO {

    // methode om nieuwe reservatie toe te voegen aan database:

    public static void maakReservatie(Reservatie reservatie) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("insert into Reservaties values (NULL, ?, ?, ?, NULL)");
            s.setInt(1, reservatie.getLezer().getId());
            s.setInt(2, reservatie.getBoek().getArtikelnummer());
            s.setObject(3, reservatie.getReservatieDatum());
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ophalen wie een boek heeft gereserveerd:

    public static int ophalenReservatie(int boekID){
        int lezer = 0;
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select Lezer_ID from Reservaties where Boek_ID = ? AND Einddatum is null");
            s.setInt(1, boekID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lezer = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lezer;
    }

    // Einde van een reservatie:

    public static void eindeReservatie(int boekID){
        try (Connection c = getConn()) {

            // Tabel Reservaties updaten:

            PreparedStatement s = c.prepareStatement("update Reservaties set Einddatum = ? where Boek_ID = ? AND Einddatum is null");
            s.setObject(1, LocalDate.now());
            s.setInt(2, boekID);
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
