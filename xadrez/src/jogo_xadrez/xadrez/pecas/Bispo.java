package jogo_xadrez.xadrez.pecas;

import jogo_xadrez.boardgame.Posicao;
import jogo_xadrez.boardgame.Tabuleiro;
import jogo_xadrez.xadrez.CorPeca;
import jogo_xadrez.xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

    public Bispo(CorPeca cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matAux
                = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao auxP = new Posicao(0, 0);

        // Noroeste
        auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(auxP)
                && !getTabuleiro().existeUmaPeca(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
            auxP.setValores(auxP.getLinha() - 1, auxP.getColuna() - 1);
        }

        // Nordeste
        auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(auxP)
                && !getTabuleiro().existeUmaPeca(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
            auxP.setValores(auxP.getLinha() - 1, auxP.getColuna() + 1);
        }

        // Sudeste
        auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(auxP)
                && !getTabuleiro().existeUmaPeca(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
            auxP.setValores(auxP.getLinha() + 1, auxP.getColuna() + 1);
        }

        // Sudoeste
        auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(auxP)
                && !getTabuleiro().existeUmaPeca(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
            auxP.setValores(auxP.getLinha() + 1, auxP.getColuna() - 1);
        }

        return matAux;
    }

    @Override
    public String toString() {
        return "B";
    }
}
