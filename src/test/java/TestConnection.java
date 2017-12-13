import model.dao.DigitalPublicationDAO;
import model.dao.PaperPublicationDao;
import model.dbservices.MySQLFactory;
import org.junit.Test;

/**
 * Created by dvsta on 02.12.2017.
 */
public class TestConnection {

    @Test
    public void testExtractAllDigitalPublication() {
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDAO digitalPublicationDAO = mySQLFactory.createDigitalPublicationDAO();
        PaperPublicationDao paperPublicationDao = mySQLFactory.createPaperPublicationDao();
        System.out.println(digitalPublicationDAO.findAllPublications());
        System.out.println(paperPublicationDao.findAllPublications());
    }

    @Test
    public void testSearchStatement(){
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDAO digitalPublicationDAO = mySQLFactory.createDigitalPublicationDAO();
        PaperPublicationDao paperPublicationDao = mySQLFactory.createPaperPublicationDao();
        System.out.println(digitalPublicationDAO.searchReferencePublications(1));
        System.out.println(paperPublicationDao.searchReferencePublications(1));
    }
}
