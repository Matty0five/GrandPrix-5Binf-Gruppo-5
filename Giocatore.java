/**
 * Questa classe rappresenta un giocatore coinvolto nella gara del Grand Prix.
 */
public class Giocatore {

    private String nome;
    private String password;

    /**
     * Costruttore della classe Giocatore.
     *
     * @param nome     Il nome del giocatore.
     * @param password La password del giocatore.
     */
    public Giocatore(String nome, String password) {
        this.nome = nome;
        this.password = password;
    }

    /**
     * Ottiene il nome del giocatore.
     *
     * @return Il nome del giocatore.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Ottiene la password del giocatore.
     *
     * @return La password del giocatore.
     */
    public String getPassword() {
        return password;
    }
}
