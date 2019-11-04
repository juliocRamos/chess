/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess_game.UI;

import com.mycompany.chess_game.xadrez.PecaXadrez;

/**
 *
 * @author Julio-Ramos
 */
public class UserInterface {
    
    public static void printBoard(PecaXadrez[][] pecas){
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                printPiece(pecas[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    
    private static void printPiece(PecaXadrez peca){
        if (peca == null) {
            System.out.print("-");
        } else {
            System.out.print(peca);
        }
        System.out.print(" ");
    }
}
