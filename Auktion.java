
import java.util.List;

/**
 * Repräsentiert eine Auktion mit einem Artikel, einem Auktionator und einer Liste von Bietern.
 */
public class Auktion implements Runnable {
    private Item item;
    private User auktionator;
    private List<User> bieter;
    private double aktuellerPreis;
    private boolean istAktiv;
    private double provision;
    private boolean artikelVerkauft;

    public Auktion(Item item, User auktionator, List<User> bieter) {
        this.item = item;
        this.auktionator = auktionator;
        this.bieter = bieter;
        this.aktuellerPreis = item.getStartPreis();
        this.istAktiv = true;
        this.provision = 0.0;
        this.artikelVerkauft = false;
    }

    public synchronized void preisVerringern() {
        if (aktuellerPreis > item.getMindestPreis()) {
            aktuellerPreis -= 1.0; // Preis wird um 1€ gesenkt
        } else {
            istAktiv = false;
        }
    }

    public synchronized boolean biete(User bieter, double gebotswahrscheinlichkeit) {
        if (istAktiv && Math.random() < gebotswahrscheinlichkeit) {
            istAktiv = false;
            artikelVerkauft = true;
            provision = aktuellerPreis * 0.18; // 1% Provision
            return true;
        }
        return false;
    }

    public void run() {
        while (istAktiv && !artikelVerkauft) {
            preisVerringern();
            for (User b : bieter) {
                if (biete(b, b.getWahrscheinlichkeit())) {
                    System.out.println(item + " verkauft an " + b.getUsername() + " von " + auktionator.getUsername()+ " für " + aktuellerPreis);
                    return;
                }
            }
            try {
                Thread.sleep(1000); // Warte 1 Sekunde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Auktion beendet, Artikel nicht verkauft.");
    }

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