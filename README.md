# Informazioni sul Progetto



## Collaboratori
- **Matteo Mazzieri**
  - [GitHub](https://github.com/Matty0five)

- **Filippo Mandorlo**
  - [GitHub](https://github.com/FilippoMandorlo)     

- **Tommaso Pigliautile**
  - [GitHub](https://github.com/tommasowww)

## Guida Didattica
- **Docente: Monica Ciuchetti**
  - [GitHub](https://github.com/mciuchetti)

- **Docente di Laboratorio: Francesco Amendola**
  - [GitHub](https://github.com/amendola-scuola)

## Dettagli Scolastici
- **Anno Scolastico: 2023/2024**
- **Classe: 5BINF**
- **Corso: TPSIT**
- **Istituto: [ITTS A. Volta](https://www.avoltapg.edu.it/)**
  - *Perugia (PG), Italia*

## Requisiti del Progetto

In previsione del prossimo GrandPrix è necessario progettare un simulatore che consenta l’accesso riservato di ogni giocatore. I dati dei piloti e delle loro auto sono memorizzati in un file, così come quelle dei giocatori. In particolare le password dei giocatori sono cifrate secondo il cifrario di Vigénere.

Ogni giocatore può scegliere il numero di auto da far partire contemporaneamente e il circuito su cui gareggiano. 

Ogni circuito ha una lunghezza definita ed ogni secondo viene percorso un tratto specifico dalle auto in funzione della loro velocità.

Il giocatore può impostare la lunghezza del circuito, il numero di giri necessario al completamento della gara, il numero di possibili pit stop. Può inoltre scegliere di truccare una macchina o di fermarne una per un incidente e se far intervenire ad un certo punto della gara la safety car.

Il giudice di gara deve poter comunicare a tutti i partecipanti e al pubblico l’inizio e la fine della gara. Durante la gara deve mostrare l’avanzamento delle auto e notificare eventuali situazioni anomale. Al termine della gara deve comunicare il vincitore e poi salvare la classifica in un file che potrà essere letto dal giocatore alla fine del gioco.


## Spiegazioni delle Classi

### Circuito

La classe `Circuito` rappresenta il circuito su cui si svolge una gara del Grand Prix. Ogni circuito ha un nome e una lunghezza in chilometri.

**Attributi:**

- `nome` (String): Nome del circuito.
- `lunghezza` (int): Lunghezza del circuito in chilometri.

**Costruttori:**

- `Circuito(String nome, int lunghezza)`: Costruttore della classe Circuito. Inizializza il circuito con il nome e la lunghezza specificati.

**Metodi:**

- `setLunghezza(int lunghezza)`: Imposta la lunghezza del circuito.
- `getLunghezza(): int`: Restituisce la lunghezza del circuito.
- `getNome(): String`: Restituisce il nome del circuito.

### Gara

La classe `Gara` gestisce una gara del Grand Prix con piloti, macchine, un circuito specifico e un giocatore. Durante la gara, vengono gestiti eventi come incidenti e l'intervento della safety car.

**Attributi:**

- `piloti` (Pilota[]): Array di piloti partecipanti alla gara.
- `macchine` (Macchina[]): Array di macchine partecipanti alla gara.
- `macchine_arrivate` (ArrayList<String>): Lista delle macchine arrivate.
- `macchine_incidentate` (ArrayList<String>): Lista delle macchine incidentate.
- `circuito` (Circuito): Il circuito su cui si svolge la gara.
- `numero_giri` (int): Il numero di giri previsti per la gara.
- `giocatore` (Giocatore): Il giocatore partecipante alla gara.
- `giudice` (Giudice): Oggetto Giudice per prendere decisioni durante la gara.

**Costruttori:**

- `Gara(Pilota[] piloti, Macchina[] macchine, Circuito circuito, int numero_giri, Giocatore giocatore)`: Costruttore della classe Gara. Inizializza la gara con i partecipanti, il circuito, il numero di giri e il giocatore specificati.

**Metodi:**

- `avvia()`: Avvia la gara e gestisce gli eventi, inclusi gli incidenti e l'intervento della safety car.
- `clearScreen()`: Metodo per cancellare il contenuto della console.
- `stampaClassifica()`: Stampa la classifica finale della gara.
- `aggiungiFinita(String nome_macchina, String nome_pilota)`: Aggiunge una macchina alla lista di quelle arrivate.
- `aggiungiIncidentata(String nome_macchina, String nome_pilota)`: Aggiunge una macchina alla lista di quelle incidentate.
- `getCircuito(): Circuito`: Restituisce il circuito su cui si sta svolgendo la gara.

### Giocatore

La classe `Giocatore` rappresenta l'utente, che inizialmente inserisce i propri dati (username e password) e dopo inserisce tutte le informazioni necessarie per l'avvio e lo svolgimento del Grand Prix.

**Attributi:**

- `nome` (String): Il nome del giocatore.
- `password` (String): La password del giocatore.

**Costruttori:**

- `Giocatore(String nome, String password)`: Costruttore della classe Giocatore. Inizializza il giocatore con il nome e la password specificati.

**Metodi:**

- `getNome(): String`: Restituisce il nome del giocatore.
- `getPassword(): String`: Restituisce la password del giocatore.

### Pilota

La classe `Pilota` rappresenta un pilota coinvolto nella gara del Grand Prix. Ogni pilota ha un nome.

**Attributi:**

- `nome` (String): Il nome del pilota.

**Costruttori:**

- `Pilota(String nome)`: Costruttore della classe Pilota. Inizializza il pilota con il nome specificato.

**Metodi:**

- `getNome(): String`: Restituisce il nome del pilota.

### Macchina

Questa classe rappresenta una macchina coinvolta nella gara del Grand Prix.

#### Attributi

- `nome`: Il nome della macchina.
- `truccata`: Un flag che indica se la macchina è truccata o meno.
- `pilota`: Il pilota associato alla macchina.
- `running`: Uno stato che indica se la macchina è in esecuzione.
- `kmPercorsi`: La distanza percorsa dalla macchina.
- `incidentata`: Uno stato che indica se la macchina è incidentata.
- `giriCompletati`: Il numero di giri completati dalla macchina.
- `lunghezzaCircuito`: La lunghezza del circuito su cui si svolge la gara.
- `giriDaCompletare`: Il numero di giri previsti per la gara.
- `kmPercorribili`: La velocità percorribile della macchina.
- `VELOCITA_STANDARD`: La velocità standard della macchina.
- `VELOCITA_TRUCCATA`: La velocità truccata della macchina.

#### Metodi

- `public Macchina(String nome, boolean truccata, Circuito circuito, int giri)`: Costruttore della classe.
- `public void run()`: Esegue il thread della macchina, gestendo il percorso, gli incidenti e la fine della gara.
- `public double arrotonda(double numero_da_arrotondare, int numero_cifre_decimali)`: Arrotonda un numero a un numero specificato di cifre decimali.
- `public String toString()`: Restituisce una rappresentazione in stringa della macchina.
- `public void setKmPercorribili(boolean scelta)`: Imposta la velocità percorribile della macchina.
- `public double getKmPercorsi()`: Restituisce la distanza percorsa dalla macchina.
- `public boolean getIncidentata()`: Restituisce lo stato di incidentata della macchina.
- `public String getNome()`: Restituisce il nome della macchina.
- `public void setPilota(Pilota pilota)`: Imposta il pilota associato alla macchina.
- `public Pilota getPilota()`: Restituisce il pilota associato alla macchina.
- `public boolean getRunning()`: Restituisce lo stato di esecuzione della macchina.

### Giudice

La classe `Giudice` prende decisioni durante la gara, come l'attivazione della safety car.

**Metodi:**

- `Giudice()`: Costruttore della classe Giudice.
- `decidiIncidente()`: Decide se verificare un incidente durante la gara.
- `decidiSafetyCar()`: Decide se attivare la safety car durante la gara.

### GestoreGara

Questa classe rappresenta il gestore della gara del Grand Prix.

#### Metodi

- `public static void main(String[] args)`: Il metodo principale per avviare e gestire la gara del Grand Prix.
- `public static void memorizza_giocatore(Giocatore giocatore)`: Memorizza i dati del giocatore, inclusa la cifratura della password.
- `public void avvia()`: Avvia la gara e gestisce gli eventi, inclusi gli incidenti e l'intervento della safety car.
- `public static void clearScreen()`: Metodo per cancellare il contenuto della console, utile per rendere più leggibili gli aggiornamenti della gara.
- `public static void stampaClassifica()`: Stampa la classifica finale della gara.
- `public static synchronized void aggiungiFinita(String nome_macchina, String nome_pilota)`: Aggiunge una macchina alla lista di quelle arrivate.
- `public static synchronized void aggiungiIncidentata(String nome_macchina, String nome_pilota)`: Aggiunge una macchina alla lista di quelle incidentate.
- `public Circuito getCircuito()`: Restituisce il circuito associato alla gara.

### Matrice e VigenereEncryptor

Le classi `Matrice`, `Vigenere` e `VigenereEncryptor` sono utilizzate per implementare la cifratura di Vigenere.

## Avvio del Programma

Per avviare la simulazione della gara del Grand Prix, eseguire la classe `GestoreGara` e seguire le istruzioni fornite.