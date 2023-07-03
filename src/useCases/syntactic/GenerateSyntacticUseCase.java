package useCases.syntactic;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import entities.enums.KeyWordListType;
import useCases.translator.GenerateTranslatorUseCase;

public class GenerateSyntacticUseCase {

    private GenerateTranslatorUseCase generateTranslator;
    private List<String> tokensList;

    public GenerateSyntacticUseCase(Queue<String> tokens, KeyWordListType keyWordType) {
        this.tokensList = new ArrayList<>(tokens);
        // atualizar arquivos invertidos

        generateTranslator = new GenerateTranslatorUseCase(this.tokensList, keyWordType);

    }

    // private void setSymbolsTableInvertedFile() {
    // Map<Integer, String> symbolsTableHash = new HashMap<>();

    // int index = 0;
    // for (String symbol : this.tokens) {
    // symbolsTableHash.put(index, symbol);
    // index++;
    // }
    // this.invertedFileSymbolsTable = GenerateInvertedFileUseCase
    // .getFileInverted(symbolsTableHash, " ");

    // GenerateInvertedFileUseCase.saveFileInverted((invertedFileSymbolsTable),
    // "symbolsTable.txt");
    // }

}
