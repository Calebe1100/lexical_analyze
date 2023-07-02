import java.util.Scanner;

import useCases.GenerateLexicalUseCase;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        GenerateLexicalUseCase useCase = new GenerateLexicalUseCase();
        String comand = "";
        String question = "";
        do {
            System.out.println("Olá, como posso te ajudar?");
            question = "Quando vai ser a viagem para cabo frio, quero essa viagem para cabo frio para semana que vem";

            useCase.execute(question);

            System.out.println("Você gostaria de realizar outra pergunta? (y/n)");
            comand = scanner.next();
        } while (comand != "n");
        useCase.execute("bom teste para começar");

    }
}
