package db;

/**
 * @author Katrien Persoons
 * Doel: Connectie met databank
 * Voorafgaande stappen:
 * 1. "mysql jdbc connector download" opzoeken in Google,
 * 2. Laatste versie van de connector downloaden
 * 3. Bestand in project plaatsen (rechtermuisknop op project -> Show in Explorer)
 * 4. Bestand toevoegen aan project (File -> Project Structure -> Libraries -> New Project Library -> bestand selecteren -> Apply)
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {

    private Connection connection = null;
    private static DatabaseSingleton instance = null;

    private DatabaseSingleton()
    {}

    public static DatabaseSingleton getInstance()
    {
        if (instance == null)
            instance = new DatabaseSingleton();
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = "jdbc:mysql://dt5.ehb.be/1920mobappgr1";
                String user = "1920mobappgr1";
                String password = "XNnhDjw";

                // Nog aan te passen! In werkelijkheid niet hardcoded: meesturen als argument in logic vanuit config file
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Cannot close connection!");
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public static void main(String[] args) {

        String url = "jdbc:mysql://dt5.ehb.be/1920mobappgr1";
        String user = "1920mobappgr1";
        String password = "XNnhDjw";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
