package jogo_xadrez;

import com.mycompany.chess_game.UI.UserInterface;
import com.mycompany.chess_game.xadrez.PartidaXadrez;

public class Program {
    public static void main(String[] args) {
        PartidaXadrez partida = new PartidaXadrez();
        UserInterface.printBoard(partida.getPecas());
    }
}
