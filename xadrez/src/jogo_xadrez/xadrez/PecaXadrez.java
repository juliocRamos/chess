package jogo_xadrez.xadrez;

import jogo_xadrez.boardgame.Peca;
import jogo_xadrez.boardgame.Posicao;
import jogo_xadrez.boardgame.Tabuleiro;

public abstract class PecaXadrez extends Peca {

    private CorPeca cor;

    public PecaXadrez(CorPeca cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    public CorPeca getCor() {
        return cor;
    }

    protected boolean existePrecaInimiga(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }
}
