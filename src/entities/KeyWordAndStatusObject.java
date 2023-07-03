package entities;

import java.util.List;

import entities.enums.KeyWordListType;
import entities.enums.KeyWordStatus;

public class KeyWordAndStatusObject {
    public List<String> words;
    public KeyWordStatus status;
    public String groupName;
    public boolean checked;
    public KeyWordListType keyWordListType;

    public KeyWordAndStatusObject(Boolean checked, String groupName, KeyWordListType keyWordListType) {
        this.groupName = groupName;
        this.checked = checked;
        this.keyWordListType = keyWordListType;
    }

    public KeyWordAndStatusObject(List<String> words, KeyWordStatus status) {
        this.status = status;
        this.words = words;
    }

}
