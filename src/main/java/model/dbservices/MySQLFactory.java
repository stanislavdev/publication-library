package model.dbservices;

import model.dao.DigitalPublicationDAO;
import model.dao.FactoryDAO;
import model.dao.PaperPublicationDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by dvsta on 08.12.2017.
 */
public class MySQLFactory extends FactoryDAO {

    @Override
    public DigitalPublicationDAO createDigitalPublicationDAO() {
        return new MySQLDigitalPublicationDAO(getConnection());
    }

    @Override
    public PaperPublicationDao createPaperPublicationDao() {
        return null;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    Constants.DB_URL,
                    Constants.DB_USER,
                    Constants.DB_PASSWORD
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
