/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess_game.xadrez.pecas;

import com.mycompany.chess_game.boardgame.Tabuleiro;
import com.mycompany.chess_game.xadrez.CorPeca;
import com.mycompany.chess_game.xadrez.PecaXadrez;

/**
 *
 * @author Julio-Ramos
 */
public class Torre extends PecaXadrez {

    public Torre(CorPeca cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "T";
    }
}
