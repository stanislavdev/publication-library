package model.dao;

import model.entity.Word;

import java.util.List;

public interface WordDao {
    public List<Word> findAll();
}
