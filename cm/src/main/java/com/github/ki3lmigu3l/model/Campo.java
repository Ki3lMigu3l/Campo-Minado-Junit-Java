package com.github.ki3lmigu3l.model;

import com.github.ki3lmigu3l.exception.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    private final int x;
    private final int y;
    private boolean aberto;
    private boolean minado;
    private boolean marcado;

    private List<Campo> vizinhos = new ArrayList<>();

    Campo(int linha, int coluna) {
        this.x = linha;
        this.y = coluna;
    }

    public boolean adicionarVizinhos(Campo vizinho) {
        boolean linhaDiferente = this.x != vizinho.x;
        boolean colunaDiferente = this.y != vizinho.y;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(x - vizinho.x);
        int deltaColuna = Math.abs(y - vizinho.y);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        }

        return false;
    }

    public void alternarMarcacao () {
        if (!aberto) {
            marcado = !marcado;
        }
    }

    public boolean abrir () {

        if (!aberto && !marcado) {
            aberto = true;

             if (minado) {
                throw new ExplosaoException();
             }

             if (vizinhancaSegura()) {
                 vizinhos.forEach(v -> v.abrir());
             }

             return true;
        }

        return false;
    }

    public boolean vizinhancaSegura () {
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public void minarCampo () {
            minado = true;
    }

    public boolean isAberto () {
        return aberto;
    }

    public boolean isFechado () {
        return !isAberto();
    }

    public boolean isMarcado() {
        return marcado;
    }

    public boolean isMinado() {
        return minado;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean objetivoAlcancado () {
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    public long minaNaVizinhanca () {
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciarJogo () {
        aberto = false;
        minado = false;
        marcado = false;
    }

    @Override
    public String toString() {
        if (marcado) {
            return "x";
        } else if (aberto && minado) {
            return "*";
        } else if (aberto && minaNaVizinhanca() > 0) {
            return Long.toString(minaNaVizinhanca());
        } else if (aberto) {
            return " ";
        } else {
            return "?";
        }
    }
}
