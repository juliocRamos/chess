package jogo_xadrez.xadrez.pecas;

import jogo_xadrez.boardgame.Posicao;
import jogo_xadrez.boardgame.Tabuleiro;
import jogo_xadrez.xadrez.CorPeca;
import jogo_xadrez.xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

    public Torre(CorPeca cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public boolean[][] movimentosPossiveis() {

        boolean[][] matAux
                = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao auxP = new Posicao(0, 0);

        // Acima
        auxP.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(auxP)
                && !getTabuleiro().existeUmaPeca(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
            auxP.setLinha(auxP.getLinha() - 1);
        }
        if (getTabuleiro().posicaoExiste(auxP) && existePrecaInimiga(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        // Esquerda
        auxP.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(auxP)
                && !getTabuleiro().existeUmaPeca(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
            auxP.setColuna(auxP.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExiste(auxP) && existePrecaInimiga(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        // Direita
        auxP.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(auxP)
                && !getTabuleiro().existeUmaPeca(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
            auxP.setColuna(auxP.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(auxP) && existePrecaInimiga(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        // Abaixo
        auxP.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(auxP)
                && !getTabuleiro().existeUmaPeca(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
            auxP.setLinha(auxP.getLinha() + 1);
        }
        if (getTabuleiro().posicaoExiste(auxP) && existePrecaInimiga(auxP)) {
            matAux[auxP.getLinha()][auxP.getColuna()] = true;
        }

        return matAux;
    }

    @Override
    public String toString() {
        return "T";
    }
}
