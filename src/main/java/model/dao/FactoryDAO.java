package model.dao;

import model.dbservices.MySQLFactory;

/**
 * Created by dvsta on 08.12.2017.
 */
public abstract class FactoryDAO {
    private static FactoryDAO FACTORY_DAO;

    public abstract DigitalPublicationDao createDigitalPublicationDao();
    public abstract PaperPublicationDao createPaperPublicationDao();
    public abstract WordDao createWordDao();

    public static FactoryDAO getInstance(){
        if( FACTORY_DAO == null ){
            synchronized (FactoryDAO.class){
                if(FACTORY_DAO==null){
                    FACTORY_DAO = new MySQLFactory();
                }
            }
        }
        return FACTORY_DAO;
    }

}
