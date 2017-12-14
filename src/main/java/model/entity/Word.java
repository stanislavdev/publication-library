package model.entity;

import java.util.Objects;

/**
 * Created by dvsta on 07.12.2017.
 */
public class Word {
    private int wordID;
    private String wordValue;

    public Word() {
    }

    public Word(String wordValue) {
        this.wordValue = wordValue;
    }

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

    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    public void setWordValue(String wordValue) {
        this.wordValue = wordValue;
    }

    @Override
    public String toString() {
        return "Word{" + System.identityHashCode(this) +
                "wordID=" + wordID +
                ", wordValue='" + wordValue + '\'' +
                '}';
    }
}
