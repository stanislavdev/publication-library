package model.dbservices;

import model.dao.PaperPublicationDao;
import model.entity.DigitalPublication;
import model.entity.PaperPublication;

import java.awt.print.Paper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLPaperPublication implements PaperPublicationDao {
    private static final String ID_PUBLICATION = "idpublication";
    private static final String NAME_PUBLICATION = "name";
    private static final String AUTHOR_PUBLICATION = "author";
    private static final String NUMBER_OF_PAGES = "number_of_pages";
    private static final String DATE_OF_PUBLICATION = "date_of_publication";
    private static final String PAPER_MAGAZINE = "name_of_paper_magazine";

    private static final String SELECT_ALL_REFERENCES_BY_TYPE_QUERY = "SELECT idpublication," +
            "name, author, number_of_pages, date_of_publication, name_of_paper_magazine " +
            " FROM publication p" +
            "  JOIN publication_has_publication publication2 " +
            "    ON p.idpublication = publication2.publication_idpublication1 " +
            " WHERE publication2.publication_idpublication = ? AND p.type_of_publication = 'Paper'";

    private Connection connection;

    public MySQLPaperPublication(Connection connection) {
        this.connection = connection;
    }

    private static final String SELECT_ALL_BY_TYPE_QUERY = "SELECT * FROM publication " +
            "WHERE  type_of_publication = 'Paper'";

    @Override
    public List<PaperPublication> searchPublication(int numberOfPages, String author, String name, Date publicationDate) {
        return null;
    }

    @Override
    public List<PaperPublication> searchReferencePublications(int publicationId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REFERENCES_BY_TYPE_QUERY);
            preparedStatement.setInt(1, publicationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parsePaperPublicationList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PaperPublication> findAllPublications() {
        try {
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(SELECT_ALL_BY_TYPE_QUERY);
            return parsePaperPublicationList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PaperPublication extractPaperPublication(ResultSet resultSet) {
        PaperPublication result = new PaperPublication();
        try {
            result.setId(resultSet.getInt(ID_PUBLICATION));
            result.setName(resultSet.getString(NAME_PUBLICATION));
            result.setAuthor(resultSet.getString(AUTHOR_PUBLICATION));
            result.setNumberOfPages(resultSet.getInt(NUMBER_OF_PAGES));
            result.setPublicationDate(resultSet.getDate(DATE_OF_PUBLICATION));
            result.setNameOfpaperMagazine(resultSet.getString(PAPER_MAGAZINE));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    private List<PaperPublication> parsePaperPublicationList(ResultSet resultSet) {
        try {
            List<PaperPublication> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(extractPaperPublication(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
