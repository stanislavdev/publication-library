package model.entity;

import java.sql.Date;
import java.util.List;

/**
 * Created by dvsta on 02.12.2017.
 */
public class DigitalPublication extends Publication {
    private String internetLink;
    private int sizeInBytes;

    public DigitalPublication() {
    }

    public DigitalPublication(int id, int numberOfPages, String author, String name, Date publicationDate,
                              List<Publication> references, List<Word> keyWords,
                              String internetLink, int sizeInBytes) {
        super(id, numberOfPages, author, name, publicationDate, references, keyWords);
        this.internetLink = internetLink;
        this.sizeInBytes = sizeInBytes;
    }

    public String getInternetLink() {
        return internetLink;
    }

    public void setInternetLink(String internetLink) {
        this.internetLink = internetLink;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    @Override
    public String toString() {
        return super.toString() + "[DigitalPublication{" +
                "internetLink='" + internetLink + '\'' +
                ", sizeInBytes=" + sizeInBytes +
                "}]";
    }
}
