import java.util.Random;
import java.util.Scanner;
 
public class JogoDePalavras {
 
    public static void main(String[] args) {
        // Criação do jogo com as músicas do álbum "Graduation" de Kanye West, no modo normal
        Jogo jogoNormal = new Jogo(MusicasGraduation.getMúsicas(), ModoDeJogo.Modo.NORMAL);
        // Inicia o jogo no modo normal
        jogoNormal.iniciar();
 
        // Criação do jogo com as músicas do álbum "Graduation" de Kanye West, no modo difícil
        Jogo jogoDificil = new Jogo(MusicasGraduation.getMúsicas(), ModoDeJogo.Modo.DIFICIL);
        // Inicia o jogo no modo difícil
        jogoDificil.iniciar();
    }
}
 
// Classe que representa o jogo
class Jogo {
    private Palavra palavraSelecionada; // Palavra selecionada para o jogo
    private int tentativasRestantes; // Número de tentativas restantes
    private Scanner scanner; // Scanner para entrada do usuário
    private ModoDeJogo.Modo modo; // Modo de jogo escolhido
 
    // Construtor do jogo
    public Jogo(String[] palavras, ModoDeJogo.Modo modo) {
        Random random = new Random();
        // Seleciona uma palavra aleatória da lista de palavras fornecidas
        this.palavraSelecionada = new Palavra(palavras[random.nextInt(palavras.length)]);
        this.modo = modo; // Define o modo de jogo escolhido
        // Ajusta o número de tentativas com base no modo escolhido
        this.tentativasRestantes = (modo == ModoDeJogo.Modo.NORMAL) ? ModoDeJogo.getTentativasRestantes(ModoDeJogo.Modo.NORMAL) :
                ModoDeJogo.getTentativasRestantes(ModoDeJogo.Modo.DIFICIL);
        this.scanner = new Scanner(System.in); // Inicializa o scanner
    }
 
    // Método para iniciar o jogo
    public void iniciar() {
        System.out.println("\nBem-vindo ao jogo de palavras no modo " + modo.toString() + "!");
        System.out.println("Tente adivinhar o nome de uma musica do album 'Graduation' de Kanye West.");
 
        // Enquanto houver tentativas restantes e a palavra não foi adivinhada
        while (tentativasRestantes > 0 && !palavraSelecionada.foiAdivinhada()) {
            System.out.println("\nPalavra: " + palavraSelecionada.mostrarEstado()); // Mostra o estado atual da palavra
            System.out.println("Tentativas restantes: " + tentativasRestantes); // Mostra o número de tentativas restantes
            System.out.print("Digite uma letra ou arrisque a palavra inteira (digite 'arriscar'): ");
            String entrada = scanner.next().toLowerCase(); // Lê a entrada do usuário
 
            if (entrada.equals("arriscar")) {
                tentativasRestantes = arriscarPalavra(); // Arrisca a palavra inteira
            } else if (entrada.length() == 1) {
                processarTentativa(entrada.charAt(0)); // Processa a tentativa de letra
            }
        }
 
        exibirResultadoFinal(); // Exibe o resultado final do jogo
    }
 
    // Método para arriscar a palavra inteira
    private int arriscarPalavra() {
        System.out.print("Digite o nome da música: ");
        String palavraArriscada = scanner.next().toLowerCase(); // Lê a palavra arriscada pelo usuário
        if (palavraArriscada.equals(palavraSelecionada.getPalavra())) { // Verifica se a palavra está correta
            palavraSelecionada.adivinhar(); // Marca a palavra como adivinhada
        } else {
            tentativasRestantes = 0; // Define o número de tentativas restantes como zero (usuário errou)
        }
        return tentativasRestantes;
    }
 
    // Método para processar uma tentativa de letra
    private void processarTentativa(char letra) {
        if (!palavraSelecionada.revelarLetra(letra)) { // Se a letra não estiver na palavra
            tentativasRestantes--; // Decrementa o número de tentativas restantes
            System.out.println("Letra incorreta. Tente novamente.");
        }
    }
 
