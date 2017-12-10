package controller;


import model.dbservices.PublicationDAO;
import model.entity.Publication;

import java.util.List;

/**
 * Created by dvsta on 03.12.2017.
 */
public class JSPLoader {
    public String tableToHtmlSelect() {
        PublicationDAO dataAccessObject = new PublicationDAO();
        return createFullTableFromList(dataAccessObject.findAllPublications());
    }

    private String createFullTableFromList(List<Publication> publications) {
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
