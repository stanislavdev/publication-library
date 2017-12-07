package model.entity;

/**
 * Created by dvsta on 07.12.2017.
 */
public class Word {
    private int wordID;
    private String wordValue;

    public Word(int wordID, String wordValue) {
        this.wordID = wordID;
        this.wordValue = wordValue;
    }

    public int getWordID() {
        return wordID;
    }

    public String getWordValue() {
        return wordValue;
    }
}
