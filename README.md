# ProjetoPOO
Projeto da cadeira de Programação Orientada a Objetos

Aluno: Rondon Guilherme de Vasconcelos Meira

Este programa é um jogo de palavras em Java utilizando meus conhecimentos de Programação Orientada a Objetos para criar um jogo divertido e lúdico com a temática do rapper americano Kanye West


# Requisitos mínimos feitos:

- O programa escolhe aleatoriamente uma palavra de uma lista pré definida, neste caso com as músicas do álbum Graduation
- O programa permite ao jogador inserir letras para tentar advinhar a palavra
- O programa controla o número de tentativas que o jogador pode usar para tentar acertar a palavra

# Requisitos adicionais feitos:
- O programa permite ao usuário arriscar acertar a palavra inteira como maneira de adiantar o jogo em cenários em que o usuário já sabe a palavra antes de colocar todas as letras
- O programa conta com modos de dificuldade como maneira de deixar o jogo de palavras mais desafiador e lúdico
- O programa é dividido em classes, para facilitar a leitura do código


# Resumo de cada classe:

## JogoDePalavras.java:
- Esta classe contém o método main e é a classe principal do jogo. Ela instancia e inicia o jogo em dois modos diferentes: normal e difícil.

## Jogo.java:
Esta classe representa o jogo em si. Ela gerencia a seleção aleatória de uma palavra a ser adivinhada, a interação com o jogador e o controle do estado do jogo.

## Palavra.java:
Esta classe representa uma palavra a ser adivinhada no jogo. Ela mantém o estado da palavra (letras adivinhadas e não adivinhadas) e fornece métodos para revelar letras e verificar se a palavra foi adivinhada.

## MusicasGraduation.java:
Esta classe fornece uma lista de músicas do álbum "Graduation" de Kanye West, que são usadas como palavras a serem adivinhadas no jogo.

## ModoDeJogo.java:
Esta classe define os modos de jogo disponíveis (normal e difícil) e fornece um método para obter o número de tentativas restantes com base no modo selecionado.
