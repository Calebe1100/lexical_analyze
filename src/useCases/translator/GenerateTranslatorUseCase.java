package useCases.translator;

import java.util.List;
import java.util.Map;

import entities.enums.KeyWordListType;
import useCases.commom.GenerateInvertedFileUseCase;
import utils.TFIDFCalculator;
import utils.answers.Answers;

public class GenerateTranslatorUseCase {

    private GenerateInvertedFileUseCase generateInvertedFile;

    public GenerateTranslatorUseCase(List<String> tokens, KeyWordListType keyWordType) {
        generateInvertedFile = new GenerateInvertedFileUseCase();
        this.setAnswerByType(keyWordType);

        this.getAnswerByInput(tokens, generateInvertedFile.getInvertFile());
    }

    public List<String> answersSelected;

    public List<String> getIndexAnswerSelected() {
        return answersSelected;
    }

    public void setIndexAnswerSelected(List<String> answersSelected) {
        this.answersSelected = answersSelected;
    }

    public void setAnswerByType(KeyWordListType keyWordType) {
        if (keyWordType == KeyWordListType.SEASON) {
            this.answersSelected = Answers.ONE;
        } else if (keyWordType == KeyWordListType.PAYMENT) {
            this.answersSelected = Answers.TWO;
        } else if (keyWordType == KeyWordListType.CLIENT) {
            this.answersSelected = Answers.THREE;
        }
    }

    private void getAnswerByInput(List<String> tokens, Map<String, List<Integer>> invertFile) {

        double maxTFIDF = Double.MIN_VALUE;
        String wordMaxTFIDF = null;
        for (String token : tokens) {
            if (invertFile.containsKey(token)) {
                double tfidf = TFIDFCalculator.calculateTfIdf(invertFile, token);
                if (tfidf > maxTFIDF) {
                    wordMaxTFIDF = token;
                    maxTFIDF = tfidf;
                }
            }
        }

        String answerToReturn = getAnswerByToken(this.answersSelected, wordMaxTFIDF);
        System.out.println(answerToReturn);
    }

    private String getAnswerByToken(List<String> answersFiltered, String wordMaxTFIDF) {
        for (String answer : answersFiltered) {
            if (answer.contains(wordMaxTFIDF)) {
                return answer;
            }
        }
        return "Problemas no sistema, tente mais tarde";
    }
}
