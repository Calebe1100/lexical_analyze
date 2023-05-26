package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GenerateAnswer {
    private Map<Integer, String> answers = new HashMap<>();

    public void fillAnswer() throws FileNotFoundException {
        File resposta = new File("src/files/answers.txt");
        Scanner inArchive = new Scanner(resposta);
        int position = 0;
        while (inArchive.hasNext()) {
            answers.put(position, inArchive.nextLine());
            position++;
        }
    }

}
