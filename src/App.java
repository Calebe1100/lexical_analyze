import java.util.Scanner;

import analyze.GenerateLexicalUseCase;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        GenerateLexicalUseCase useCase = new GenerateLexicalUseCase();
        String comand = "";
        String question = "";
        do {
            System.out.println("Olá, como posso te ajudar?");
            question = "Qual o pacote tem serviço para idosos";

            useCase.execute(question);

            System.out.println(useCase.isSimilar("teste", "texte", 1));

            System.out.println("Você gostaria de realizar outra pergunta? (y/n)");
            comand = scanner.next();
        } while (comand != "n");
        useCase.execute("bom teste para começar");

    }
}
