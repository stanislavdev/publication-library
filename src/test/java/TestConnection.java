import model.dao.DigitalPublicationDao;
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
        DigitalPublicationDao digitalPublicationDao = mySQLFactory.createDigitalPublicationDao();
        PaperPublicationDao paperPublicationDao = mySQLFactory.createPaperPublicationDao();
        System.out.println(digitalPublicationDao.findAllPublications());
        System.out.println(paperPublicationDao.findAllPublications());
    }

    @Test
    public void testSearchStatement(){
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDao digitalPublicationDao = mySQLFactory.createDigitalPublicationDao();
        PaperPublicationDao paperPublicationDao = mySQLFactory.createPaperPublicationDao();
        System.out.println(digitalPublicationDao.findAllPublications().get(0));
        System.out.println(digitalPublicationDao.findAllPublications().get(1));
        System.out.println(digitalPublicationDao.searchReferencePublications(1));
        System.out.println(paperPublicationDao.searchReferencePublications(1));
    }

    @Test
    public void testKeWords(){
        MySQLFactory mySQLFactory = new MySQLFactory();
        DigitalPublicationDao digitalPublicationDao = mySQLFactory.createDigitalPublicationDao();
        System.out.println(digitalPublicationDao.findAllPublications());
    }
}
