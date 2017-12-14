package model.dbservices;

import model.dao.DigitalPublicationDao;
import model.entity.DigitalPublication;
import model.entity.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dvsta on 08.12.2017.
 */
public class MySQLDigitalPublication implements DigitalPublicationDao {
    private static final String ID_PUBLICATION = "idpublication";
    private static final String NAME_PUBLICATION = "name";
    private static final String AUTHOR_PUBLICATION = "author";
    private static final String NUMBER_OF_PAGES = "number_of_pages";
    private static final String DATE_OF_PUBLICATION = "date_of_publication";
    private static final String INTERNET_LINK = "internet_link";
    private static final String SIZE_IN_BITES = "size_in_bites";

    Map<Integer, DigitalPublication> digitalPublicationMap = new HashMap<>();
    Map<Integer, Word> wordMap = new HashMap<>();

    private static final String SELECT_ALL_BY_TYPE_QUERY = "SELECT\n" +
            "  idpublication,\n" +
            "  name,\n" +
            "  author,\n" +
            "  number_of_pages,\n" +
            "  date_of_publication,\n" +
            "  internet_link,\n" +
            "  size_in_bites,\n" +
            "  words.idwords,\n" +
            "  words.word_value\n" +
            "FROM publication p\n" +
            "  LEFT JOIN publication_has_words word\n" +
            "    ON p.idpublication = word.publication_idpublication\n" +
            "  LEFT JOIN words ON word.idwords = words.idwords\n" +
            "WHERE type_of_publication = 'Digital' ORDER BY publication_idpublication, word.idwords";
    private static final String SELECT_ALL_REFERENCES_BY_TYPE_QUERY = "SELECT\n" +
            "  idpublication,\n" +
            "  name,\n" +
            "  author,\n" +
            "  number_of_pages,\n" +
            "  date_of_publication,\n" +
            "  internet_link,\n" +
            "  size_in_bites,\n" +
            "  words.idwords,\n" +
            "  words.word_value\n" +
            "FROM publication p\n" +
            "  LEFT JOIN publication_has_publication publication2\n" +
            "    ON p.idpublication = publication2.publication_idpublication1\n" +
            "  LEFT JOIN publication_has_words word ON p.idpublication = word.publication_idpublication\n" +
            "  JOIN words ON word.idwords = words.idwords\n" +
            "WHERE publication2.publication_idpublication = ? AND p.type_of_publication = 'Digital';";

    private Connection connection;

    public MySQLDigitalPublication(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<DigitalPublication> searchPublication(int numberOfPages, String author, String name,
                                                      Date publicationDate) {
        return null;
    }

    @Override
    public List<DigitalPublication> searchReferencePublications(int publicationId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REFERENCES_BY_TYPE_QUERY);
            preparedStatement.setInt(1, publicationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return extractDigitalPublication(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DigitalPublication> findAllPublications() {
        try {
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(SELECT_ALL_BY_TYPE_QUERY);
            return extractDigitalPublication(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<DigitalPublication> extractDigitalPublication(ResultSet resultSet) {
        List<DigitalPublication> resultList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Word word = MySQLWord.extractWord(resultSet);
                if (word.getWordValue() == null) {
                    word.setWordValue(" - ");
                }
                DigitalPublication result = new DigitalPublication();
                result.setId(resultSet.getInt(ID_PUBLICATION));
                result.setName(resultSet.getString(NAME_PUBLICATION));
                result.setAuthor(resultSet.getString(AUTHOR_PUBLICATION));
                result.setNumberOfPages(resultSet.getInt(NUMBER_OF_PAGES));
                result.setSizeInBytes(resultSet.getInt(SIZE_IN_BITES));
                result.setPublicationDate(resultSet.getDate(DATE_OF_PUBLICATION));
                result.setInternetLink(resultSet.getString(INTERNET_LINK));
                result = makeDigitalPublicaionUnique(digitalPublicationMap, result);
                word = makeWordUnique(wordMap, word);
                result.getKeyWords().add(word);

                if (!resultList.contains(result)){
                    resultList.add(result);
                }

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return resultList;
    }


//    private List<DigitalPublication> parseDigitalPublicationList(ResultSet resultSet) {
//        try {
//            List<DigitalPublication> result = new ArrayList<>();
//            while (resultSet.next()) {
//                result.add(extractDigitalPublication(resultSet));
//                for (int i = 0; i < result.size(); i++) {
//                    System.out.println("+" + result.get(i) + "===" + result.get(i).getKeyWords());
//                }
//            }
//            return result;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private DigitalPublication makeDigitalPublicaionUnique(Map<Integer, DigitalPublication> map,
                                                           DigitalPublication dp) {
        map.putIfAbsent(dp.getId(), dp);
        return map.get(dp.getId());
    }

    private Word makeWordUnique(Map<Integer, Word> map, Word w) {
        map.putIfAbsent(w.getWordID(), w);
        return map.get(w.getWordID());
    }
}
