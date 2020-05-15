package db;

import entity.Boek;
import entity.Lezer;
import entity.Uitlening;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UitleenDAO extends BaseDAO {

    // Een uitlening toevoegen:

    public static void uitleningToevoegen(Uitlening uitlening) {

        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("insert into Uitleningen values (NULL, ?, ?, ?, NULL, NULL)");
            s.setInt(1, uitlening.getLezer().getId());
            s.setInt(2, uitlening.getBoek().getArtikelnummer());
            s.setObject(3, uitlening.getDatumUitgeleend());

            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Een uitlening verlengen:

    public static void verlengUitlening(int uitleenID){
        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("update Uitleningen set Verlengdatum = ? where Uitleen_ID = ?");
            s.setObject(1, LocalDate.now());
            s.setInt(2, uitleenID);
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Een uitlening terugbrengen:

    public static void binnenbrengenUitlening(int boekID){
        try (Connection c = getConn()) {

            PreparedStatement s = c.prepareStatement("update Uitleningen set Inleverdatum = ? where Boek_ID = ? AND Inleverdatum is null");
            s.setObject(1, LocalDate.now());
            s.setInt(2, boekID);

            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Een overzicht van alle lopende uitleningen:

    public static ArrayList<Uitlening> ophalenUitleningen() {
        ArrayList<Uitlening> lijstUitlening = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Uitleningen where Inleverdatum is null");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijstUitlening.add(new Uitlening(new Lezer(rs.getInt(2)), new Boek(rs.getInt(3)), rs.getInt(1), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijstUitlening;
    }

    // Ophalen alle lopende uitleningen van specifieke lezer:

    public static ArrayList<Uitlening> uitleengeschiedenisLezer(int lezerID) {
        ArrayList<Uitlening> lijstUitlening = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Uitleningen where Lezer_ID = ? and Inleverdatum is null");
            s.setInt(1, lezerID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijstUitlening.add(new Uitlening(new Lezer(rs.getInt(2)), new Boek(rs.getInt(3)), rs.getInt(1), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lijstUitlening;
    }

    // Ophalen alle uitleningen van specifieke lezer:

    public static ArrayList<Uitlening> uitleengeschiedenLezer(int lezerID) {
        ArrayList<Uitlening> lijstUitlening = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Uitleningen where Lezer_ID = ?");
            s.setInt(1, lezerID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijstUitlening.add(new Uitlening(new Lezer(rs.getInt(2)), new Boek(rs.getInt(3)), rs.getInt(1), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lijstUitlening;
    }

    // Ophalen uitlening op basis van uitleenID:

    public static Uitlening ophalenUitlening(int uitleenID){
        Uitlening uitlening = null;
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Uitleningen where Uitleen_ID = ?");
            s.setInt(1, uitleenID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                uitlening = new Uitlening(new Lezer(rs.getInt(2)), new Boek(rs.getInt(3)), rs.getInt(1), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uitlening;
    }

    // Ophalen uitlening op basis van boekID:

    public static Uitlening ophalenUitleningSchuld(int boekID) {
        Uitlening uitlening = null;
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Uitleningen where Boek_ID = ? AND Inleverdatum is null");
            s.setInt(1, boekID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                uitlening = new Uitlening(new Lezer(rs.getInt(2)), new Boek(rs.getInt(3)), rs.getInt(1), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uitlening;
    }

// Ophalen alle uitleningen van specifiek boek:

    public static ArrayList<Uitlening> uitleengeschiedenisBoek(int boekID) {
        ArrayList<Uitlening> lijstUitlening = new ArrayList<>();
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select * from Uitleningen where Boek_ID = ?");
            s.setInt(1, boekID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                lijstUitlening.add(new Uitlening(new Lezer(rs.getInt(2)), new Boek(rs.getInt(3)), rs.getInt(1), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lijstUitlening;
    }

    // Per lezer max. 10 boeken tegelijk uitlenen, waarvan max. 5 normale boeken:

    public static int aantalUitleningenPerLezer(int lezerID) {
        int aantal = 0;
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select count(Lezer_ID) from Uitleningen where Lezer_ID = ? AND Inleverdatum is null");
            s.setInt(1, lezerID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                aantal = rs.getInt(1);}
            } catch (SQLException e){
                e.printStackTrace();
            }
            return aantal;
        }

    public static int aantalVolwassenboekenPerLezer(int lezerID) {
        int aantal = 0;
        try (Connection c = getConn()) {
            PreparedStatement s = c.prepareStatement("select count(isKinderboek) from Boeken b join Uitleningen u on (b.Boek_ID = u.Boek_ID) where isKinderboek = ? AND Lezer_ID = ? AND Inleverdatum is null");
            s.setInt(1, 0);
            s.setInt(2, lezerID);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                aantal = rs.getInt(1);}
        } catch (SQLException e){
            e.printStackTrace();
        }
        return aantal;
    }

    }
