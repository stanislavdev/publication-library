package model.dao;

import model.entity.Publication;

import java.util.List;

/**
 * Created by dvsta on 02.12.2017.
 */
public interface GenericDAO<T extends Publication> {
    public List<T> searchPublication(int numberOfPages, String author,
                                               String name, java.sql.Date publicationDate);

    public List<T> searchReferencePublications(int publicationId);
    public List<T> findAllPublications();

    void close();
}
