import java.util.ArrayList;

public class VigenereEncryptor {
    private Matrice matrix;
    private ArrayList<Vigenere> quadranti;

    public VigenereEncryptor(String verme) {
        matrix = new Matrice(verme);
        quadranti = new ArrayList<>();

        Vigenere quadrante_1 = new Vigenere(0, 12, 0, 12, matrix);
        quadranti.add(quadrante_1);

        Vigenere quadrante_2 = new Vigenere(0, 12, 12, 26, matrix);
        quadranti.add(quadrante_2);

        Vigenere quadrante_3 = new Vigenere(12, 26, 0, 12, matrix);
        quadranti.add(quadrante_3);

        Vigenere quadrante_4 = new Vigenere(12, 26, 12, 26, matrix);
        quadranti.add(quadrante_4);
    }

    public String encrypt(String plainText) {
        for (Vigenere v : quadranti) {
            Thread t = new Thread(v);
            t.start();
            try {
                t.join();
            } catch (InterruptedException ex) {
                System.err.println("Errore metodo join");
            }
        }

        return matrix.cifra(plainText.toUpperCase());
    }
}

/*
// Esempio di come usare il metodo nelle altre classi
import Cifratura.VigenereEncryptor

class Classe {
    public static void main(String[] args) {
        // Example usage
        VigenereEncryptor encryptor = new VigenereEncryptor("verme");
        String encryptedText = encryptor.encrypt("stringa_da_cifrare");
        System.out.println("Testo cifrato: " + encryptedText);
    }
}
 */