package model.impl;

import model.dao.PaperPublicationDao;
import model.entity.PaperPublication;
import model.entity.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLPaperPublication implements PaperPublicationDao {
    private static final String ID_PUBLICATION = "idpublication";
    private static final String NAME_PUBLICATION = "name";
    private static final String AUTHOR_PUBLICATION = "author";
    private static final String NUMBER_OF_PAGES = "number_of_pages";
    private static final String DATE_OF_PUBLICATION = "date_of_publication";
    private static final String PAPER_MAGAZINE = "name_of_paper_magazine";

    private Map<Integer, PaperPublication> paperPublicationMap = new HashMap<>();
    private Map<Integer, Word> wordMap = new HashMap<>();

    private static final String SELECT_ALL_REFERENCES_BY_TYPE_QUERY = "SELECT\n" +
            "  idpublication,\n" +
            "  name,\n" +
            "  author,\n" +
            "  number_of_pages,\n" +
            "  date_of_publication,\n" +
            "  name_of_paper_magazine,\n" +
            "  words.idwords,\n" +
            "  words.word_value\n" +
            "FROM publication p\n" +
            "  LEFT JOIN publication_has_publication publication2\n" +
            "    ON p.idpublication = publication2.publication_idpublication1\n" +
            "  LEFT JOIN publication_has_words word \n" +
            "    ON p.idpublication = word.publication_idpublication\n" +
            "  LEFT JOIN words  \n" +
            "    ON word.idwords = words.idwords\n" +
            "WHERE publication2.publication_idpublication = ? \n" +
            "      AND type_of_publication = 'Paper' " +
            " ORDER BY idpublication, word.idwords";

    private static final String SELECT_BY_ATTRIBUTES = "SELECT\n" +
            "  idpublication,\n" +
            "  name,\n" +
            "  author,\n" +
            "  number_of_pages,\n" +
            "  date_of_publication,\n" +
            "  name_of_paper_magazine,\n" +
            "  words.idwords,\n" +
            "  words.word_value\n" +
            "FROM publication\n" +
            "  LEFT JOIN publication_has_words word\n" +
            "    ON publication.idpublication = word.publication_idpublication\n" +
            "  LEFT JOIN words\n" +
            "    ON word.idwords = words.idwords\n" +
            "WHERE type_of_publication = 'Paper' AND(" +
            "     name = ? " +
            "      OR author = ? " +
            "      OR number_of_pages = ? " +
            "      OR date_of_publication = ? " +
            "      OR word_value = ? )" +
            " ORDER BY publication_idpublication, words.idwords ";

    private static final String SELECT_ALL_BY_TYPE_QUERY = "SELECT " +
            "              idpublication, " +
            "              name, " +
            "              author, " +
            "              number_of_pages, " +
            "              date_of_publication, " +
            "              name_of_paper_magazine, " +
            "              words.idwords, " +
            "              words.word_value " +
            "              FROM publication p " +
            "              LEFT JOIN publication_has_words word " +
            "                ON p.idpublication = word.publication_idpublication " +
            "              LEFT JOIN words ON word.idwords = words.idwords " +
            "              WHERE type_of_publication = 'Paper' ORDER BY idpublication, word.idwords";

    private Connection connection;

    MySQLPaperPublication(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PaperPublication> searchPublication(String numberOfPages, String author, String name,
                                                    String publicationDate, String word) {
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ATTRIBUTES);
            ps.setString(1, name);
            ps.setString(2, author);
            ps.setString(3, numberOfPages);
            ps.setString(4, publicationDate);
            ps.setString(5, word);
            ResultSet resultSet = ps.executeQuery();
            return createPaperPublicationList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PaperPublication> searchReferencePublications(int publicationId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REFERENCES_BY_TYPE_QUERY);
            preparedStatement.setInt(1, publicationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPaperPublicationList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PaperPublication> findAllPublications() {
        try {
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(SELECT_ALL_BY_TYPE_QUERY);
            return createPaperPublicationList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PaperPublication> createPaperPublicationList(ResultSet resultSet) {
        List<PaperPublication> resultList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Word word = MySQLWord.extractWord(resultSet);
                if (word.getWordValue() == null) {
                    word.setWordValue(" - ");
                }
                PaperPublication result = new PaperPublication();
                result.setId(resultSet.getInt(ID_PUBLICATION));
                result.setName(resultSet.getString(NAME_PUBLICATION));
                result.setAuthor(resultSet.getString(AUTHOR_PUBLICATION));
                result.setNumberOfPages(resultSet.getInt(NUMBER_OF_PAGES));
                result.setPublicationDate(resultSet.getDate(DATE_OF_PUBLICATION));
                result.setNameOfpaperMagazine(resultSet.getString(PAPER_MAGAZINE));
                result = makePaperPublicationUnique(paperPublicationMap, result);
                word = makeWordUnique(wordMap, word);
                result.getKeyWords().add(word);
                if (!resultList.contains(result)) {
                    resultList.add(result);
                }

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return resultList;
    }

    private PaperPublication makePaperPublicationUnique(Map<Integer, PaperPublication> map,
                                                        PaperPublication dp) {
        map.putIfAbsent(dp.getId(), dp);
        return map.get(dp.getId());
    }

    private Word makeWordUnique(Map<Integer, Word> map, Word w) {
        map.putIfAbsent(w.getWordID(), w);
        return map.get(w.getWordID());
    }
}
