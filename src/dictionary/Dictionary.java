package dictionary;

import java.util.ArrayList;

public class Dictionary {
    /**
     * List of words.
     */
    protected ArrayList<Word> wordList = new ArrayList<>();
    /**
     * Search result list ( Must be clear everytime using DictionaryManagement.dictionarySearcher ).
     */
    public ArrayList<Word> resultSearcher = new ArrayList<>();

}
