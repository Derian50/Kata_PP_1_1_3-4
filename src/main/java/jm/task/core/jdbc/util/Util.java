package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/users";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "wkla7382Qw";
    private static Connection connection;
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
