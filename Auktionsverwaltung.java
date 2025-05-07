
import java.util.ArrayList;
import java.util.List;

/**
 * Verwaltet die Logik für das Erstellen und Starten von Auktionen.
 */
public class Auktionsverwaltung {
    private List<Auktion> auktionen = new ArrayList<>();

    public Auktion erstelleAuktion(Item item, User auktionator, List<User> bieter) {
        Auktion auktion = new Auktion(item, auktionator, bieter);
        auktionen.add(auktion);
        return auktion;
    }

    public void starteAuktion(Auktion auktion) {
        new Thread(auktion).start();
    }

    public List<Auktion> getAuktionen() {
        return auktionen;
    }
}