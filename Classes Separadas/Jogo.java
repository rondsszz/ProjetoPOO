import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Palavra palavraSelecionada;
    private int tentativasRestantes;
    private Scanner scanner;
    private ModoDeJogo.Modo modo;

    public Jogo(String[] palavras, ModoDeJogo.Modo modo) {
        Random random = new Random();
        this.palavraSelecionada = new Palavra(palavras[random.nextInt(palavras.length)]);
        this.modo = modo;
        this.tentativasRestantes = (modo == ModoDeJogo.Modo.NORMAL) ? ModoDeJogo.getTentativasRestantes(ModoDeJogo.Modo.NORMAL) :
                ModoDeJogo.getTentativasRestantes(ModoDeJogo.Modo.DIFICIL);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("\nBem-vindo ao jogo de palavras no modo " + modo.toString() + "!");
        System.out.println("Tente adivinhar o nome de uma música do álbum 'Graduation' de Kanye West.");

        while (tentativasRestantes > 0 && !palavraSelecionada.foiAdivinhada()) {
            System.out.println("\nPalavra: " + palavraSelecionada.mostrarEstado());
            System.out.println("Tentativas restantes: " + tentativasRestantes);
            System.out.print("Digite uma letra ou arrisque a palavra inteira (digite 'arriscar'): ");
            String entrada = scanner.next().toLowerCase();

            if (entrada.equals("arriscar")) {
                tentativasRestantes = arriscarPalavra();
            } else if (entrada.length() == 1) {
                processarTentativa(entrada.charAt(0));
            }
        }

        exibirResultadoFinal();
    }

    private int arriscarPalavra() {
        System.out.print("Digite o nome da música: ");
        String palavraArriscada = scanner.next().toLowerCase();
        if (palavraArriscada.equals(palavraSelecionada.getPalavra())) {
            palavraSelecionada.adivinhar();
        } else {
            tentativasRestantes = 0;
        }
        return tentativasRestantes;
    }

    private void processarTentativa(char letra) {
        if (!palavraSelecionada.revelarLetra(letra)) {
            tentativasRestantes--;
            System.out.println("Letra incorreta. Tente novamente.");
        }
    }

    private void exibirResultadoFinal() {
        if (palavraSelecionada.foiAdivinhada()) {
            String palavraFormatada = formatarPalavra(palavraSelecionada.getPalavra());
            System.out.println("\nParabéns! Você adivinhou o nome da música: " + palavraFormatada);
        } else {
            System.out.println("\nVocê perdeu! O nome da música era: " + palavraSelecionada.getPalavra());
        }
    }

    private String formatarPalavra(String palavra) {
        String[] palavras = palavra.split("(?=[A-Z])");
        StringBuilder sb = new StringBuilder();
        for (String p : palavras) {
            sb.append(p).append(" ");
        }
        return sb.toString().trim();
    }
}
