import controller.JSPLoader;

import model.dbservices.DBConnection;
import model.dbservices.PublicationDAO;
import model.entity.Publication;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by dvsta on 02.12.2017.
 */
public class TestConnection {

    @Test
    public void test() {
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from publication");
            resultSet.next();
            System.out.println(resultSet.getString(3));
            Assert.assertTrue(resultSet.getInt(1) == 1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testDAO() {
        PublicationDAO publicationDAO = new PublicationDAO();
        System.out.println(publicationDAO.createReferencesList(1));
        for (Publication p : publicationDAO.createReferencesList(1)
                ) {
            System.out.println(p.getId() + "," + p.getName() + "," + p.getAuthor());
        }

        for (Publication p : publicationDAO.searchPublication(15, null, null, null)
                ) {
            System.out.println(p.getId() + "," + p.getName() + "," + p.getAuthor());
        }
    }

    @Test
    public void testJSPLoader() {
        JSPLoader jspLoader = new JSPLoader();
        System.out.println(jspLoader.tableToHtmlSelect());
    }
}
