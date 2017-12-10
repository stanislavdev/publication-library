package model.dbservices;

import model.dao.DigitalPublicationDAO;
import model.entity.DigitalPublication;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * Created by dvsta on 08.12.2017.
 */
public class MySQLDigitalPublicationDAO implements DigitalPublicationDAO {

    private Connection connection;

    public MySQLDigitalPublicationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<DigitalPublication> searchPublication(int numberOfPages, String author, String name,
                                                      Date publicationDate) {
        return null;
    }

    @Override
    public List<DigitalPublication> searchReferencePublications(int publicationId) {
        return null;
    }

    @Override
    public List<DigitalPublication> findAllPublications() {
        return null;
    }

    @Override
    public void close() {

    }
}
