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

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

                FileInputStream fis = new FileInputStream("connection.properties");
                Properties p = new Properties ();
                p.load (fis);
                String url= (String) p.get ("URL");
                String username= (String) p.get ("username");
                String password= (String) p.get ("password");
                //String url = "jdbc:mysql://dt5.ehb.be/1920mobappgr1";
                //String user = "1920mobappgr1";
                //String password = "XNnhDjw";

                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException | IOException e) {
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
