package jogo_xadrez.UI;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import jogo_xadrez.xadrez.CorPeca;
import jogo_xadrez.xadrez.PartidaXadrez;
import jogo_xadrez.xadrez.PecaXadrez;
import jogo_xadrez.xadrez.PosicaoXadrez;

//Classe que define as funcionalidades da interface com o usuário
public class UserInterface {

    //Definição de cores
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void printTabuleiro(PecaXadrez[][] pecas) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                printPeca(pecas[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printTabuleiro(PecaXadrez[][] pecas,
            boolean[][] movimentosPossiveis) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                printPeca(pecas[i][j], movimentosPossiveis[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printPartida(PartidaXadrez partida,
            List<PecaXadrez> pecasCapturadas) {
        printTabuleiro(partida.getPecas());
        System.out.println();
        printarPecasCapturadas(pecasCapturadas);
        System.out.println();
        System.out.println("Turno: " + partida.getTurno());
        System.out.println("Esperando a jogada do jogador: "
                + partida.getJogadorAtual());

        if (partida.isInCheck()) {
            System.out.println("CHECK!");
        }
    }

    private static void printPeca(PecaXadrez peca, boolean setBackgroundColor) {
        if (setBackgroundColor) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }

        if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (peca.getCor() == CorPeca.WHITE) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printarPecasCapturadas(List<PecaXadrez> pecasCapturadas) {
        List<PecaXadrez> white = pecasCapturadas
                .stream()
                .filter(e -> e.getCor() == CorPeca.WHITE)
                .collect(Collectors.toList());

        List<PecaXadrez> black = pecasCapturadas
                .stream()
                .filter(e -> e.getCor() == CorPeca.BLACK)
                .collect(Collectors.toList());

        System.out.print("Brancas: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));

        System.out.print("Pretas: ");
        System.out.print(ANSI_YELLOW);
        System.out.print(Arrays.toString(black.toArray()));
    }

    public static PosicaoXadrez lerPosicaoUsuario(Scanner sc) {
        try {
            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));

            return new PosicaoXadrez(coluna, linha);

        } catch (Exception ex) {
            throw new InputMismatchException("Erro ao ler a posição do xadrez");
        }
    }

    public static void limparInterface() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
