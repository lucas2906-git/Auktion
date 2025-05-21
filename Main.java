package Auktion;
import java.util.List;

import Nutzer.User;
import Nutzer.Userverwaltung;

import java.io.*;

/**
 * Startet die Simulation des Auktionshauses.
 * Richtet Benutzer, Artikel und Auktionen ein und leitet die Ausgabe zusätzlich in eine Datei um.
 */
public class Main {

  

    /**
     * Hilfsklasse zum gleichzeitigen Schreiben in mehrere OutputStreams.
     * Wird verwendet, um die Konsolenausgabe zusätzlich in eine Datei umzuleiten.
     */
    static class MultiOutputStream extends OutputStream {
        private OutputStream[] ausgaeben;

        /**
         * Konstruktor zur Übergabe mehrerer Ausgabekanäle.
         *
         * @param ausgaeben Beliebige Anzahl an OutputStreams (z. B. Konsole und Datei)
         */
        public MultiOutputStream(OutputStream... ausgaeben) {
            this.ausgaeben = ausgaeben;
        }

        @Override
        public void write(int b) throws IOException {
            for (OutputStream out : ausgaeben) {
                out.write(b);
            }
        }

        @Override
        public void flush() throws IOException {
            for (OutputStream out : ausgaeben) {
                out.flush();
            }
        }

        @Override
        public void close() throws IOException {
            for (OutputStream out : ausgaeben) {
                out.close();
            }
        }
    }

    /**
     * Der Einstiegspunkt für die Auktionshaus-Simulation.
     * Fügt Benutzer hinzu, weist Rollen zu, erstellt Artikel und Auktionen und führt die Simulation aus.
     * Die Konsolenausgabe wird zusätzlich in die Datei "ausgabe.txt" umgeleitet.
     *
     * @param args Kommandozeilenargumente (nicht verwendet)
     */
    public static void main(String[] args) {

        try {
            // Ursprüngliche Konsole merken
            PrintStream konsole = System.out;

            // Datei vorbereiten
            PrintStream datei = new PrintStream(new FileOutputStream("ausgabe.txt"));

            // Kombinierter Stream: schreibt an Konsole + Datei
            PrintStream kombiniert = new PrintStream(new MultiOutputStream(konsole, datei));

            // System.out umleiten
            System.setOut(kombiniert);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Auktionshaus auktionshaus = Auktionshaus.getInstance();
        Userverwaltung userverwaltung = auktionshaus.getUserverwaltung();

        // Benutzer hinzufügen
        userverwaltung.addUser(new User("Rainer Winkler", 0.12, 300.60));
        userverwaltung.addUser(new User("Walter Frosch", 0.84, 300.50));
        userverwaltung.addUser(new User("Robin Burkert", 0.04, 430.20));
        userverwaltung.addUser(new User("Deniz Undav", 0.14, 1000.10));
        userverwaltung.addUser(new User("Luca Bader", 0.04, 120.02));
        userverwaltung.addUser(new User("Lucas Ringer", 0.04, 450.20));
        userverwaltung.addUser(new User("Fabian Groß", 0.04, 780.21));
        userverwaltung.addUser(new User("Hansi Flick", 0.24, 9000.2));
        userverwaltung.addUser(new User("Jogi Löw", 0.14, 221.2));
        userverwaltung.addUser(new User("Julian Nagelsmann", 0.14, 2000.20));
        userverwaltung.addUser(new User("Emre Abi", 0.08, 401.5));

        // Zuweisung der Rollen (3 Bieter, 2 Auktionatoren)
        // (wer Bieter ist kann nicht Auktionator sein und umgekehrt)
        userverwaltung.assignRandomRolesDisjoint(5, 2);

        // Artikel erstellen
        Item item1 = new Item("Antike Vase", 400.0, 200.0);
        Item item2 = new Item("Döner", 15.0, 5.0);
        

        // Auktionatoren und Bieter vorbereiten
        User auktionator1 = userverwaltung.getAuktionator(0);
        User auktionator2 = userverwaltung.getAuktionator(1);
        List<User> bieter = userverwaltung.getBieterList();

        if (bieter.isEmpty()) {
            System.out.println("Keine Bieter verfügbar!");
            return;
        }

        // Auktionen erstellen und starten
        
        auktionshaus.getAuktionsverwaltung().erstelleAuktion(item1, auktionator1, bieter);
        auktionshaus.getAuktionsverwaltung().erstelleAuktion(item2, auktionator2, bieter);
        auktionshaus.getAuktionsverwaltung().starteAuktionen();


        // Abschlussbericht ausgeben
        auktionshaus.erstelleBericht();
    }
}
