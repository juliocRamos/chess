package jogo_xadrez.xadrez;


import jogo_xadrez.boardgame.Peca;
import jogo_xadrez.boardgame.Tabuleiro;

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
