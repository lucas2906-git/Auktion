import java.util.List;

/**
 * Repräsentiert das Auktionshaus, das als Singleton implementiert ist.
 * Es verwaltet Benutzer und Auktionen und steuert den Ablauf der Auktion mittels Simulation.
 */
public class Auktionshaus {

    private static Auktionshaus instance;
    private Userverwaltung userverwaltung;
    private Auktionsverwaltung auktionsverwaltung;

    /**
     * Privater Konstruktor zur Umsetzung des Singleton-Musters.
     * Initialisiert die Userverwaltung und Auktionsverwaltung.
     */
    private Auktionshaus() {
        userverwaltung = new Userverwaltung();
        auktionsverwaltung = new Auktionsverwaltung();
    }

    /**
     * Gibt die einzige Instanz des Auktionshauses zurück. 
     * Wird bei Bedarf erstellt (Lazy Initialization).
     *
     * @return die Instanz des Auktionshauses
     */
    public static Auktionshaus getInstance() {
        if (instance == null) {
            instance = new Auktionshaus();
        }
        return instance;
    }

    /**
     * Gibt die Userverwaltung zurück.
     *
     * @return das Userverwaltung-Objekt
     */
    public Userverwaltung getUserverwaltung() {
        return userverwaltung;
    }

    /**
     * Gibt die Auktionsverwaltung zurück.
     *
     * @return das Auktionsverwaltung-Objekt
     */
    public Auktionsverwaltung getAuktionsverwaltung() {
        return auktionsverwaltung;
    }


    /**
     * Erstellt und gibt einen Bericht über alle durchgeführten Auktionen aus.
     * Enthält Informationen über die Anzahl der Auktionen, Bieter, Verkäufe und Provisionssummen.
     */
    public void erstelleBericht() {
        List<Auktion> auktionen = auktionsverwaltung.getAuktionen();
        int anzahlAuktionen = auktionen.size();
        int anzahlAuktionatoren = userverwaltung.getAuktionatorList().size();
        int anzahlBieter = userverwaltung.getBieterList().size();
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
