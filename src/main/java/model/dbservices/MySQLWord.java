package model.dbservices;

import model.dao.WordDao;
import model.entity.Word;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLWord implements WordDao {
    private Connection connection;

    private static final String ID_WORD = "words.idwords";
    private static final String VALUE = "words.word_value";

    public MySQLWord(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Word> findAll() {
        return null;
    }

    static public Word extractWord(ResultSet resultSet) {
        Word result = new Word();
        try {
            result.setWordID(resultSet.getInt(ID_WORD));
            result.setWordValue(resultSet.getString(VALUE));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return result;
    }
}
