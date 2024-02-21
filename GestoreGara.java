import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Questa classe rappresenta il gestore della gara del Grand Prix.
 */
public class GestoreGara {

    /**
     * Il metodo principale per avviare e gestire la gara del Grand Prix.
     *
     * @param args Gli argomenti da linea di comando (non utilizzati in questo caso).
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Richiesta del nome e della password dell'utente
        System.out.print("Inserisci il tuo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci la tua password: ");
        String password = scanner.nextLine();

        // Creazione e memorizzazione del giocatore
        Giocatore giocatore = new Giocatore(nome, password);
        memorizza_giocatore(giocatore);

        System.out.println("Benvenuto al Grand Prix!");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Prima di iniziare la gara, dovrai inserire alcuni dati...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Richiesta dei dati per la gara
        int numero_piloti;
        Pilota[] piloti;
        Macchina[] macchine;
        Circuito circuito;
        int numero_giri;

        // Creazione del circuito
        System.out.print("Inserisci il nome del circuito su cui vuoi gareggiare: ");
        String nome_circuito = scanner.nextLine();

        System.out.print("Inserisci la lunghezza del circuito (in chilometri): ");
        int lunghezza_circuito = scanner.nextInt();

        circuito = new Circuito(nome_circuito, lunghezza_circuito);

        // Richiesta del numero di giri necessari al completamento della gara
        System.out.print("Inserisci il numero di giri necessari al completamento della gara: ");
        numero_giri = scanner.nextInt();
        scanner.nextLine(); // Resetto lo scanner

        System.out.println("Ora passiamo all'inserimento dei dati dei giocatori...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Inizializzazione degli array di piloti e macchine
        System.out.print("Inserisci il numero di piloti che parteciperanno: ");
        numero_piloti = scanner.nextInt();
        scanner.nextLine(); // Resetto lo scanner
        
        if (numero_piloti <= 1) {
            System.out.println("Non possono esserci meno di 2 piloti che gareggiano.\nImposto il numero di giocatori a 2");
            numero_piloti = 2;
        }

        piloti = new Pilota[numero_piloti];
        macchine = new Macchina[numero_piloti];

        System.out.println("Ora inserisci i dati dei giocatori...");

        // Creazione dei piloti e delle macchine
        for (int i = 0; i < numero_piloti; i++) {
            System.out.print("Inserisci il nome del pilota " + (i + 1) + ": ");
            String nome_pilota = scanner.nextLine();
            piloti[i] = new Pilota(nome_pilota);

            System.out.print("Inserisci il nome della macchina di " + nome_pilota + ": ");
            String nome_macchina = scanner.nextLine();
            String risposta;
            do {
                System.out.print("Vuoi che la macchina " + nome_macchina + " sia truccata? (si/no) ");
                risposta = scanner.nextLine().toLowerCase();
                if (!(risposta.equals("si") || risposta.equals("no")))
                    System.out.println("Risposta non valida.");
            } while (!(risposta.equals("si") || risposta.equals("no")));
            boolean truccata = risposta.equals("si");

            macchine[i] = new Macchina(nome_macchina, truccata, circuito, numero_giri);
            macchine[i].setPilota(piloti[i]);
        }

        // Creazione e avvio della gara
        Gara gara = new Gara(piloti, macchine, circuito, numero_giri, giocatore);
        gara.avvia();

        scanner.close();
    }

    /**
     * Memorizza i dati del giocatore, inclusa la cifratura della password.
     *
     * @param giocatore Il giocatore da memorizzare.
     */
    public static void memorizza_giocatore(Giocatore giocatore) {
        String verme = "MAZZMANDPIGL";
        VigenereEncryptor cifratore = new VigenereEncryptor(verme);
        String password = cifratore.encrypt(giocatore.getPassword());

        // Creazione di una stringa JSON
        String jsonContent = "{ \"" + giocatore.getNome() + "\":";

        jsonContent += "\"" + password + "\"";

        jsonContent += "}";

        // Scrittura del file JSON
        String file_giocatore = ".\\Giocatore.json";
        try (FileWriter fileWriter = new FileWriter(file_giocatore)) {
            fileWriter.write(jsonContent);
        } catch (IOException e) {
            System.err.println("Error writing to JSON file: " + e.getMessage());
        }
    }
}
