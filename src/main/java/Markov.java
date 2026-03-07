import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Josue Nava - Jimenez
 * @version 0.2.0
 * @since 2/17/26
 *
 * File name - Markov.java
 * Class - CST 338: Software Design
 * Assignment - HW02: Markov Text Generator
 * Description: basic markov text generator that reads a file and creates random sentences from word patterns
 */

public class Markov {
    private static final String BEGINS_SENTENCE = "__$";
    private static final String PUNCTUATION_MARKS = ".!?$";

    private String prevWord;
    private HashMap<String, ArrayList<String>> words;

    /**
    * basic constructor. creates the object and initializes word map
    */
    public Markov() {
        words = new HashMap<>();
        words.put(BEGINS_SENTENCE, new ArrayList<>());
        prevWord = BEGINS_SENTENCE;
    }

    /**
     * returns the hashmap that stores the markov chain
     */
    public HashMap<String, ArrayList<String>> getWords() {
        return words; // maybe this one is correct
    }

    /**
     * reads from file and adds contents into the markov chain
     */
    public void addFromFile(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                addLine(sc.nextLine());
            }

            sc.close();
            fr.close();
        }

        catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }

    /**
     * splits lines into words
     */
    public void addLine(String line) {
        if (line.isEmpty()) { // the equivalence of empty calories
            return;
        }

        String[] split = line.split("\\s+");

        for (String word : split) {
            if (!word.isEmpty()) {
                addWord(word);
            }
        }
    }

    /**
     * adds words onto markov chain
     */
    void addWord(String word) {
        if (endsWithPunctuation(prevWord)) {
            words.get(BEGINS_SENTENCE).add(word);
        }

        else {
            if (!words.containsKey(prevWord)) {
                words.put(prevWord, new ArrayList<>());
            }

            words.get(prevWord).add(word);
        }

        prevWord = word;
    }

    /**
     * basic random sentence generator
     */
    public String getSentence() {
        StringBuilder sentence = new StringBuilder();

        String currentWord = randomWord(BEGINS_SENTENCE);

        while (!endsWithPunctuation(currentWord)) {
            sentence.append(currentWord).append(" ");
            currentWord = randomWord(currentWord);
        }

        sentence.append(currentWord);
        return sentence.toString();
    }

    /**
     * returns a random word after the given word
     */
    String randomWord(String word) {
        ArrayList<String> next= words.get(word);
        int index = (int)(Math.random() * next.size());

        return next.get(index);
    }

    /**
     * checks if a word ends with a punctuation (refer to PUNCTUATION_MARKS variable)
     */
    public static boolean endsWithPunctuation(String word) {
        if (word.isEmpty()) {
            return false; // had to fix the 9 out of tests passed :(
        }
        return PUNCTUATION_MARKS.indexOf(word.charAt(word.length() - 1)) != -1;
    }

    /**
     * string representation of the markov map
     */
    @Override
    public String toString() {
        return words.toString();
    }
}
