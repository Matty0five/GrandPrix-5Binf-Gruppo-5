/**
 * Questa classe rappresenta un circuito coinvolto in una gara del Grand Prix.
 */


public class Circuito {

    /**
     * Nome del circuito.
     */
    private String nome;

    /**
     * Lunghezza del circuito in chilometri.
     */
    private int lunghezza;

    /**
     * Costruttore della classe Circuito.
     *
     * @param nome     Il nome del circuito.
     * @param lunghezza La lunghezza del circuito in chilometri.
     */
    public Circuito(String nome, int lunghezza) {
        this.nome = nome;
        this.lunghezza = lunghezza;
    }

    /**
     * Imposta la lunghezza del circuito.
     *
     * @param lunghezza La nuova lunghezza del circuito in chilometri.
     */
    public void setLunghezza(int lunghezza) {
        this.lunghezza = lunghezza;
    }

    /**
     * Ottiene la lunghezza del circuito.
     *
     * @return La lunghezza del circuito in chilometri.
     */
    public int getLunghezza() {
        return lunghezza;
    }

    /**
     * Ottiene il nome del circuito.
     *
     * @return Il nome del circuito.
     */
    public String getNome() {
        return nome;
    }
}
