package com.github.ki3lmigu3l.model;

import com.github.ki3lmigu3l.exception.ExplosaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTest {

    // private Campo campo = new Campo(3, 3);

    private Campo campo;

    @BeforeEach
    void iniciarCampo () {
        campo = new Campo(3, 3);
    }

    @Test
    void testaAdicionarVizinhoDistanciaUmEsquerda () {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinhos(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testaAdicionarVizinhoDistanciaUmDireita () {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adicionarVizinhos(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testaAdicionarVizinhoDistanciaUmCima () {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinhos(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testaAdicionarVizinhoDistanciaUmBaixo () {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinhos(vizinho);
        Assertions.assertTrue(resultado);
    }


    @Test
    void testaAdicionarVizinhoDistanciaDoisDiagonalAbaixo () {
        Campo vizinho = new Campo(2,2);
        boolean resultado = campo.adicionarVizinhos(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeAdicionarNaoVizinho () {
        Campo vizinho = new Campo(1,1);
        boolean resultado = campo.adicionarVizinhos(vizinho);
        Assertions.assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado () {
        Assertions.assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao () {
        campo.alternarMarcacao();
        Assertions.assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas () {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        Assertions.assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirCampoNaoMinadoNaoMarcado () {
        Assertions.assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirCampoNaoMinadoMarcado () {
        campo.alternarMarcacao();
        Assertions.assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirCampoMinadoMarcado () {
        campo.alternarMarcacao();
        campo.minarCampo();
        Assertions.assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirCampoMinadoNaoMarcado () {
        campo.minarCampo();
        Assertions.assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirVizinhancaUm () {
        Campo vizinhoUm = new Campo(2,2);
        Campo vizinhoDoVizinhoUm = new Campo(1,1);

        vizinhoUm.adicionarVizinhos(vizinhoDoVizinhoUm);
        campo.adicionarVizinhos(vizinhoUm);
        campo.abrir();

        Assertions.assertTrue(vizinhoUm.isAberto() && vizinhoDoVizinhoUm.isAberto());
    }

    @Test
    void testeAbrirVizinhancaDois() {
        Campo vizinhoUm = new Campo(2,2);
        Campo vizinhoMinado = new Campo(1, 2);
        Campo vizinhoDoVizinhoUm = new Campo(1,1);

        vizinhoMinado.minarCampo();

        vizinhoUm.adicionarVizinhos(vizinhoDoVizinhoUm);
        vizinhoUm.adicionarVizinhos(vizinhoMinado);
        campo.adicionarVizinhos(vizinhoUm);
        campo.abrir();

        Assertions.assertTrue(vizinhoUm.isAberto() && vizinhoDoVizinhoUm.isFechado());
    }

    @Test
    void testeReiniciarJogo () {
        campo.reiniciarJogo();
        Assertions.assertFalse(campo.isAberto() && campo.isMarcado() && campo.isMinado());
    }
}
