package katas.exercises;

import java.util.Arrays;

public class WordCounter {

    /**
     * Counts the number of words in a given sentence.
     *
     * @param sentence the input string (a sentence)
     * @return the number of words in the sentence
     */
    public static int countWords(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return 0;
        }
        return sentence.trim().split("\\s+").length;
    }

    public static void main(String[] args) {
        String sentence = "This is a sample     sentence for counting words.";
        int wordCount = countWords(sentence);
        System.out.println(wordCount);
    }
}

