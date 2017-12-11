package model.dbservices;

import model.dao.GenericDAO;
import model.entity.Publication;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvsta on 02.12.2017.
 */
public class PublicationDAO implements GenericDAO {

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
    public List<Publication> searchPublication(int numberOfPages, String author, String name, Date publicationDate) {
        return null;
    }

    @Override
    public List<Publication> searchReferencePublications(int publicationId) {
        return null;
    }

    @Override
    public List findAllPublications() {
        return null;
    }

//    @Override
//    public List<Publication> findAllPublications() {
//        ResultSet resultSet = QueryExecuter.execute(queryForFullTable());
//        List<Publication> references = new ArrayList<>();
//        try {
//            while (resultSet.next()) {
//                references.add(Publication.parsePublication(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return references;
//    }

    @Override
    public void close() {
        try {
            DBConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
