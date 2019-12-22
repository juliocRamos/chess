package jogo_xadrez.xadrez.pecas;

import jogo_xadrez.boardgame.Posicao;
import jogo_xadrez.boardgame.Tabuleiro;
import jogo_xadrez.xadrez.CorPeca;
import jogo_xadrez.xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei(CorPeca cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matTmp
                = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao auxP = new Posicao(0, 0);

        // Acima
        auxP.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matTmp[auxP.getLinha()][auxP.getColuna()] = true;
        }

        // Abaixo
        auxP.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matTmp[auxP.getLinha()][auxP.getColuna()] = true;
        }

        // Esquerda
        auxP.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matTmp[auxP.getLinha()][auxP.getColuna()] = true;
        }

        // Direita
        auxP.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matTmp[auxP.getLinha()][auxP.getColuna()] = true;
        }

        // Diagonal esquerda Superior (Noroeste)
        auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matTmp[auxP.getLinha()][auxP.getColuna()] = true;
        }

        // Diagonal esquerda Inferior (Sudoeste)
        auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matTmp[auxP.getLinha()][auxP.getColuna()] = true;
        }

        // Diagonal direita Superior (Nordeste)
        auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matTmp[auxP.getLinha()][auxP.getColuna()] = true;
        }

        // Diagonal direita Inferior (Sudeste)
        auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(auxP) && podeSerMovido(auxP)) {
            matTmp[auxP.getLinha()][auxP.getColuna()] = true;
        }

        return matTmp;
    }
}
