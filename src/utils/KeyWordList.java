package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.KeyWordAndStatusObject;
import entities.enums.KeyWordListType;
import entities.enums.KeyWordStatus;

public class KeyWordList {

    private List<String> receiptList = Arrays.asList("aceita", "recebe", "registra", "admite", "namorado", "encontro");
    private List<String> destinyList = Arrays.asList("prado", "vila velha", "vila-velha", "vila velha, vila-velha",
            "cabo frio", "capitólio", "capitolio");
    private List<String> clientList = Arrays.asList("crianças", "estudantes ", "idosos ", "estrangeiros ");
    private List<String> paymentList = Arrays.asList("cartão de crédito", "boleto ", "transferência bancário",
            "via pix", "cartão de débito", "carnê");
    private List<String> seasonList = Arrays.asList("outono", "inverno ", "verão ", "primavera");

    private Map<KeyWordListType, KeyWordAndStatusObject> listOfKeyWordLists;

    public KeyWordList() {
        listOfKeyWordLists = new HashMap<>();

        listOfKeyWordLists.put(KeyWordListType.RECEIPT,
                new KeyWordAndStatusObject(receiptList, KeyWordStatus.MANDATORY));
        listOfKeyWordLists.put(KeyWordListType.DESTINY,
                new KeyWordAndStatusObject(destinyList, KeyWordStatus.MANDATORY));
        listOfKeyWordLists.put(KeyWordListType.CLIENT,
                new KeyWordAndStatusObject(clientList, KeyWordStatus.MANDATORY));
        listOfKeyWordLists.put(KeyWordListType.PAYMENT,
                new KeyWordAndStatusObject(paymentList, KeyWordStatus.MANDATORY));
        listOfKeyWordLists.put(KeyWordListType.SEASON,
                new KeyWordAndStatusObject(seasonList, KeyWordStatus.MANDATORY));
    }

    public Map<KeyWordListType, KeyWordAndStatusObject> getListOfKeyWordLists() {
        return this.listOfKeyWordLists;
    }

}
