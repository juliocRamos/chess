package jogo_xadrez.xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    private List<Peca> pecasNoTabuleiro = new ArrayList<>();

    private List<Peca> pecasCapturadas = new ArrayList<>();

    private boolean inCheck;

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
        pecasNoTabuleiro.add(peca);
    }

    public PecaXadrez moverPeca(PosicaoXadrez posicaoOrigem,
            PosicaoXadrez posicaoDestino) {
        Posicao origem = posicaoOrigem.toPosicao();
        Posicao destino = posicaoDestino.toPosicao();

        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        Peca pecaCapturada = fazerMovimento(origem, destino);

        if (isReiEmCheck(jogadorAtual)) {
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Você não pode se colocar em Check!");
        }

        inCheck = (isReiEmCheck(getCorOponente(jogadorAtual)));
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

        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        tabuleiro.adicionarPeca(p, destino);
        return (PecaXadrez) pecaCapturada;
    }

    private void desfazerMovimento(Posicao origem, Posicao destino,
            Peca possivelPecaCapturada) {
        Peca p = tabuleiro.removerPeca(destino);
        tabuleiro.adicionarPeca(p, origem);

        if (possivelPecaCapturada != null) {
            tabuleiro.adicionarPeca(p, destino);
            pecasCapturadas.remove(p);
            pecasNoTabuleiro.add(p);
        }
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

    // Apenas retorna a cor das peças do oponente
    private CorPeca getCorOponente(CorPeca cor) {
        return (cor == CorPeca.WHITE) ? CorPeca.BLACK : CorPeca.WHITE;
    }

    //Retorna o rei dada a cor passada como argumento
    private PecaXadrez getRei(CorPeca cor) {
        List<Peca> listaPecas = this.pecasNoTabuleiro.stream()
                .filter(x -> ((PecaXadrez) x).getCor() == cor)
                .collect(Collectors.toList());

        for (Peca p : listaPecas) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Não existe o rei da cor: "
                + cor + " no tabuleiro!");
    }

    /*
     Percorro todas as peças adversárias testando se algum dos movimentos 
     possíveis ameaça o Rei do oponente.
     */
    public boolean isReiEmCheck(CorPeca cor) {
        Posicao posicaoRei = getRei(cor).getPosicaoXadrez().toPosicao();

        List<Peca> pecasInimigas = this.pecasNoTabuleiro.stream()
                .filter(x -> ((PecaXadrez) x).getCor() == getCorOponente(cor))
                .collect(Collectors.toList());

        for (Peca p : pecasInimigas) {
            boolean[][] mat = p.movimentosPossiveis();

            if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    public boolean isInCheck() {
        return inCheck;
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
