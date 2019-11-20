package jogo_xadrez;

import java.util.InputMismatchException;
import java.util.Scanner;
import jogo_xadrez.UI.UserInterface;
import jogo_xadrez.xadrez.PosicaoXadrez;
import jogo_xadrez.xadrez.PartidaXadrez;
import jogo_xadrez.xadrez.PecaXadrez;
import jogo_xadrez.xadrez.exceptions.XadrezException;

public class Program {

    public static void main(String[] args) {
        PartidaXadrez partida = new PartidaXadrez();

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                UserInterface.limparInterface();
                UserInterface.printBoard(partida.getPecas());
                System.out.println();
                System.err.print("Origem: ");
                PosicaoXadrez origem = UserInterface.lerPosicaoUsuario(sc);
                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = UserInterface.lerPosicaoUsuario(sc);

                PecaXadrez pecaMovimentada = partida.moverPeca(origem, destino);

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
