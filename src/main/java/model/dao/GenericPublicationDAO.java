package model.dao;

import model.entity.Publication;

import java.util.Date;
import java.util.List;

/**
 * Created by dvsta on 02.12.2017.
 */
public interface GenericPublicationDAO<T extends Publication> {
    public List<T> searchPublication( String numberOfPages, String author,
                                     String name,String publicationDate, String word);

    public List<T> searchReferencePublications(int publicationId);
    public List<T> findAllPublications();
}
