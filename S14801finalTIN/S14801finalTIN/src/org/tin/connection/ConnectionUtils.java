package org.tin.connection;

import java.sql.Connection;
import java.sql.SQLException;
 
public class ConnectionUtils {
 
    public static Connection getConnection() 
              throws ClassNotFoundException, SQLException {
 
        return OracleConnUtils.getOracleConnection();
    }
     
    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
 
    public static void rollbackConnection(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
