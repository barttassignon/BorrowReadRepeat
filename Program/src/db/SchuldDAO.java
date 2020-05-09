package db;

import entity.Schuld;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class SchuldDAO extends BaseDAO {

    public void aanrekenenSchuld(int lezer_id, Schuld schuld)
    {
        try (Connection c = getConn()) {

            // Eerst tabel Lezers updaten (parent)

            PreparedStatement p = c.prepareStatement("insert into Schulden values (NULL, ?, ?, ?, ?, NULL)");
            p.setInt(1, lezer_id);
            p.setString(2, schuld.getOorsprong().name());
            p.setDouble(3, schuld.getBedrag());
            p.setObject(4, schuld.getDatumAangemaakt());

            int result = p.executeUpdate();

            if (result > 0)
                System.out.println("De schuld werd toegevoegd!");
            else System.out.println("De schuld kon niet worden toegevoegd!");

            // Nog aan te passen: foutmelding laten afhangen van errorcode (bvb.: lezer niet gevonden)

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    public void betalenSchuld()
        {

}

    public static void main(String[] args) {
        SchuldDAO schuldda = new SchuldDAO();
        Schuld schuld = new Schuld(Schuld.Oorsprong.RESERVATIE, 0.5, LocalDate.of(2020, 1, 5));
        schuldda.aanrekenenSchuld(37, schuld);
    }
}
