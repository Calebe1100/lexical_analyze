package utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TFIDFCalculator {

    public static double calculateTfIdf(Map<String, List<Integer>> wordList, String word) {
        int numAnswerWord = wordList.containsKey(word) ? wordList.get(word).size() : 0;
        int answerAmount = wordList.size();
        double idf = Math.log((double) answerAmount / (numAnswerWord + 1));
        double tf = (double) wordList.getOrDefault(word, Collections.emptyList()).size() / answerAmount;
        return tf * idf;
    }

    public static String findWordMaxTFIDF(Map<String, List<Integer>> wordList, String selectedWord) {
        String maxTfIdfWord = null;
        double maxTfIdf = Double.NEGATIVE_INFINITY;

        for (String word : wordList.keySet()) {
            double tfIdf = calculateTfIdf(wordList, selectedWord);

            if (tfIdf > maxTfIdf) {
                maxTfIdf = tfIdf;
                maxTfIdfWord = word;
            }
        }

        return maxTfIdfWord;
    }
}