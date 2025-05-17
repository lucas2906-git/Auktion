import java.util.List;

/**
 * Repräsentiert eine Auktion für einen Artikel, der von einem Auktionator angeboten wird
 * und an eine Liste von Bietern versteigert wird.
 * Die Auktion senkt periodisch den Preis, bis ein Bieter bietet oder der Mindestpreis erreicht ist.
 */
public class Auktion implements Runnable {
    private Item item;                  // Der versteigerte Artikel
    private User auktionator;          // Der Auktionator (Verkäufer)
    private List<User> bieter;         // Liste der potenziellen Bieter
    private double aktuellerPreis;     // Der aktuelle Preis des Artikels
    private boolean istAktiv;          // Status, ob die Auktion noch läuft
    private double provision;          // Die vom Auktionator verdiente Provision
    private boolean artikelVerkauft;   // Gibt an, ob der Artikel verkauft wurde

    /**
     * Erstellt eine neue Auktion.
     *
     * @param item Der zu versteigernde Artikel
     * @param auktionator Der Auktionator (Verkäufer)
     * @param bieter Liste der teilnehmenden Bieter
     */
    public Auktion(Item item, User auktionator, List<User> bieter) {
        this.item = item;
        this.auktionator = auktionator;
        this.bieter = bieter;
        this.aktuellerPreis = item.getStartPreis();
        this.istAktiv = true;
        this.provision = 0.0;
        this.artikelVerkauft = false;
    }

    /**
     * Verringert den aktuellen Preis um 1€, solange dieser über dem Mindestpreis liegt.
     * Falls der Mindestpreis erreicht wird, wird die Auktion deaktiviert.
     */
    public synchronized void preisVerringern() {
        if (aktuellerPreis > item.getMindestPreis()) {
            aktuellerPreis -= 1.0;
        } else {
            istAktiv = false;
        }
    }

    /**
     * Ein Bieter versucht ein Gebot basierend auf einer Wahrscheinlichkeit abzugeben.
     *
     * @param bieter Der möglicherweise bietende Nutzer
     * @param gebotswahrscheinlichkeit Die Wahrscheinlichkeit, mit der geboten wird
     * @return true, wenn erfolgreich geboten wurde (Artikel verkauft), sonst false
     */
    public synchronized boolean biete(User bieter, double gebotswahrscheinlichkeit) {
    	
        if (istAktiv && Math.random() < gebotswahrscheinlichkeit) {
        	 if (istAktiv && Math.random() < gebotswahrscheinlichkeit) {
        	        if (bieter.getBudget() >= aktuellerPreis) { // Prüfen, ob genug Budget vorhanden ist
        	            istAktiv = false;
        	            artikelVerkauft = true;
        	            bieter.reduceBudget(aktuellerPreis); // Budget des Bieters nach Kauf reduzieren
        	            provision = aktuellerPreis * 0.18; // 18% Provision
        	            return true;
        	       
        	        }
        	 }
        }
    	
        return false;
    }

    /**
     * Führt die Auktion in einem separaten Thread aus. Reduziert regelmäßig den Preis und
     * überprüft, ob ein Bieter ein Gebot abgibt. Die Auktion endet entweder mit Verkauf
     * oder wenn der Mindestpreis erreicht ist.
     */
    public void run() {
        while (istAktiv && !artikelVerkauft) {
            preisVerringern();
            for (User b : bieter) {
                if (biete(b, b.getWahrscheinlichkeit())) {
                    System.out.println(item + " verkauft an " + b.getUsername() +
                                       " von " + auktionator.getUsername() +
                                       " für " + aktuellerPreis + "€");
                    return;
                }
            }
            try {
                Thread.sleep(100); // Warte 0,1 Sekunden
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Auktion beendet, Artikel nicht verkauft.");
    }

    /**
     * Gibt die durch den Verkauf generierte Provision zurück.
     *
     * @return Die Provision in Euro
     */
    public double getProvision() {
        return provision;
    }
    public boolean istArtikelVerkauft() {
        return artikelVerkauft;
    }

    public List<User> getBieter() {
        return bieter;
    }
}
