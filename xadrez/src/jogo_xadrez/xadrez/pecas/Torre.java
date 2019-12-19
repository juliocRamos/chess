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
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matTmp
                = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao auxPosi = new Posicao(0, 0);

        // Acima
        auxPosi.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(auxPosi)
                && !getTabuleiro().existeUmaPeca(auxPosi)) {
            matTmp[auxPosi.getLinha()][auxPosi.getColuna()] = true;
            auxPosi.setLinha(auxPosi.getLinha() - 1);
        }
        if (getTabuleiro().posicaoExiste(auxPosi) && existePrecaInimiga(auxPosi)) {
            matTmp[auxPosi.getLinha()][auxPosi.getColuna()] = true;
        }

        // Esquerda
        auxPosi.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(auxPosi)
                && !getTabuleiro().existeUmaPeca(auxPosi)) {
            matTmp[auxPosi.getLinha()][auxPosi.getColuna()] = true;
            auxPosi.setColuna(auxPosi.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExiste(auxPosi) && existePrecaInimiga(auxPosi)) {
            matTmp[auxPosi.getLinha()][auxPosi.getColuna()] = true;
        }

        // Direita
        auxPosi.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(auxPosi)
                && !getTabuleiro().existeUmaPeca(auxPosi)) {
            matTmp[auxPosi.getLinha()][auxPosi.getColuna()] = true;
            auxPosi.setColuna(auxPosi.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(auxPosi) && existePrecaInimiga(auxPosi)) {
            matTmp[auxPosi.getLinha()][auxPosi.getColuna()] = true;
        }

        // Abaixo
        auxPosi.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(auxPosi)
                && !getTabuleiro().existeUmaPeca(auxPosi)) {
            matTmp[auxPosi.getLinha()][auxPosi.getColuna()] = true;
            auxPosi.setLinha(auxPosi.getLinha() + 1);
        }
        if (getTabuleiro().posicaoExiste(auxPosi) && existePrecaInimiga(auxPosi)) {
            matTmp[auxPosi.getLinha()][auxPosi.getColuna()] = true;
        }

        return matTmp;
    }
}
