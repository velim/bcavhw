package cz.velim.bchw.db;

import java.sql.*;

/**
 * Created by mvelek on 5/5/2014.
 */
public class MyConnection {
    private Connection m_conn;

    public Boolean initConnection(String dbstring) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            m_conn = DriverManager.getConnection(dbstring);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.err.println("Nepodarilo se otevrit DB spojeni");
            return false;
        }
        return true;
    }

    public Connection getConnection() {
        return m_conn;
    }
}
