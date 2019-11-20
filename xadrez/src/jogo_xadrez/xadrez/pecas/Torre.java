package com.mycompany.chess_game.xadrez.pecas;

import com.mycompany.chess_game.boardgame.Tabuleiro;
import com.mycompany.chess_game.xadrez.CorPeca;
import com.mycompany.chess_game.xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

    public Torre(CorPeca cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "T";
    }
}
