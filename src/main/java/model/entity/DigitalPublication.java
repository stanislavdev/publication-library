package model.entity;

import java.sql.Date;
import java.util.List;

/**
 * Created by dvsta on 02.12.2017.
 */
public class DigitalPublication extends Publication {
    private String internetLink;
    private int sizeInBytes;

    public DigitalPublication(int id, int numberOfPages, String author, String name, Date publicationDate,
                              List<Publication> references, List<Word> keyWords,
                              String internetLink, int sizeInBytes) {
        super(id, numberOfPages, author, name, publicationDate, references, keyWords);
        this.internetLink = internetLink;
        this.sizeInBytes = sizeInBytes;
    }
}
