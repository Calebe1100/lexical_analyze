package utils;

import java.util.Arrays;
import java.util.List;

public class WordsAttribution {

    public static List<String> recipients = Arrays.asList("Aceita ", "Recebe", " Registra", "Admite");

    public static String generateReceiptWords(List<String> words) {
        if (words.containsAll(recipients)) {
            for (String word : words) {
                for (String recipient : recipients) {
                    if (word == recipient) {
                        return word;
                    }
                }
            }
        }
        return null;
    }

}
