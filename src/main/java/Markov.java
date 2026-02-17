import java.util.ArrayList;
import java.util.HashMap;

public class Markov {
    private static final String BEGINS_SENTENCE = "";
    private static final String PUNCTUATION_MARKS = "";

    private String prevWord;
    private HashMap<String, ArrayList<String>> words;

    public Markov() {
        words = new HashMap<>();
        prevWord = BEGINS_SENTENCE;
    }

    public HashMap<String, ArrayList<String>> getWords() {
        return words; // maybe this one is correct
    }

    public void addFromFile(String filename) {
        // todo: finish
    }

    public void addLine(String line) {
        // todo: and this
    }

    void addWord(String word) {
        // todo: finish this too
    }

    public String getSentence() {
        return ""; // maybe filler lowkey
    }

    String randomWord(String word) {
        return ""; // filler
    }

    public boolean endsWithPunctuation(String word) {
        return true; // also filler
    }

    @Override
    public String toString() {
        return "Hello, world!"; // hello
    }
}
