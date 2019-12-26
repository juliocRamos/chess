package jogo_xadrez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import jogo_xadrez.UI.UserInterface;
import jogo_xadrez.xadrez.PosicaoXadrez;
import jogo_xadrez.xadrez.PartidaXadrez;
import jogo_xadrez.xadrez.PecaXadrez;
import jogo_xadrez.xadrez.exceptions.XadrezException;

public class Program {

    public static void main(String[] args) {

        PartidaXadrez partida = new PartidaXadrez();
        List<PecaXadrez> pecasCapturadas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                UserInterface.limparInterface();
                UserInterface.printPartida(partida, pecasCapturadas);
                System.out.println();
                System.err.print("Origem: ");
                PosicaoXadrez origem = UserInterface.lerPosicaoUsuario(sc);

                boolean[][] movimentosPossiveis
                        = partida.movimentosPossiveis(origem);
                UserInterface.limparInterface();
                UserInterface.printTabuleiro(partida.getPecas(),
                        movimentosPossiveis);

                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = UserInterface.lerPosicaoUsuario(sc);
                PecaXadrez pecaMovida = partida.moverPeca(origem, destino);

                if (pecaMovida != null) {
                    pecasCapturadas.add(pecaMovida);
                }

            } catch (XadrezException ex) {
                System.out.println(ex.getMessage());
                sc.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println(ex.getMessage());
                sc.nextLine();
            }
        }
    }
}
