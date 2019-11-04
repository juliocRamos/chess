/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess_game.xadrez;

import com.mycompany.chess_game.boardgame.Tabuleiro;

/**
 *
 * @author Julio-Ramos
 */
public class PartidaXadrez {

    private Tabuleiro tab;

    public PartidaXadrez() {
        this.tab = new Tabuleiro(8, 8);
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] matriz = new PecaXadrez[tab.getLinhas()][tab.getLinhas()];
        for (int i = 0; i < tab.getColunas(); i++) {
            for (int j = 0; j < tab.getLinhas(); j++) {
                matriz[i][j] = (PecaXadrez) tab.peca(i, j);
            }
        }
        return matriz;
    }
}
