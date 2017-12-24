package model.impl;

import model.entity.Word;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLWord {
    private Connection connection;

    private static final String ID_WORD = "words.idwords";
    private static final String VALUE = "words.word_value";

    public MySQLWord(Connection connection) {
        this.connection = connection;
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
