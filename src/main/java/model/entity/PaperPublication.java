package model.entity;

import java.sql.Date;
import java.util.List;

/**
 * Created by dvsta on 02.12.2017.
 */
public class PaperPublication extends Publication {
    private String nameOfpaperMagazine;

    public PaperPublication() {
    }

    public PaperPublication(int id, int numberOfPages, String author, String name,
                            Date publicationDate, List<Publication> references,
                            List<Word> keyWords, String nameOfMagazine) {
        super(id, numberOfPages, author, name, publicationDate, references, keyWords);
        this.nameOfpaperMagazine = nameOfMagazine;
    }

    public void setNameOfpaperMagazine(String nameOfpaperMagazine) {
        this.nameOfpaperMagazine = nameOfpaperMagazine;
    }

    public String getNameOfpaperMagazine() {
        return nameOfpaperMagazine;
    }

    @Override
    public String toString() {
        return super.toString() + "PaperPublication{" +
                "nameOfpaperMagazine='" + nameOfpaperMagazine + '\'' +
                '}';
    }
}
