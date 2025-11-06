package br.com.dio;

import br.com.dio.model.Board;
import br.com.dio.model.Space;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static br.com.dio.util.BoardTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    private static Board board;

    private final static int BOARD_LIMIT = 9;

    public static void main(String[] args) {
        final var positions = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
        var option = -1;
        while (true){
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Vísualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option){
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção invalida, selicione uma opção do menu");
            }

        }
    }

    private static void startGame(Map<String, String> positions) {
        if (nonNull(board)){
            System.out.println("O jogo foi iniciado");
            return;
        }

        List<List<Space>> space = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            space.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = positions.get("%s,%s".formatted(i,j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                space.get(i).add(currentSpace);
            }
        }
        board = new Board(space);
        System.out.println("o jogo esta pronto para começar");

    }
    private static void inputNumber() {
        if (isNull(board)){
            System.out.println("o jogo ainda não foi iniciado");
            return;
        }
        System.out.println("infome a coluna em que o numero sera inserido");
        var col = runUtilGetValidNumber(0, 8);
        System.out.println("infome a linha em que o numero sera inserido");
        var row = runUtilGetValidNumber(0, 8);
        System.out.printf("informe o numero  que vai entrar na posição [%s, %s]",col,row);
        var value = runUtilGetValidNumber(1, 9);
        if (board.changeValue(col, row, value)){
            System.out.printf("a posição [%s, %s] tem um valor fixo\n", col, row);
        }
    }

    private static void removeNumber() {
        if (isNull(board)){
            System.out.println("o jogo ainda não foi iniciado");
            return;
        }
        System.out.println("infome a coluna em que o numero sera inserido");
        var col = runUtilGetValidNumber(0, 8);
        System.out.println("infome a linha em que o numero sera inserido");
        var row = runUtilGetValidNumber(0, 8);
        System.out.printf("informe o numero  que vai entrar na posição [%s, %s]",col,row);
        if (board.clearValue(col, row)){
            System.out.printf("a posição [%s, %s] tem um valor fixo\n", col, row);
        }
    }
    private static void showCurrentGame() {
        if (isNull(board)){
            System.out.println("o jogo ainda não foi iniciado");
            return;
        }
        var args = new Object[81];
        var argPos = 0;
        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (var col: board.getSpaces()){
                args[argPos ++] =  " " + ((isNull(col.get(i).getActual()))? " ": col.get(i).getActual());
            }

        }
        System.out.println("seu jogo se encontra da seguinte forma: ");
        System.out.printf((BOARD_TEMPLATE) + "\n", args);
    }
    private static void showGameStatus() {
        if (isNull(board)){
            System.out.println("o jogo ainda não foi iniciado");
            return;
        }
        System.out.printf("o jogo se encontra atualmente no status %s \n", board.getStatus().getLabel());
        if (board.hasErrors()){
            System.out.println("O jogo contem erros");
        }else{
            System.out.println("O jogo nao contem erros");
        }
    }
    private static void clearGame() {
        if (isNull(board)){
            System.out.println("o jogo ainda não foi iniciado");
            return;
        }
        System.out.println("tem certeza que deseja limpar seu jogo");
        var confirm = scanner.next();
        while (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não")){
            System.out.println("informe 'sim'  ou 'não' ");
            confirm = scanner.next();

        }
        if (confirm.equalsIgnoreCase("sim")){
            board.reset();
        }
    }
    private static void finishGame() {
        if (isNull(board)){
            System.out.println("o jogo ainda não foi iniciado");
            return;
        }

        if (board.gameIsFinished()){
            System.out.println("parabens você concluiu o jogo");
        }else if (board.hasErrors()){
            System.out.println("seu jogo tem erro verifique seu board o ajuste-o");
        }else{
            System.out.println("você ainda precisa preechenr algum espaço");
        }
    }









    private static int runUtilGetValidNumber(final int min, final int max){
        var current = scanner.nextInt();
        while (current < min || current > max){
            System.out.printf("informe um numero %s e %s\n",min, max);
            current = scanner.nextInt();
        }
        return current;
    }


}
