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
    private static final String ID_PUBLICATION = "idpublication";
    private static final String NAME_PUBLICATION = "name";
    private static final String AUTHOR_PUBLICATION = "author";
    private static final String NUMBER_OF_PAGES = "number_of_pages";
    private static final String DATE_OF_PUBLICATION = "date_of_publication";
    private static final String INTERNET_LINK = "internet_link";
    private static final String SIZE_IN_BITES = "size_in_bites";

    private static final String SELECT_ALL_BY_TYPE_QUERY = "SELECT * FROM publication " +
            "WHERE  type_of_publication = 'Digital'";
    private static final String SELECT_ALL_REFERENCES_BY_TYPE_QUERY = "SELECT idpublication," +
            "name, author, number_of_pages, date_of_publication, internet_link, size_in_bites " +
            " FROM publication p" +
            "  JOIN publication_has_publication publication2 " +
            "    ON p.idpublication = publication2.publication_idpublication1 " +
            " WHERE publication2.publication_idpublication = ? AND p.type_of_publication = 'Digital'";

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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REFERENCES_BY_TYPE_QUERY);
            preparedStatement.setInt(1, publicationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseDigitalPublicationList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
