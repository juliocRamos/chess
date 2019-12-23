package jogo_xadrez.xadrez.pecas;

import jogo_xadrez.boardgame.Posicao;
import jogo_xadrez.boardgame.Tabuleiro;
import jogo_xadrez.xadrez.CorPeca;
import jogo_xadrez.xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

    public Cavalo(CorPeca cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public boolean[][] movimentosPossiveis() {

        boolean[][] matAux
                = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao auxP = new Posicao(0, 0);

        auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        auxP.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        auxP.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        auxP.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        auxP.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        return matAux;
    }

    @Override
    public String toString() {
        return "C";
    }
}
