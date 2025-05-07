/**
 * Repräsentiert einen Artikel, der in einer Auktion verkauft wird.
 */
public class Item {
    private String name;
    private double startPreis;
    private double mindestPreis;

    public Item(String name, double startPreis, double mindestPreis) {
        this.name = name;
        this.startPreis = startPreis;
        this.mindestPreis = mindestPreis;
    }
    public String toString() {
    	return name;
    }

    public String getName() {
        return name;
    }

    public double getStartPreis() {
        return startPreis;
    }

    public double getMindestPreis() {
        return mindestPreis;
    }
}