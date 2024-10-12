# Campo Minado

O projeto consiste em um jogo bastante conhecido: o Campo Minado. O objetivo é desenvolver este jogo para explorar a lógica de programação e implementar testes unitários utilizando JUnit 5.

<div align="center">
    <h3 align="center">Tecnologias utilizada</h3>
        <img width="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/>
        <img width="50" src="https://user-images.githubusercontent.com/25181517/117533873-484d4480-afef-11eb-9fad-67c8605e3592.png" alt="JUnit" title="JUnit"/>
</div>

<br>

<h2 align="center">Classe Campo</h2>

<br>

A classe "Campo" representa o campo no jogo. Cada campo possui coordenadas (x e y), e pode estar em um dos seguintes estados: aberto, minado (contendo uma mina), ou marcado (indicando que o jogador suspeita que há uma mina).

### Atributos:

- X e Y: Coordenadas do campo na grade do jogo.
- Aberto: Indica se o campo foi aberto pelo jogador.
- Minado: Indica se o campo contém uma mina.
- Marcado: indica se o campo foi marcado pelo jogador.
- Vizinhos: Indica todos os campos vizinhos.

### Métodos:
- AdicionarVizinhos(Campo vizinho): Utilizado para adicionar um campo vizinho a lista.
- AlterarMarcacao(): Alterna o estado da marcação no campo.
- Abrir(): Abre o campo, se o campo estiver minado será disparado uma exception.
- VizinhancaSegura(): Verifica se não há minas nos campos vizinhos.
- MinarCampo(): Marca o campo com uma mina.
- IsAberto(), IsFechado(), isMarcado(), isMinado(): Estes métodos são usados para verificar o estado dos campos.
- ObjetivoAlcancado(): Avalia se o objetivo do jogador foi alcançado (campo desvendado ou protegido com a marcação).
- MinaNaVizinhanca(): Conta o número de minas nos campos vizinhos.
- ReiniciarJogo(): Reinicia o estado do campo para uma nova partida.
- ToString(): Sobrescrevemos o método toString() para exibir a representação do campo com base em seu estado atual.

<br>

### Testes Unitarios:
Seguindo o caminho src/test/java/com/github/ki3lmigu3l/model, você encontrará a classe CampoTest, que contém os testes para os métodos implementados na classe Campo.

#### Exemplo:
```java
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
    void testeReiniciarJogo () {
        campo.reiniciarJogo();
        Assertions.assertFalse(campo.isAberto() && campo.isMarcado() && campo.isMinado());
    }
```
