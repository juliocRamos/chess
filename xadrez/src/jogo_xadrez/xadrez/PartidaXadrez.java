package com.mycompany.chess_game.xadrez;

import com.mycompany.chess_game.boardgame.Tabuleiro;
import com.mycompany.chess_game.xadrez.pecas.Rei;
import com.mycompany.chess_game.xadrez.pecas.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        this.setupInicial();
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] matriz
                = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getLinhas()];

        for (int i = 0; i < tabuleiro.getColunas(); i++) {
            for (int j = 0; j < tabuleiro.getLinhas(); j++) {
                matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return matriz;
    }

    private void adicionarNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.adicionarPeca(peca,
                new PosicaoXadrez(coluna, linha).toPosition());
    }

    //Inicia a partida, colocando as peças
    private void setupInicial() {
        adicionarNovaPeca('c', 7, new Rei(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('c', 8, new Torre(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('d', 7, new Torre(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('e', 7, new Torre(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('e', 8, new Torre(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('d', 8, new Torre(CorPeca.WHITE, tabuleiro));

        adicionarNovaPeca('c', 2, new Rei(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('d', 2, new Torre(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('e', 2, new Torre(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('e', 1, new Torre(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('d', 1, new Torre(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('a', 3, new Torre(CorPeca.BLACK, tabuleiro));
    }
}
