package useCases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateInvertedFileUseCase {

        private Map<String, List<Integer>> invertFile;

        public Map<String, List<Integer>> getInvertFile() {
                return invertFile;
        }

        public void setInvertFile(Map<String, List<Integer>> invertFile) {
                this.invertFile = invertFile;
        }

        GenerateInvertedFileUseCase() {
                Map<Integer, String> answers = new HashMap<>();
                answers.put(1, "Esta é uma resposta de exemplo");
                answers.put(2, "Outra resposta interessante");
                answers.put(3, "Uma resposta diferente");

                this.invertFile = getFileInverted(answers, " ");
        }

        static Map<String, List<Integer>> getFileInverted(Map<Integer, String> answers, String separator) {
                Map<String, List<Integer>> invertedFile = new HashMap<>();

                for (Map.Entry<Integer, String> entry : answers.entrySet()) {
                        int idAnswer = entry.getKey();
                        String answer = entry.getValue();
                        String[] words = answer.split(separator);

                        for (String word : words) {
                                if (invertedFile.containsKey(word)) {
                                        invertedFile.get(word).add(idAnswer);
                                } else {
                                        List<Integer> list = new ArrayList<>();
                                        list.add(idAnswer);
                                        invertedFile.put(word, list);
                                }
                        }
                }

                return invertedFile;
        }

        public static void saveFileInverted(Map<String, List<Integer>> fileInverted, String fileName) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        for (Map.Entry<String, List<Integer>> entry : fileInverted.entrySet()) {
                                String word = entry.getKey();
                                List<Integer> currency = entry.getValue();
                                writer.write(word + ": " + currency.toString());
                                writer.newLine();
                        }
                } catch (IOException e) {
                        System.out.println("Erro ao salvar o arquivo invertido: " + e.getMessage());
                }
        }
}
