package db;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDAO {

    private static Connection conn = null;

    public static Connection getConn() {
        try {
            if(conn == null || conn.isClosed())
                conn = DatabaseSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
