import controller.JSPLoader;

import model.dao.DigitalPublicationDAO;
import model.dao.FactoryDAO;
import model.dbservices.DBConnection;
import model.dbservices.MySQLFactory;
import model.dbservices.PublicationDAO;
import model.entity.Publication;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by dvsta on 02.12.2017.
 */
public class TestConnection {

    @Test
    public void testExtractAllDigitalPublication() {
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDAO digitalPublicationDAO = mySQLFactory.createDigitalPublicationDAO();
        System.out.println(digitalPublicationDAO.findAllPublications());
    }
}
