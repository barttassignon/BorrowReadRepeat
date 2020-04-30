package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Katrien Persoons
 */

public class Main {

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

