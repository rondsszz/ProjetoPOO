public class Palavra {
    private String palavra;
    private boolean[] letrasAdivinhadas;

    public Palavra(String palavra) {
        this.palavra = palavra.toLowerCase();
        this.letrasAdivinhadas = new boolean[palavra.length()];
    }

    public boolean foiAdivinhada() {
        for (boolean letraAdivinhada : letrasAdivinhadas) {
            if (!letraAdivinhada) {
                return false;
            }
        }
        return true;
    }

    public String mostrarEstado() {
        StringBuilder estado = new StringBuilder();
        for (int i = 0; i < palavra.length(); i++) {
            if (letrasAdivinhadas[i]) {
                estado.append(palavra.charAt(i)).append(" ");
            } else {
                estado.append("_ ");
            }
        }
        return estado.toString().trim();
    }

    public boolean revelarLetra(char letra) {
        boolean letraEncontrada = false;
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == letra) {
                letrasAdivinhadas[i] = true;
                letraEncontrada = true;
            }
        }
        return letraEncontrada;
    }

    public void adivinhar() {
        for (int i = 0; i < palavra.length(); i++) {
            letrasAdivinhadas[i] = true;
        }
    }

    public String getPalavra() {
        return palavra;
    }
}
