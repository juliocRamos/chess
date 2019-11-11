package com.mycompany.chess_game.xadrez;

import com.mycompany.chess_game.boardgame.Posicao;
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
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getLinhas()];
        for (int i = 0; i < tabuleiro.getColunas(); i++) {
            for (int j = 0; j < tabuleiro.getLinhas(); j++) {
                matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return matriz;
    }

    //Inicia a partida, colocando as peças
    private void setupInicial() {
        tabuleiro.adicionarPeca(new Torre(CorPeca.WHITE, tabuleiro), new Posicao(9, 1));
        tabuleiro.adicionarPeca(new Torre(CorPeca.WHITE, tabuleiro), new Posicao(2, 1));
        tabuleiro.adicionarPeca(new Rei(CorPeca.BLACK, tabuleiro), new Posicao(0, 4));
    }
}
