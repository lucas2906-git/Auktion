
/**
 * Repräsentiert das Auktionshaus und steuert die Simulation.
 */
import java.util.List;
public class Auktionshaus {
    private static Auktionshaus instance;
    private Userverwaltung userverwaltung;
    private Auktionsverwaltung auktionsverwaltung;

    private Auktionshaus() {
        userverwaltung = new Userverwaltung();
        auktionsverwaltung = new Auktionsverwaltung();
    }

    public static Auktionshaus getInstance() {
        if (instance == null) {
            instance = new Auktionshaus();
        }
        return instance;
    }

    // Getter für die Userverwaltung
    public Userverwaltung getUserverwaltung() {
        return userverwaltung;
    }

    // Getter für die Auktionsverwaltung
    public Auktionsverwaltung getAuktionsverwaltung() {
        return auktionsverwaltung;
    }

    public void startSimulation() {
        // Benutzer initialisieren???????????????????????????????????????????????????????????????????????????
        userverwaltung.addUser(new User("a", 0.5, 100.2));
        userverwaltung.addUser(new User("a", 0.5, 100.2));
        userverwaltung.addUser(new User("a", 0.5, 100.2));

        // Artikel erstellen
        Item item = new Item("Antike Vase", 100.0, 50.0);

        // Auktion starten
        User auktionator = userverwaltung.getAuktionator();
        List<User> bieter = userverwaltung.getBieterList();

        Auktion auktion = auktionsverwaltung.erstelleAuktion(item, auktionator, bieter);
        auktionsverwaltung.starteAuktion(auktion);

        // Warte auf das Ende der Auktion
        try {
            Thread.sleep(5000); // Warte 5 Sekunden, um die Auktion zu beenden
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Bericht erstellen
        erstelleBericht();
    }

    public void erstelleBericht() {
        List<Auktion> auktionen = auktionsverwaltung.getAuktionen();
        int anzahlAuktionen = auktionen.size();
        int anzahlAuktionatoren = userverwaltung.getAuktionatorList().size();
        int anzahlBieter = userverwaltung.getBieterList().size() - anzahlAuktionatoren;
        int anzahlVerkaufteArtikel = 0;
        double gesamtProvision = 0.0;
        int gesamtBieter = 0;

        for (Auktion auktion : auktionen) {
            if (auktion.istArtikelVerkauft()) {
                anzahlVerkaufteArtikel++;
                gesamtProvision += auktion.getProvision();
            }
            gesamtBieter += auktion.getBieter().size();
        }

        double durchschnittlicheBieterProAuktion = (double) gesamtBieter / anzahlAuktionen;

        System.out.println("\n--- Bericht der Simulation ---");
        System.out.println("Anzahl der durchgeführten Auktionen: " + anzahlAuktionen);
        System.out.println("Anzahl der Auktionatoren: " + anzahlAuktionatoren);
        System.out.println("Anzahl der Bieter: " + anzahlBieter);
        System.out.println("Durchschnittliche Bieter pro Auktion: " + durchschnittlicheBieterProAuktion);
        System.out.println("Anzahl der verkauften Artikel: " + anzahlVerkaufteArtikel);
        System.out.println("Gesamtbetrag der Provision: " + gesamtProvision + " €");
    }
}