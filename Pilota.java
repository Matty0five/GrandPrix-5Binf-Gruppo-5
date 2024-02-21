/**
 * Questa classe rappresenta un pilota coinvolto nella gara del Grand Prix.
 */
public class Pilota {

    private String nome;

    /**
     * Costruttore della classe Pilota.
     *
     * @param nome Il nome del pilota.
     */
    public Pilota(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il nome del pilota.
     *
     * @return Il nome del pilota.
     */
    public String getNome() {
        return nome;
    }
}
