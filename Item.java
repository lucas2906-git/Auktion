package Auktion;
/**
 * Repräsentiert einen Artikel, der in einer Auktion verkauft wird.
 * Ein Artikel besitzt einen Namen, einen Startpreis sowie einen Mindestpreis.
 */
public class Item {

    private String name;
    private double startPreis;
    private double mindestPreis;

    /**
     * Konstruktor zum Erstellen eines Artikels mit Name, Startpreis und Mindestpreis.
     *
     * @param name          Der Name des Artikels
     * @param startPreis    Der Startpreis der Auktion
     * @param mindestPreis  Der Mindestpreis, unter den der Artikel nicht verkauft wird
     */
    public Item(String name, double startPreis, double mindestPreis) {
        this.name = name;
        this.startPreis = startPreis;
        this.mindestPreis = mindestPreis;
    }

    /**
     * Gibt den Namen des Artikels als String zurück.
     *
     * @return Der Name des Artikels
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Gibt den Namen des Artikels zurück.
     *
     * @return Der Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt den Startpreis der Auktion zurück.
     *
     * @return Der Startpreis in Euro
     */
    public double getStartPreis() {
        return startPreis;
    }

    /**
     * Gibt den Mindestpreis des Artikels zurück.
     * Die Auktion endet, wenn dieser Preis unterschritten wird.
     *
     * @return Der Mindestpreis in Euro
     */
    public double getMindestPreis() {
        return mindestPreis;
    }
}
