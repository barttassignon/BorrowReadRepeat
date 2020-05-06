package db;

import entity.Boek;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class BoekDAO extends BaseDAO {

    // Toevoegen van boek

    public void toevoegenBoek(Boek boek) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("insert into Boeken values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            s.setLong(1, boek.getISBN());
            s.setString(2, boek.getTitel());
            s.setString(3, boek.getAuteur());
            s.setString(4, boek.getUitgeverij());
            s.setString(5, boek.getTaal());
            s.setInt(6, boek.getPaginas());
            s.setObject(7, boek.getAankoopdatum());
            s.setDouble(8, boek.getPrijs());
            s.setString(9, boek.getPlaatsInBib());

            // Aanpassen zodat CSV-bestand kan worden ingelezen en afgelopen met while-loop om gegevens in batch toe te voegen
            // Foutmelding toevoegen indien gegevens reeds in de databank zitten ("boek bestaat reeds")

            int result = s.executeUpdate();
            if (result > 0)
                System.out.println("GELUKT");
            else System.out.println("MISLUKT!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
    }

    // geef alle boeken weer

    public ArrayList<Boek> ophalenBoeken() {
        ArrayList<Boek> lijstBoeken = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Boeken");
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                lijstBoeken.add(new Boek(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4)));
            }
            System.out.println("GELUKT!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        return lijstBoeken;
    }

    // verwijder boek:

    public void verwijderenBoek(int artikelnummer) {
        try {
            Connection c = getConn();
                PreparedStatement s = c.prepareStatement("delete from Boek where artikelnummer = ?");
                s.setInt(1, artikelnummer);
                int result = s.executeUpdate();
                if (result > 0) {
                    System.out.println("Het boek werd verwijderd");
                } else {
                    System.out.println("Er bestaat geen boek met dit artikelnummer");
                }
           } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MISLUKT!");
        }
        //       public static void main(int[] args)  {
        //Boek b = new Boek(9789029586665L, "De alchemist", "Paolo Coelho", "de Arbeiderspers", "Nederlands", 144, LocalDate.of(2000, Month.MAY, 15), 25.45, "COE135.2");
//        BoekDAO bda = new BoekDAO();
//        bda.ophalenBoeken();

//        for(Boek b: bda.ophalenBoeken())
//        {
//            System.out.println(b.toString());
//        }
        //bda.toevoegenBoek(b);
        // "-L" toevoegen aan ISBN zodat dit wordt aanzien als een long in plaats van een int
    }

}
