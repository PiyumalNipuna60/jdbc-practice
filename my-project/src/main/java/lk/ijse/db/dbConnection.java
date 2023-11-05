package lk.ijse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private Connection con;
    private static dbConnection dbConnection;

    public dbConnection() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop","root","1234");
    }
    public static dbConnection getInstance() throws SQLException {
        if (dbConnection == null){
            return dbConnection = new dbConnection();
        }
        else
            return dbConnection;
    }
    public Connection getConnection() {
        return con;
    }
}