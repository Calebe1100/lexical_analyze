package useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import entities.KeyWordAndStatusObject;
import enums.KeyWordListType;
import enums.KeyWordStatus;
import messages.MessageErrors;
import messages.MessageResponse;

public class ProcessKeyWordUseCase {

    protected Queue<String> inputKeyWords;

    private List<String> identifyWordList;
    private List<KeyWordAndStatusObject> expectedWordList;

    private GenerateInvertedFileUseCase internalKeyWordList;
    private Map listOfWords;

    private String messageResponse;

    private String currentSelectedWord = "";
    private int expectedSizeKeyWords;

    public ProcessKeyWordUseCase(Queue<String> inputKeyWords) {
        this.identifyWordList = new ArrayList<String>();
        this.expectedWordList = new ArrayList<KeyWordAndStatusObject>();
        this.inputKeyWords = inputKeyWords;
        internalKeyWordList = new GenerateInvertedFileUseCase();
        this.listOfWords = this.internalKeyWordList.getListOfKeyWordLists();
        this.identifyQuestion();
    }

    protected void identifyQuestion() {

        String firstWord = this.inputKeyWords.peek();
        switch (firstWord.toLowerCase()) {
            case "quais":
                buildWhat();
                break;
            case "qual":
                buildWhich();
                break;
            case "quando":
                buildWhen();
                break;
            default:
                if (verifyPossibleReceiptWords(firstWord)) {
                    this.buildRecipient();
                } else {
                    this.messageResponse = MessageErrors.NOT_UDESTAND_ERROR;
                }
                break;
        }
    }

    private void buildWhich() {
        this.expectedSizeKeyWords = 1;
        this.expectedWordList.add(new KeyWordAndStatusObject(false, "clientes", KeyWordListType.CLIENT));
        checkRulesByTypes(this.inputKeyWords, new KeyWordListType[] { KeyWordListType.CLIENT },
                KeyWordStatus.MANDATORY);

    }

    private void buildRecipient() {
        this.expectedSizeKeyWords = 1;
        this.expectedWordList.add(new KeyWordAndStatusObject(false, "recebimento", KeyWordListType.CLIENT));
        this.expectedWordList.add(
                new KeyWordAndStatusObject(false, "pagamento", KeyWordListType.CLIENT));
        checkRulesByTypes(this.inputKeyWords, new KeyWordListType[] { KeyWordListType.CLIENT },
                KeyWordStatus.MANDATORY);

    }

    private void buildWhat() {
        this.expectedSizeKeyWords = 1;
        this.expectedWordList.add(new KeyWordAndStatusObject(false, "estações", KeyWordListType.SEASON));
        checkRulesByTypes(this.inputKeyWords, new KeyWordListType[] { KeyWordListType.SEASON },
                KeyWordStatus.MANDATORY);

    }

    private void buildWhen() {
        this.expectedSizeKeyWords = 1;
        this.expectedWordList.add(new KeyWordAndStatusObject(false, "destinos", KeyWordListType.DESTINY));
        checkRulesByTypes(this.inputKeyWords, new KeyWordListType[] { KeyWordListType.DESTINY },
                KeyWordStatus.MANDATORY);

    }

    private void checkRulesByTypes(Queue<String> inputKeyWords, KeyWordListType[] keyWordListTypes,
            KeyWordStatus mandatory) {

        int cont = 0;
        for (KeyWordListType keyWordType : keyWordListTypes) {
            cont = 0;
            for (String keyWord : inputKeyWords) {
                if (isWordType(keyWordType, keyWord, cont)) {
                    this.identifyWordList.add(this.currentSelectedWord);
                    setExpecWordList(keyWordType);
                }
                cont++;
            }
        }
        if (this.identifyWordList.size() != this.expectedSizeKeyWords) {
            this.checkWordMissing();
        }
        this.messageResponse = MessageResponse.PROCESSING_MESSAGE;
    }

    private void setExpecWordList(KeyWordListType keyWordType) {
        this.expectedWordList.forEach(word -> {
            if (word.keyWordListType == keyWordType) {
                word.checked = true;
            }
        });

    }

    private void checkWordMissing() {
        this.messageResponse = "Para qual .... você deseja?";

    }

    private boolean isWordType(KeyWordListType type, String word, int index) {
        Map listWords = this.internalKeyWordList.getListOfKeyWordLists();
        if (listWords.containsKey(type)) {
            KeyWordAndStatusObject keyWordAndStatusObject = (KeyWordAndStatusObject) listWords.get(type);
            String wordCompost = ConcatenateWord(word, index);
            if (keyWordAndStatusObject.words.contains(word) || keyWordAndStatusObject.words.contains(wordCompost)) {
                this.currentSelectedWord = wordCompost;
                return true;
            }
        }
        return false;
    }

    private String ConcatenateWord(String word, int index) {
        if (index < this.inputKeyWords.size() - 1) {
            return word + " " + this.inputKeyWords.stream().toList().get(index + 1);
        }
        return word;
    }

    private boolean verifyPossibleReceiptWords(String word) {
        List<String> values = (List<String>) this.listOfWords.get(KeyWordListType.RECEIPT);
        return values.contains(word);
    }

}
