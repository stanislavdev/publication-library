package model.entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dvsta on 02.12.2017.
 */
public class Publication {
    private int id;
    private int numberOfPages;
    private String author;
    private String name;
    private Date publicationDate;
    private List<Publication> references;
    private List<Word> keyWords;

    public Publication(int id, int numberOfPages, String author,
                       String name, Date publicationDate,
                       List<Publication> references, List<Word> keyWords) {
        this.id = id;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.name = name;
        this.publicationDate = publicationDate;
        this.references = references;
        this.keyWords = keyWords;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public List<Publication> getReferences() {
        return references;
    }

    public List<Word> getKeyWords() {
        return keyWords;
    }
}
