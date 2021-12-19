package dictionary;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DictionaryManagement extends Dictionary {
    private final String IMPORT_FILE_PATH = "src/resources/data.json";
    private final String url =
            "https://script.google.com/macros/s/AKfycbz_g0cKMWhvQsyk4n83kwywXZRVauZ-Pjor6LHy9ZbsGM_Szia83P4DMySl34HevphM9w/exec";

    public DictionaryManagement() throws EngineException {
        System.setProperty(
                "freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
        Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
        importFromFile();
    }

    /**
     * Import dictionary from file.
     */
    public void importFromFile() {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(IMPORT_FILE_PATH)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object o : jsonArray) {
                Word newWord = new Word();

                JSONObject obj = (JSONObject) o;

                newWord.setWord((String) obj.get("word"));
                newWord.setWord_type((String) obj.get("word_type"));
                newWord.setPronunciation((String) obj.get("pronounciation"));

                ArrayList<String> tmp1 = new ArrayList<String>();
                JSONArray arrays1 = (JSONArray) obj.get("eplanations");
                Iterator<String> iterator1 = arrays1.iterator();

                while (iterator1.hasNext()) {
                    tmp1.add(iterator1.next());
                }
                newWord.setExplanations(tmp1);

                ArrayList<String> tmp2 = new ArrayList<String>();
                JSONArray arrays2 = (JSONArray) obj.get("usages");
                Iterator<String> iterator2 = arrays2.iterator();

                while (iterator2.hasNext()) {
                    tmp2.add(iterator2.next());
                }
                newWord.setUsages(tmp2);

                wordList.add(newWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add new word to dictionary.
     *
     * @param addingWord adding word
     * @return result state
     */
    public String addWord(Word addingWord) {
        if (wordList.contains(addingWord)) {
            return "Existing word!";
        }
        wordList.add(addingWord);
        return "Added!";
    }

    /**
     * Remove a word from dictionary.
     */
    public void removeWord(Word removeWord) {
        wordList.removeIf(word -> word.isSpelling(removeWord));
    }

    /**
     * Search list words start with target word.
     */
    public ArrayList<Word> dictionarySearcher(String searchWord) {
        resultSearcher.clear();
        if (searchWord.equals("")) {
            for (int i = 0; i < wordList.size(); i++) {
                resultSearcher.add(wordList.get(i));
            }
            return resultSearcher;
        }
        searchWord = searchWord.toLowerCase();
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getWord().length() >= searchWord.length()) {
                String s = wordList.get(i).getWord().substring(0, searchWord.length());
                if (s.equals(searchWord)) {
                    resultSearcher.add(wordList.get(i));
                }
            }
        }
        return resultSearcher;
    }

    /**
     * text to speech.
     */
    public void textToSpeech(String text) throws EngineException {
        try {
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.speakPlainText(text, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
