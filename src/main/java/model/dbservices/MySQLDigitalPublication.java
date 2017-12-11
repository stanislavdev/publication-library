package model.dbservices;

import model.dao.DigitalPublicationDAO;
import model.entity.DigitalPublication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvsta on 08.12.2017.
 */
public class MySQLDigitalPublication implements DigitalPublicationDAO {
    public static final String ID_PUBLICATION = "publication.idpublication";
    public static final String NAME_PUBLICATION = "publication.name";
    public static final String AUTHOR_PUBLICATION = "publication.author";
    public static final String NUMBER_OF_PAGES = "publication.number_of_pages";
    public static final String DATE_OF_PUBLICATION = "publication.date_of_publication";
    public static final String INTERNET_LINK = "publication.internet_link";
    public static final String SIZE_IN_BITES = "publication.size_in_bites";

    public static final String SELECT_ALL_BY_TYPE_QUERY = "SELECT * FROM publication " +
            "WHERE  type_of_publication = 'Digital'";

    private Connection connection;

    public MySQLDigitalPublication(Connection connection) {
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
        try {
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(SELECT_ALL_BY_TYPE_QUERY);
            return parseDigitalPublicationList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }

    private DigitalPublication extractDigitalPublication(ResultSet resultSet) {
        DigitalPublication result = new DigitalPublication();
        try {
            result.setId(resultSet.getInt(ID_PUBLICATION));
            result.setName(resultSet.getString(NAME_PUBLICATION));
            result.setAuthor(resultSet.getString(AUTHOR_PUBLICATION));
            result.setNumberOfPages(resultSet.getInt(NUMBER_OF_PAGES));
            result.setSizeInBytes(resultSet.getInt(SIZE_IN_BITES));
            result.setPublicationDate(resultSet.getDate(DATE_OF_PUBLICATION));
            result.setInternetLink(resultSet.getString(INTERNET_LINK));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    private List<DigitalPublication> parseDigitalPublicationList(ResultSet resultSet) {
        try {
            List<DigitalPublication> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(extractDigitalPublication(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
