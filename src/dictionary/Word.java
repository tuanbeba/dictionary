package dictionary;

import java.util.ArrayList;

public class Word {
    private String word;
    private String word_type;
    private ArrayList<String> explanations;
    private ArrayList<String> usages;
    private String pronunciation;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord_type() {
        return word_type;
    }

    public void setWord_type(String word_type) {
        this.word_type = word_type;
    }

    public ArrayList<String> getExplanations() {
        return explanations;
    }

    public void setExplanations(ArrayList<String> explanations) {
        this.explanations = explanations;
    }

    public ArrayList<String> getUsages() {
        return usages;
    }

    public void setUsages(ArrayList<String> usages) {
        this.usages = usages;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public Word(
            String word,
            String word_type,
            ArrayList<String> explanations,
            ArrayList<String> usages,
            String pronunciation) {
        this.word = word;
        this.word_type = word_type;
        this.explanations = explanations;
        this.usages = usages;
        this.pronunciation = pronunciation;
    }

    public Word() {
        this.word = "";
        this.word_type = "";
        this.explanations = new ArrayList<String>();
        this.usages = new ArrayList<String>();
        this.pronunciation = "";
    }
    /**
     * Compare two words spelling.
     */
    public Boolean isSpelling(Word thatWord) {
        return this.word.equals(thatWord.getWord());
    }

}
