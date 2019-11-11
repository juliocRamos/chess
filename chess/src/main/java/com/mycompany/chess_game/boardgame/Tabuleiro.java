package com.mycompany.chess_game.boardgame;

import com.mycompany.chess_game.boardgame.exceptions.TabuleiroException;

public class Tabuleiro {

    private int linhas;
    private int colunas;

    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1) {
            throw new TabuleiroException("Erro ao criar o tabuleiro!"
                    + "\nÉ preciso definir pelo menos uma linha e uma coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca peca(int linha, int coluna) {
        if (!posicaoExiste(linha, coluna)) {
            throw new TabuleiroException("Posicao não existe no tabuleiro!");
        }
        return pecas[linha][coluna];
    }

    public Peca peca(Posicao posicao) {
        if (!posicaoExiste(posicao)) {
            throw new TabuleiroException("Posicao não existe no tabuleiro!");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void adicionarPeca(Peca peca, Posicao posicao) {
        if (existeUmaPeca(posicao)) {
            throw new TabuleiroException("Já existe uma peça nesta posição" + posicao);
        }

        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }

    // Verifica se é uma posicao válida
    public boolean posicaoExiste(int linha, int coluna) {
        return (((linha >= 0) && (linha <= this.linhas))
                && (coluna >= 0 && (coluna < this.colunas)));
    }

    public boolean posicaoExiste(Posicao posicao) {
        return posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }

    // Verifica se existe uma peça na posicao
    public boolean existeUmaPeca(Posicao posicao) {
        if (!posicaoExiste(posicao)) {
            throw new TabuleiroException("Posicao não existe no tabuleiro!");
        }
        return peca(posicao) != null;
    }
}
