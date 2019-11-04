package com.mycompany.chess_game.xadrez;

import com.mycompany.chess_game.boardgame.Peca;
import com.mycompany.chess_game.boardgame.Tabuleiro;

public class PecaXadrez extends Peca {

    private CorPeca cor;

    public PecaXadrez(CorPeca cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    public CorPeca getCor() {
        return cor;
    }
}
