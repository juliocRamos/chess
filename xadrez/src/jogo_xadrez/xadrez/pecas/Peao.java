package jogo_xadrez.xadrez.pecas;

import jogo_xadrez.boardgame.Posicao;
import jogo_xadrez.boardgame.Tabuleiro;
import jogo_xadrez.xadrez.CorPeca;
import jogo_xadrez.xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    public Peao(CorPeca cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public boolean[][] movimentosPossiveis() {

        boolean[][] matAux
                = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao auxP = new Posicao(0, 0);

        // Uma posição acima para a peça branca
        if (getCor() == CorPeca.WHITE) {
            auxP.setValores(posicao.getLinha() - 1, posicao.getColuna());

            if (getTabuleiro().posicaoExiste(auxP)
                    && !getTabuleiro().existeUmaPeca(auxP)) {
                matAux[auxP.getLinha()][auxP.getColuna()] = true;
            }

            // Primeiro movimento (acima)... pode mover duas casas
            auxP.setValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao auxP2
                    = new Posicao(posicao.getLinha() - 1, posicao.getColuna());

            if ((getTabuleiro().posicaoExiste(auxP)
                    && !getTabuleiro().existeUmaPeca(auxP))
                    && (getTabuleiro().posicaoExiste(auxP2)
                    && !getTabuleiro().existeUmaPeca(auxP2))
                    && getContadorMovimentos() == 0) {
                matAux[auxP.getLinha()][auxP.getColuna()] = true;
            }

            // Diagonal esquerda Superior (Noroeste)
            auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(auxP) && existePrecaInimiga(auxP)) {
                matAux[auxP.getLinha()][auxP.getColuna()] = true;
            }

            // Diagonal direita Superior (Nordeste)
            auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(auxP) && existePrecaInimiga(auxP)) {
                matAux[auxP.getLinha()][auxP.getColuna()] = true;
            }
        } else {
            auxP.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(auxP)
                    && !getTabuleiro().existeUmaPeca(auxP)) {
                matAux[auxP.getLinha()][auxP.getColuna()] = true;
            }

            // Primeiro movimento (acima)... pode mover duas casas
            auxP.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao auxP2
                    = new Posicao(posicao.getLinha() + 1, posicao.getColuna());

            if ((getTabuleiro().posicaoExiste(auxP)
                    && !getTabuleiro().existeUmaPeca(auxP))
                    && (getTabuleiro().posicaoExiste(auxP2)
                    && !getTabuleiro().existeUmaPeca(auxP2))
                    && getContadorMovimentos() == 0) {
                matAux[auxP.getLinha()][auxP.getColuna()] = true;
            }

            // Diagonal esquerda Superior (Noroeste)
            auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(auxP) && existePrecaInimiga(auxP)) {
                matAux[auxP.getLinha()][auxP.getColuna()] = true;
            }

            // Diagonal direita Superior (Nordeste)
            auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(auxP) && existePrecaInimiga(auxP)) {
                matAux[auxP.getLinha()][auxP.getColuna()] = true;
            }
        }
        return matAux;
    }

    @Override
    public String toString() {
        return "P";
    }
}
