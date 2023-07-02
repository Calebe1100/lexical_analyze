package useCases;

import java.util.Queue;

public abstract class ProcessTemplateUseCase {

    protected Queue<String> keyWords;

    public Queue<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Queue<String> keyWords) {
        this.keyWords = keyWords;
    }

    public ProcessTemplateUseCase(Queue<String> keyWords) {
        this.keyWords = keyWords;
        init();
    }

    protected abstract void init();

    protected abstract void identifyQuestion();

}
