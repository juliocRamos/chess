package jogo_xadrez.xadrez;

import jogo_xadrez.boardgame.Peca;
import jogo_xadrez.boardgame.Posicao;
import jogo_xadrez.boardgame.Tabuleiro;
import jogo_xadrez.xadrez.exceptions.XadrezException;
import jogo_xadrez.xadrez.pecas.Bispo;
import jogo_xadrez.xadrez.pecas.Cavalo;
import jogo_xadrez.xadrez.pecas.Peao;
import jogo_xadrez.xadrez.pecas.Rainha;
import jogo_xadrez.xadrez.pecas.Rei;
import jogo_xadrez.xadrez.pecas.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    private int turno;

    private CorPeca jogadorAtual;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        jogadorAtual = CorPeca.WHITE;
        turno = 1;
        setupInicial();
    }

    public int getTurno() {
        return turno;
    }

    public CorPeca getJogadorAtual() {
        return jogadorAtual;
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
        proximoTurno();
        return (PecaXadrez) pecaCapturada;
    }

    private void validarPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.existeUmaPeca(posicao)) {
            throw new XadrezException("Não existe uma peça na posição informada");
        } else if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao))
                .getCor()) {
            throw new XadrezException("A peça escolhida não é sua!");

        } else if (!this.tabuleiro.peca(posicao).existemMovimentosPossiveis()) {
            throw new XadrezException("Não existem movimentos possíveis "
                    + "para a peça escolhida.");
        }
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
            throw new XadrezException("A peça escolhida não pode se "
                    + "mover para a posição de destino");
        }
    }

    public Peca fazerMovimento(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
        p.incrementearContadorMovimentos();
        Peca pecaCapturada = tabuleiro.removerPeca(destino);

        tabuleiro.adicionarPeca(p, destino);
        return (PecaXadrez) pecaCapturada;
    }

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
        Posicao posicao = posicaoOrigem.toPosicao();
        validarPosicaoOrigem(posicao);

        return tabuleiro.peca(posicao).movimentosPossiveis();
    }

    private void proximoTurno() {
        turno++;
        jogadorAtual = (jogadorAtual == CorPeca.WHITE)
                ? CorPeca.BLACK
                : CorPeca.WHITE;
    }

    //Inicia a partida, colocando as peças
    private void setupInicial() {
        adicionarNovaPeca('a', 1, new Torre(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('e', 1, new Rei(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('d', 1, new Rainha(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('h', 1, new Torre(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('c', 1, new Bispo(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('f', 1, new Bispo(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('b', 1, new Cavalo(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('g', 1, new Cavalo(CorPeca.WHITE, tabuleiro));

        adicionarNovaPeca('a', 2, new Peao(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('b', 2, new Peao(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('c', 2, new Peao(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('d', 2, new Peao(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('e', 2, new Peao(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('f', 2, new Peao(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('g', 2, new Peao(CorPeca.WHITE, tabuleiro));
        adicionarNovaPeca('h', 2, new Peao(CorPeca.WHITE, tabuleiro));

        adicionarNovaPeca('a', 8, new Torre(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('e', 8, new Rei(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('d', 8, new Rainha(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('h', 8, new Torre(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('c', 8, new Bispo(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('f', 8, new Bispo(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('b', 8, new Cavalo(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('g', 8, new Cavalo(CorPeca.BLACK, tabuleiro));

        adicionarNovaPeca('a', 7, new Peao(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('b', 7, new Peao(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('c', 7, new Peao(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('d', 7, new Peao(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('e', 7, new Peao(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('f', 7, new Peao(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('g', 7, new Peao(CorPeca.BLACK, tabuleiro));
        adicionarNovaPeca('h', 7, new Peao(CorPeca.BLACK, tabuleiro));

    }
}
