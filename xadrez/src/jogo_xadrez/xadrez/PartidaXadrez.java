package jogo_xadrez.xadrez;

import jogo_xadrez.boardgame.Peca;
import jogo_xadrez.boardgame.Posicao;
import jogo_xadrez.boardgame.Tabuleiro;
import jogo_xadrez.xadrez.exceptions.XadrezException;
import jogo_xadrez.xadrez.pecas.Rei;
import jogo_xadrez.xadrez.pecas.Torre;

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
                new PosicaoXadrez(coluna, linha).toPosicao());
    }

    public PecaXadrez moverPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
        Posicao origem = posicaoOrigem.toPosicao();
        Posicao destino = posicaoDestino.toPosicao();

        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        Peca pecaCapturada = fazerMovimento(origem, destino);
        return (PecaXadrez) pecaCapturada;
    }

    private void validarPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.existeUmaPeca(posicao)) {
            throw new XadrezException("Não existe uma peça na posição informada");
        } else if (!this.tabuleiro.peca(posicao).existemMovimentosPossiveis()) {
            throw new XadrezException("Não existem movimentos possíveis "
                    + "para a peça escolhida.");
        }
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
            throw new XadrezException("A peça escolhida não pode se mover para a posição de destino");
        }
    }

    public Peca fazerMovimento(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removerPeca(origem);
        Peca pecaCapturada = tabuleiro.removerPeca(destino);

        tabuleiro.adicionarPeca(p, destino);
        return (PecaXadrez) pecaCapturada;
    }

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
        Posicao posicao = posicaoOrigem.toPosicao();
        validarPosicaoOrigem(posicao);

        return tabuleiro.peca(posicao).movimentosPossiveis();
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
