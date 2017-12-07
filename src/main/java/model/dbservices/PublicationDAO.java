package model.dbservices;

import model.dao.DataAccessObject;
import model.entity.Publication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvsta on 02.12.2017.
 */
public class PublicationDAO implements DataAccessObject {

    private String queryForReferences(int id) {
        return "SELECT publication.* FROM publication,publicationreferences " +
                "WHERE publicationreferences.first_id = " + id +
                " AND publication.id = publicationreferences.second_id ";
    }

    private String queryForSearch(int numberOfPages, String author,
                                  String name, java.sql.Date publicationDate) {
        return "SELECT * FROM publication WHERE " +
                " numberOfPage LIKE " + numberOfPages +
                " OR author LIKE " + author +
                " OR nameOfPublication LIKE " + name +
                " OR publicationDate LIKE " + publicationDate;
    }

    private String queryForFullTable() {
        return "SELECT * FROM publication";
    }


    @Override
    public List<Publication> searchPublication(int numberOfPages, String author,
                                               String name, java.sql.Date publicationDate) {
        ResultSet resultSet = QueryExecuter.execute(queryForSearch(numberOfPages, author, name, publicationDate));
        List<Publication> publications = new ArrayList<>();
        try {
            while (resultSet.next()) {
                publications.add(Publication.parsePublication(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publications;
    }

    @Override
    public List<Publication> createReferencesList(int id) {
        ResultSet resultSet = QueryExecuter.execute(queryForReferences(id));
        List<Publication> references = new ArrayList<>();
        try {
            while (resultSet.next()) {
                references.add(Publication.parsePublication(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return references;
    }

    @Override
    public List<Publication> createFullListOfPublication() {
        ResultSet resultSet = QueryExecuter.execute(queryForFullTable());
        List<Publication> references = new ArrayList<>();
        try {
            while (resultSet.next()) {
                references.add(Publication.parsePublication(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return references;
    }

    @Override
    public void close() {
        try {
            DBConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
