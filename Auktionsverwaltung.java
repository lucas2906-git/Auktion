import java.util.ArrayList;
import java.util.List;

/**
 * Verwaltet alle laufenden und erstellten Auktionen innerhalb des Auktionshauses.
 * Zuständig für das Erzeugen neuer Auktionen und deren Start in eigenen Threads.
 */
public class Auktionsverwaltung {

    private List<Auktion> auktionen = new ArrayList<>();

    /**
     * Erstellt eine neue Auktion mit dem gegebenen Artikel, Auktionator und der Liste von Bietern.
     * Die Auktion wird zur internen Liste hinzugefügt.
     *
     * @param item         Der zu versteigernde Artikel
     * @param auktionator  Der Auktionator (Verkäufer)
     * @param bieter       Die Liste der teilnehmenden Bieter
     * @return Die erstellte Auktion
     */
    public Auktion erstelleAuktion(Item item, User auktionator, List<User> bieter) {
        Auktion auktion = new Auktion(item, auktionator, bieter);
        auktionen.add(auktion);
        return auktion;
    }

    /**
     * Startet die übergebene Auktion in einem neuen Thread.
     * Die Auktion läuft damit parallel und autonom.
     *
     * @param auktion Die zu startende Auktion
     */
  public void starteAuktionen() {
        List<Thread> threadListe = new ArrayList<>();

        // Starte alle Auktionen und speichere ihre Threads
        for (Auktion auktion : auktionen) {
            Thread t = new Thread(auktion);
            t.start();
            threadListe.add(t);
        }

        // Warte auf das Ende aller Auktionen
        for (Thread t : threadListe) {
            try {
                t.join(); // Blockiert das Hauptprogramm nur nach dem Start aller Auktionen
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
    }


    /**
     * Gibt eine Liste aller bisher erstellten Auktionen zurück.
     *
     * @return Die Liste der Auktionen
     */
    public List<Auktion> getAuktionen() {
        return auktionen;
    }
}
