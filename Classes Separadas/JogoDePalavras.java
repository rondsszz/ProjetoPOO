public class JogoDePalavras {
    public static void main(String[] args) {
        Jogo jogoNormal = new Jogo(MusicasGraduation.getMúsicas(), ModoDeJogo.Modo.NORMAL);
        jogoNormal.iniciar();

        Jogo jogoDificil = new Jogo(MusicasGraduation.getMúsicas(), ModoDeJogo.Modo.DIFICIL);
        jogoDificil.iniciar();
    }
}