    // Método para exibir o resultado final do jogo
    private void exibirResultadoFinal() {
        if (palavraSelecionada.foiAdivinhada()) { // Se a palavra foi adivinhada
            String palavraFormatada = formatarPalavra(palavraSelecionada.getPalavra()); // Formata a palavra para exibição
            System.out.println("\nParabéns! Você adivinhou o nome da música: " + palavraFormatada);
        } else {
            System.out.println("\nVocê perdeu! O nome da música era: " + palavraSelecionada.getPalavra()); // Senão, exibe a palavra correta
        }
    }
 
    // Método para formatar a palavra (separar as palavras)
    private String formatarPalavra(String palavra) {
        String[] palavras = palavra.split("(?=[A-Z])"); // Divide a palavra onde há uma transição de maiúscula para minúscula
        StringBuilder sb = new StringBuilder();
        for (String p : palavras) {
            sb.append(p).append(" "); // Adiciona um espaço entre as palavras
        }
        return sb.toString().trim(); // Retorna a palavra formatada
    }
}
 
// Classe que representa uma palavra do jogo
class Palavra {
    private String palavra; // A palavra a ser adivinhada
    private boolean[] letrasAdivinhadas; // Array que indica quais letras foram adivinhadas
 
    // Construtor da palavra
    public Palavra(String palavra) {
        this.palavra = palavra.toLowerCase(); // Converte a palavra para minúsculas
        this.letrasAdivinhadas = new boolean[palavra.length()]; // Inicializa o array de letras adivinhadas
    }
 
    // Método para verificar se a palavra foi adivinhada completamente
    public boolean foiAdivinhada() {
        for (boolean letraAdivinhada : letrasAdivinhadas) {
            if (!letraAdivinhada) {
                return false;
            }
        }
        return true;
    }
 
    // Método para mostrar o estado atual da palavra (com as letras adivinhadas)
    public String mostrarEstado() {
        StringBuilder estado = new StringBuilder();
        for (int i = 0; i < palavra.length(); i++) {
            if (letrasAdivinhadas[i]) {
                estado.append(palavra.charAt(i)).append(" "); // Adiciona a letra se ela foi adivinhada
            } else {
                estado.append("_ "); // Adiciona um espaço se a letra não foi adivinhada
            }
        }
        return estado.toString().trim(); // Retorna o estado atual da palavra
    }
 
    // Método para revelar uma letra na palavra
    public boolean revelarLetra(char letra) {
        boolean letraEncontrada = false;
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == letra) { // Se a letra for encontrada na palavra
                letrasAdivinhadas[i] = true; // Marca a letra como adivinhada
                letraEncontrada = true; // Define que a letra foi encontrada
            }
        }
        return letraEncontrada; // Retorna se a letra foi encontrada na palavra
    }
 
    // Método para marcar a palavra como adivinhada
    public void adivinhar() {
        for (int i = 0; i < palavra.length(); i++) {
            letrasAdivinhadas[i] = true; // Marca todas as letras como adivinhadas
        }
    }
 
    // Método para obter a palavra
    public String getPalavra() {
        return palavra;
    }
}
 
// Classe que fornece as músicas do álbum "Graduation" de Kanye West
class MusicasGraduation {
    private static final String[] MÚSICAS = {"GoodMorning", "Champion", "Stronger", "IWonder", "GoodLife", "CantTellMeNothing", "BarryBonds", "DrunkandHotGirls", "FlashingLights", "EverythingIAm", "TheGlory", "Homecoming", "BigBrother"};
 
    // Método para obter as músicas
    public static String[] getMúsicas() {
        return MÚSICAS;
    }
}
 
// Classe que representa os modos de jogo
class ModoDeJogo {
    public enum Modo {
        NORMAL,
        DIFICIL
    }
 
    // Método para obter o número de tentativas restantes com base no modo de jogo
    public static int getTentativasRestantes(Modo modo) {
        switch (modo) {
            case NORMAL:
                return 5; // Modo normal: 5 tentativas
            case DIFICIL:
                return 3; // Modo difícil: 3 tentativas
            default:
                throw new IllegalArgumentException("Modo de jogo inválido.");
        }
    }
}