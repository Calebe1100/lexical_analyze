import java.util.Scanner;

import useCases.lexical.GenerateLexicalUseCase;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        GenerateLexicalUseCase useCase = new GenerateLexicalUseCase();
        String comand = "";
        String question = "";
        do {
            System.out.println("Olá, como posso te ajudar?");
            question = "Quais viagens vão ocorrer no inverno ?";

            useCase.execute(question);

            System.out.println("Você gostaria de realizar outra pergunta? (y/n)");
            comand = scanner.next();
        } while (comand == "y");
        useCase.execute("bom teste para começar");

    }
}
