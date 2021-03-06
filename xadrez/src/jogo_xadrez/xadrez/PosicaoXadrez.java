package jogo_xadrez.xadrez;

import jogo_xadrez.boardgame.Posicao;
import jogo_xadrez.xadrez.exceptions.XadrezException;

public class PosicaoXadrez {

    private char coluna;

    private int linha;

    public PosicaoXadrez(char coluna, int linha) {
        if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
            throw new XadrezException("Erro ao instanciar PosicaoXadrez."
                    + "Valores válidos vão de a1 até h8");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }
    
    /**
     * Formula da coluna = 'a' (unicode) + coluna_xadrez
     *     Ex: 'a' + 'a' = 0
     *         'b' + 'a' = 1
     *         ...
     * Formula da linha = 8 - linha_xadrez
     *
     * @return
     */
    protected Posicao toPosicao(){
        return new Posicao(8 - this.linha, this.coluna - 'a');
    }
    
    protected static PosicaoXadrez fromPosicao(Posicao posicao){
     return new PosicaoXadrez((char)('a' + posicao.getColuna()),
        8 - posicao.getLinha());
    }

    @Override
    public String toString() {
        return "" + this.coluna + this.linha;
    }
}
