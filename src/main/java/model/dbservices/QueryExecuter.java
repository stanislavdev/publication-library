package model.dbservices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dvsta on 02.12.2017.
 */
public class QueryExecuter {
    public static ResultSet execute(String query) {
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
