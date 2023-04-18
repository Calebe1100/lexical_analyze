import analyze.GenerateLexicalUseCase;

public class App {
    public static void main(String[] args) throws Exception {
        GenerateLexicalUseCase useCase = new GenerateLexicalUseCase();
        useCase.execute("bom teste para começar");
        System.out.println(useCase.isSimilar("teste", "texte", 1));

    }
}
