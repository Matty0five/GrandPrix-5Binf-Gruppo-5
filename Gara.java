import java.util.ArrayList;

/**
 * Questa classe rappresenta una gara del Grand Prix con piloti, macchine e un circuito specifico.
 */
public class Gara {

    private Pilota[] piloti;
    private Macchina[] macchine;
    private static ArrayList<String> macchine_arrivate;
    private static ArrayList<String> macchine_incidentate;
    private Circuito circuito;
    private int numero_giri;
    private Giocatore giocatore;
    private Giudice giudice;

    /**
     * Costruttore della classe Gara.
     *
     * @param piloti      Array di piloti partecipanti alla gara.
     * @param macchine    Array di macchine partecipanti alla gara.
     * @param circuito    Il circuito su cui si svolge la gara.
     * @param numero_giri Il numero di giri previsti per la gara.
     * @param giocatore    Il giocatore partecipante alla gara.
     */
    public Gara(Pilota[] piloti, Macchina[] macchine, Circuito circuito, int numero_giri, Giocatore giocatore) {
        this.piloti = piloti;
        this.macchine = macchine;
        this.circuito = circuito;
        this.numero_giri = numero_giri;
        this.giocatore = giocatore;
        giudice = new Giudice();
    }

    /**
     * Metodo per cancellare il contenuto della console, utile per rendere più leggibili gli aggiornamenti della gara.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Avvia la gara e gestisce gli eventi, inclusi gli incidenti e l'intervento della safety car.
     */
    public void avvia() {
        macchine_arrivate = new ArrayList<String>();
        macchine_incidentate = new ArrayList<String>();

        System.out.println("Pronti...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("Attenti...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("Via!");
        System.out.println("Che il Grand Prix di " + circuito.getNome() + " abbia inizio!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        clearScreen();

        for (int i = 0; i < macchine.length; i++) {
            macchine[i].start();
        }

        int n_incidentate_prima = 0;
        int n_incidentate_dopo = 0;
        long tempo = System.currentTimeMillis() + 500000;

        for (int i = 0; i < macchine.length; i++) {
            macchine[i].setKmPercorribili(true);
        }

        while ((macchine_arrivate.size() + macchine_incidentate.size()) != macchine.length) {
            n_incidentate_dopo = macchine_incidentate.size();

            if (n_incidentate_dopo > n_incidentate_prima) {
                System.out.println("Una macchina ha fatto un incidente!");
                if (giudice.safety_car()) {
                    System.out.println("Il giudice ha dichiarato che l'incidente richiede l'intervento della safety car!");
                    for (int i = 0; i < macchine.length; i++) {
                        macchine[i].setKmPercorribili(false);
                    }
                    tempo = System.currentTimeMillis();
                } else {
                    System.out.println("Il giudice non ritiene che sia necessaria la safety car!");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

            } else if (System.currentTimeMillis() - tempo > 10000) {
                System.out.println("La safety car è uscita! Le macchine possono tornare alla velocità standard!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                for (int i = 0; i < macchine.length; i++) {
                    macchine[i].setKmPercorribili(true);
                }
                tempo = System.currentTimeMillis() + 500000;
            }

            for (int i = 0; i < macchine.length; i++) {
                System.out.println(macchine[i].toString());
            }
            System.out.println("Macchine arrivate: " + macchine_arrivate.size() + " su "
                    + (macchine.length - macchine_incidentate.size()));
            ;
            System.out.println("Macchine incidentate: " + macchine_incidentate.size());
            System.out.println("");

            n_incidentate_prima = macchine_incidentate.size();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            clearScreen();
        }

        System.out.println("Gara terminata!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        System.out.println("/////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("                Il vincitore è " + macchine_arrivate.get(0));
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        clearScreen();

        System.out.println("Classifica finale del Grand Prix di " + circuito.getNome() + ":");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        stampaClassifica();
    }

    /**
     * Stampa la classifica finale della gara.
     */
    public static void stampaClassifica() {
        for (int i = 0; i < macchine_arrivate.size(); i++) {
            System.out.println((i + 1) + "°) " + macchine_arrivate.get(i));
        }
        if (macchine_incidentate != null) {
            for (int i = 0; i < macchine_incidentate.size(); i++) {
                System.out
                        .println((i + macchine_arrivate.size() + 1) + "°) " + macchine_incidentate.get(i) + " (Incidentata)");
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Aggiunge una macchina alla lista di quelle arrivate.
     *
     * @param nome_macchina  Il nome della macchina.
     * @param nome_pilota    Il nome del pilota associato alla macchina.
     */
    public static synchronized void aggiungiFinita(String nome_macchina, String nome_pilota) {
        macchine_arrivate.add("l'auto '" + nome_macchina + "' con pilota '" + nome_pilota + "'");
    }

    /**
     * Aggiunge una macchina alla lista di quelle incidentate.
     *
     * @param nome_macchina  Il nome della macchina.
     * @param nome_pilota    Il nome del pilota associato alla macchina.
     */
    public static synchronized void aggiungiIncidentata(String nome_macchina, String nome_pilota) {
        macchine_incidentate.add("l'auto '" + nome_macchina + "' con pilota '" + nome_pilota + "'");
    }

    /**
     * Restituisce il circuito associato alla gara.
     *
     * @return Il circuito della gara.
     */
    public Circuito getCircuito() {
        return circuito;
    }
}
