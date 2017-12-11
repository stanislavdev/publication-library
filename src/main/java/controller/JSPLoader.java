package controller;


import model.dao.DigitalPublicationDAO;
import model.dbservices.MySQLFactory;
import model.dbservices.PublicationDAO;
import model.entity.DigitalPublication;
import model.entity.Publication;

import java.util.List;

/**
 * Created by dvsta on 03.12.2017.
 */
public class JSPLoader {
    public String tableToHtmlSelect() {
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDAO digitalPublicationDAO = mySQLFactory.createDigitalPublicationDAO();
        return createFullTableFromList(digitalPublicationDAO.findAllPublications());
    }

    private String createFullTableFromList(List<DigitalPublication> publications) {
        String result = "";
        for (Publication publication : publications) {
            result += "<tr>" +
                    "<td> " + publication.getId() + "</td>" +
                    "<td> " + publication.getName() + "</td>" +
                    "<td> " + publication.getAuthor() + "</td>" +
                    "<td> " + publication.getNumberOfPages() + "</td>" +
                    "<td> " + publication.getPublicationDate() + "</td>" +
                    "</tr>";
        }
        return result;
    }
}
