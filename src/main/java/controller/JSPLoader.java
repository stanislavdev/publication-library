package controller;


import model.dao.DigitalPublicationDao;
import model.dao.PaperPublicationDao;
import model.dbservices.MySQLFactory;
import model.entity.DigitalPublication;
import model.entity.PaperPublication;
import model.entity.Word;

import java.util.List;

/**
 * Created by dvsta on 03.12.2017.
 */
public class JSPLoader {
    public String tableToHtmlSelectDigital() {
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDao digitalPublicationDao = mySQLFactory.createDigitalPublicationDao();
        return createFullTableFromDigitalList(digitalPublicationDao.findAllPublications());
    }

    public String tableToHtmlSelectPaper() {
        MySQLFactory mySQLFactory = new MySQLFactory();
        PaperPublicationDao paperPublicationDao = mySQLFactory.createPaperPublicationDao();
        return createFullTableFromPaperList(paperPublicationDao.findAllPublications());
    }

    public String tableToHtmlSelectDigitalReferences(int id) {
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDao digitalPublicationDao = mySQLFactory.createDigitalPublicationDao();
        return createFullTableFromDigitalList(digitalPublicationDao.searchReferencePublications(id));
    }

    public String tableToHtmlSelectPaperReferences(int id) {
        MySQLFactory mySQLFactory = new MySQLFactory();
        PaperPublicationDao paperPublicationDao = mySQLFactory.createPaperPublicationDao();
        return createFullTableFromPaperList(paperPublicationDao.searchReferencePublications(id));
    }

    public String tableToHtmlSelectDigitalSearch(String numberOfPages, String author, String name,
                                                 String publicationDate, String word) {
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDao digitalPublicationDao = mySQLFactory.createDigitalPublicationDao();
        return createFullTableFromDigitalList(digitalPublicationDao.searchPublication(
                numberOfPages, author, name, publicationDate, word));
    }


    private String createFullTableFromDigitalList(List<DigitalPublication> publications) {
        String result = "";
        for (int i = 0; i < publications.size(); i++) {
            for (int j = 0; j < publications.get(i).getKeyWords().size(); j++) {
                result += "<tr>" +
                        "<td> " + publications.get(i).getId() + "</td>" +
                        "<td> " + publications.get(i).getName() + "</td>" +
                        "<td> " + publications.get(i).getAuthor() + "</td>" +
                        "<td> " + publications.get(i).getNumberOfPages() + "</td>" +
                        "<td> " + publications.get(i).getPublicationDate() + "</td>" +
                        "<td> " + publications.get(i).getInternetLink() + "</td>" +
                        "<td> " + publications.get(i).getSizeInBytes() + "</td>" +
                        "<td> " + publications.get(i).getKeyWords().get(j).getWordValue() + "</td>" +
                        "</tr>";
            }
        }
        return result;
    }

    private String createFullTableFromPaperList(List<PaperPublication> publications) {
        String result = "";
        for (int i = 0; i < publications.size(); i++) {
            for (int j = 0; j < publications.get(i).getKeyWords().size(); j++) {
                result += "<tr>" +
                        "<td> " + publications.get(i).getId() + "</td>" +
                        "<td> " + publications.get(i).getName() + "</td>" +
                        "<td> " + publications.get(i).getAuthor() + "</td>" +
                        "<td> " + publications.get(i).getNumberOfPages() + "</td>" +
                        "<td> " + publications.get(i).getPublicationDate() + "</td>" +
                        "<td> " + publications.get(i).getNameOfpaperMagazine() + "</td>" +
                        "<td> " + publications.get(i).getKeyWords().get(j).getWordValue() + "</td>" +
                        "</tr>";
            }
        }
        return result;
    }
}
