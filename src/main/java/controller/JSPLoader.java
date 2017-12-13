package controller;


import model.dao.DigitalPublicationDAO;
import model.dao.PaperPublicationDao;
import model.dbservices.MySQLFactory;
import model.entity.DigitalPublication;
import model.entity.PaperPublication;

import java.util.List;

/**
 * Created by dvsta on 03.12.2017.
 */
public class JSPLoader {
    public String tableToHtmlSelectDigital() {
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDAO digitalPublicationDAO = mySQLFactory.createDigitalPublicationDAO();
        return createFullTableFromDigitalList(digitalPublicationDAO.findAllPublications());
    }

    public String tableToHtmlSelectPaper(){
        MySQLFactory mySQLFactory = new MySQLFactory();
        PaperPublicationDao paperPublicationDao = mySQLFactory.createPaperPublicationDao();
        return createFullTableFromPaperList(paperPublicationDao.findAllPublications());
    }

    public String tableToHtmlSelectDigitalReferences(int id) {
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDAO digitalPublicationDAO = mySQLFactory.createDigitalPublicationDAO();
        return createFullTableFromDigitalList(digitalPublicationDAO.searchReferencePublications(id));
    }

    public String tableToHtmlSelectPaperReferences(int id) {
        MySQLFactory mySQLFactory = new MySQLFactory();
        PaperPublicationDao paperPublicationDao = mySQLFactory.createPaperPublicationDao();
        return createFullTableFromPaperList(paperPublicationDao.searchReferencePublications(id));
    }


    private String createFullTableFromDigitalList(List<DigitalPublication> publications) {
        String result = "";
        for (DigitalPublication publication : publications) {
            result += "<tr>" +
                    "<td> " + publication.getId() + "</td>" +
                    "<td> " + publication.getName() + "</td>" +
                    "<td> " + publication.getAuthor() + "</td>" +
                    "<td> " + publication.getNumberOfPages() + "</td>" +
                    "<td> " + publication.getPublicationDate() + "</td>" +
                    "<td> " + publication.getInternetLink() + "</td>" +
                    "<td> " + publication.getSizeInBytes() + "</td>" +
                    "</tr>";
        }
        return result;
    }

    private String createFullTableFromPaperList(List<PaperPublication> publications) {
        String result = "";
        for (PaperPublication publication : publications) {
            result += "<tr>" +
                    "<td> " + publication.getId() + "</td>" +
                    "<td> " + publication.getName() + "</td>" +
                    "<td> " + publication.getAuthor() + "</td>" +
                    "<td> " + publication.getNumberOfPages() + "</td>" +
                    "<td> " + publication.getPublicationDate() + "</td>" +
                    "<td> " + publication.getNameOfpaperMagazine() + "</td>" +
                    "</tr>";
        }
        return result;
    }
}
