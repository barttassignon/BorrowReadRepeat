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

import entity.OnzeLogger;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

                // Bestand met URL database en login-gegevens ophalen en uitlezen
                FileInputStream fis = new FileInputStream("connection.properties");
                // Properties-klasse aanmaken om key/value-pairs in dit bestand te kunnen uitlezen
                Properties p = new Properties ();
                // Load-functie om gegevens effectief uit te lezen
                p.load (fis);
                String url = (String) p.get("URL");
                String username = (String) p.get("username");
                String password = (String) p.get("password");

                connection = DriverManager.getConnection(url, username, password);

                OnzeLogger SingletonLog = new OnzeLogger();
                SingletonLog.doLoggingInfo(this.getClass().getSimpleName() + " : Connectie met SQL Database is gemaakt.");




            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();

            OnzeLogger SingletonLog = new OnzeLogger();
            SingletonLog.doLoggingSevere(this.getClass().getSimpleName() + ": Connectie met SQL Database is mislukt.");
        }
        return connection;


    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                OnzeLogger SingletonLog = new OnzeLogger();
                SingletonLog.doLoggingInfo(this.getClass().getSimpleName() + ": Connectie met SQL Database is afgesloten.");
            } catch (SQLException e) {
                System.out.println("Cannot close connection!");
                OnzeLogger SingletonLog = new OnzeLogger();
                SingletonLog.doLoggingSevere(this.getClass().getSimpleName() + ": Kan de connectie met SQL Database is niet afsluiten.");
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
