package analyze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utils.LevenshteinDistance;

public class GenerateLexicalUseCase {


    private List<String> symbolsTable;
    private List<String> allWords;
    private List<String> keysWords;
    private List<String> stopWords;
    private Queue<String> queue;
    private String input;
    
    public GenerateLexicalUseCase(){
        symbolsTable = new ArrayList<String>();
        allWords = new ArrayList<String>();
        keysWords = new ArrayList<String>();
        stopWords = new ArrayList<String>();
        queue = new LinkedList<String>();
    }

    public void execute(String input) {
        this.input = input;
        this.allWords = splitQuery(input);

        setWordsQueue();
        setWordKeys();
        setStopWords();
        setSymbolsTable();

        allWords.removeAll(this.stopWords);
        
    }

    private void setWordsQueue() {
        queue.addAll(splitQuery(this.input));
    }

    private void setSymbolsTable() {
        List<String> auxWords = this.allWords;

        auxWords.removeAll(keysWords);
        auxWords.removeAll(stopWords);

        this.symbolsTable = auxWords;
    }

    private void setWordKeys() {

        try {
            FileReader fr = new FileReader("key_words.txt");
            BufferedReader br = new BufferedReader(fr);

            String word;

            while((word = br.readLine()) != null) {
                this.keysWords.add(word);
            }

            br.close();
        } catch (IOException e) {
            System.err.println("Arquivo de key_words.txt não encontrado!");
        }


    }

    private void setStopWords() {
        try {
            FileReader fr = new FileReader("stop_words.txt");
            BufferedReader br = new BufferedReader(fr);

            String word;

            while((word = br.readLine()) != null) {
                this.stopWords.add(word);
            }

            br.close();
        } catch (IOException e) {
            System.err.println("Arquivo de stop_words.txt não encontrado!");
        }
    }

    public boolean isAlphabetContains(String word) {
        String regex = "[\\p{L}\\p{N}\\p{Punct}&&[^\\[\\]{}()<>«»‘’“”‘’\"'`´^~¨¬¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¹º»¼½¾¿×÷]]";

        if (word.matches(regex))
            return true;

        return false;
    }
    
    public static boolean isSimilar(String s1, String s2, int maxError) {
        
        return LevenshteinDistance.getDistance(s1, s2) < maxError;  
    }

    private ArrayList<String> splitQuery(String query) {
        ArrayList<String> words =
                Stream.of(query.toLowerCase().split(" "))
                        .collect(Collectors.toCollection(ArrayList<String>::new));

        return words;
    }
}
