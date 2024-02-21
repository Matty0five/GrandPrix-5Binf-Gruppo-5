import java.util.Random;

/**
 * Questa classe rappresenta un giudice che prende decisioni durante una gara del Grand Prix.
 */
public class Giudice {

    long tempoSafetyCar;

    /**
     * Costruttore della classe Giudice.
     */
    public Giudice() {}

    /**
     * Decide se attivare la safety car. Restituisce true con una probabilità del 66.67% (2 su 3).
     *
     * @return True se la safety car deve essere attivata, altrimenti false.
     */
    public boolean safety_car() {
        Random random = new Random();
        return random.nextInt(3) > 0;
    }

    /**
     * Restituisce il tempo della safety car.
     *
     * @return Il tempo della safety car.
     */
    public long getTempoSafetyCar() {
        return tempoSafetyCar;
    }
}
