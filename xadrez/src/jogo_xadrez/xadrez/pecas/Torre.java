package jogo_xadrez.xadrez.pecas;

import jogo_xadrez.boardgame.Tabuleiro;
import jogo_xadrez.xadrez.CorPeca;
import jogo_xadrez.xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

    public Torre(CorPeca cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matTmp
                = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        return matTmp;
    }
}
