package model.entity;

import java.sql.Date;
import java.util.List;

/**
 * Created by dvsta on 02.12.2017.
 */
public class PaperPublication extends Publication {
    private String nameOfpaperMagazine;

    public PaperPublication(int id, int numberOfPages, String author, String name,
                            Date publicationDate, List<Publication> references,
                            List<Word> keyWords, String nameOfMagazine) {
        super(id, numberOfPages, author, name, publicationDate, references, keyWords);
        this.nameOfpaperMagazine = nameOfMagazine;
    }
}
