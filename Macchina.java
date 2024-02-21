import java.util.*;
import java.lang.Math;

/**
 * Questa classe rappresenta una macchina coinvolta nella gara del Grand Prix.
 */
public class Macchina extends Thread {

    private String nome;
    private boolean truccata = false;
    private Pilota pilota;
    private boolean running;
    private double kmPercorsi;
    private boolean incidentata;
    private int giriCompletati;
    private int lunghezzaCircuito;
    private int giriDaCompletare;
    private double kmPercorribili;
    private final double VELOCITA_STANDARD = 10.0;
    private final double VELOCITA_TRUCCATA = 8.0;

    /**
     * Costruttore della classe Macchina.
     *
     * @param nome       Il nome della macchina.
     * @param truccata   True se la macchina è truccata, altrimenti false.
     * @param circuito   Il circuito su cui si svolge la gara.
     * @param giri       Il numero di giri previsti per la gara.
     */
    public Macchina(String nome, boolean truccata, Circuito circuito, int giri) {
        this.nome = nome;
        this.truccata = truccata;
        this.lunghezzaCircuito = circuito.getLunghezza();
        this.giriDaCompletare = giri;
    }

    /**
     * Esegue il thread della macchina, gestendo il percorso, gli incidenti e la fine della gara.
     */
    @Override
    public void run() {
        running = true;
        Random random = new Random();
        boolean presente_safetycar = false;
        int possibilita_incidente;
        int incidente_avvenuto;

        while (running && !incidentata) {
            
            presente_safetycar = kmPercorribili >= (lunghezzaCircuito / VELOCITA_STANDARD);

            if (presente_safetycar)
                kmPercorsi += Math.random() * kmPercorribili;
            else
                kmPercorsi += kmPercorribili;

            if (kmPercorsi >= lunghezzaCircuito) {
                giriCompletati += 1;
                kmPercorsi = 0;
            }

            if (presente_safetycar)
                possibilita_incidente = truccata ? 100 : 200;
            else
                possibilita_incidente = 1;

            incidente_avvenuto = random.nextInt(possibilita_incidente);
            if (incidente_avvenuto == 2) {
                incidentata = true;
                Gara.aggiungiIncidentata(nome, pilota.getNome());
            }

            if (giriCompletati == giriDaCompletare) {
                running = false;
                Gara.aggiungiFinita(nome, pilota.getNome());
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Arrotonda un numero a un numero specificato di cifre decimali.
     *
     * @param numero_da_arrotondare     Il numero da arrotondare.
     * @param numero_cifre_decimali     Il numero di cifre decimali.
     * @return Il numero arrotondato.
     */
    public double arrotonda(double numero_da_arrotondare, int numero_cifre_decimali) {

        double fattoreDiMoltiplicazione = Math.pow(10, numero_cifre_decimali);
        double numeroMoltiplicato = numero_da_arrotondare * fattoreDiMoltiplicazione;
        double numeroArrotondato = Math.round(numeroMoltiplicato);
        numeroArrotondato /= fattoreDiMoltiplicazione;

        return numeroArrotondato;
    }

    /**
     * Restituisce una rappresentazione in stringa della macchina.
     *
     * @return Una stringa rappresentante lo stato attuale della macchina.
     */
    @Override
    public String toString() {

        if (incidentata) {
            return "La macchina '" + nome + "', con pilota '" + pilota.getNome() +
                    "' ha fatto un incidente! Ha percorso " + arrotonda(kmPercorsi, 2) + "/" +
                    lunghezzaCircuito + "km ed era al " + (giriCompletati + 1) + "° giro!";
        } else if ((giriCompletati == giriDaCompletare) && !incidentata) {
            return "La macchina '" + nome + "', con pilota '" + pilota.getNome() +
                    "' ha terminato la gara!";
        } else {
            return "La macchina '" + nome + "', con pilota '" + pilota.getNome() +
                    "' ha percorso " + arrotonda(kmPercorsi, 2) + "/" + lunghezzaCircuito +
                    "km ed è al giro " + (giriCompletati + 1) + "/" + giriDaCompletare;
        }
    }

    /**
     * Imposta la velocità percorribile della macchina.
     *
     * @param scelta True se la velocità deve essere ripristinata a quella normale, false se deve essere impostata
     *               alla velocità stabilita dalla safety car.
     */
    public void setKmPercorribili(boolean scelta) {
        if (scelta) {
            if (truccata)
                kmPercorribili = lunghezzaCircuito / VELOCITA_STANDARD;
            else
                kmPercorribili = lunghezzaCircuito / VELOCITA_TRUCCATA;
        } else {
            kmPercorribili = lunghezzaCircuito / 15.0;
        }
    }

    /**
     * Restituisce la distanza percorsa dalla macchina.
     *
     * @return La distanza percorsa in chilometri.
     */
    public double getKmPercorsi() {
        return kmPercorsi;
    }

    /**
     * Restituisce lo stato di incidentata della macchina.
     *
     * @return True se la macchina è incidentata, altrimenti false.
     */
    public boolean getIncidentata() {
        return incidentata;
    }

    /**
     * Restituisce il nome della macchina.
     *
     * @return Il nome della macchina.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il pilota associato alla macchina.
     *
     * @param pilota Il pilota da associare alla macchina.
     */
    public void setPilota(Pilota pilota) {
        this.pilota = pilota;
    }

    /**
     * Restituisce il pilota associato alla macchina.
     *
     * @return Il pilota associato alla macchina.
     */
    public Pilota getPilota() {
        return pilota;
    }

    /**
     * Restituisce lo stato di esecuzione della macchina.
     *
     * @return True se la macchina è in esecuzione, altrimenti false.
     */
    public boolean getRunning() {
        return running;
    }
}
