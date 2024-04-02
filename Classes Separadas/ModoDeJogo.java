public class ModoDeJogo {
    public enum Modo {
        NORMAL,
        DIFICIL
    }

    public static int getTentativasRestantes(Modo modo) {
        switch (modo) {
            case NORMAL:
                return 5;
            case DIFICIL:
                return 3;
            default:
                throw new IllegalArgumentException("Modo de jogo inválido.");
        }
    }
}
