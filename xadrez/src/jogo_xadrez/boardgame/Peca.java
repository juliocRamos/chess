package jogo_xadrez.boardgame;

public abstract class Peca {

    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        posicao = null;
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public abstract boolean[][] movimentosPossiveis();

    public boolean movimentoPossivel(Posicao posicao) {
        return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean existemMovimentosPossiveis() {
        boolean[][] matrizMovimentosPossiveis
                = movimentosPossiveis();

        for (int i = 0; i < matrizMovimentosPossiveis.length; i++) {
            for (int j = 0; j < matrizMovimentosPossiveis.length; j++) {
                if (matrizMovimentosPossiveis[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
