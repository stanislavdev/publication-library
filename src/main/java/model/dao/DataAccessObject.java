package model.dao;

import model.entity.Publication;

import java.util.List;

/**
 * Created by dvsta on 02.12.2017.
 */
public interface DataAccessObject {
    public List<Publication> searchPublication(int numberOfPages, String author,
                                               String name, java.sql.Date publicationDate);

    public List<Publication> searchReferencePublications(int publicationId);
    public List<Publication> createListOfPublication();

    void close();
}
