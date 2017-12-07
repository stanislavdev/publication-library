package model.dbservices;



import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by dvsta on 02.12.2017.
 */
public class DBConnection {

    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection.connection;
    }
}
