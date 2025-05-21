package Nutzer;
/**
 * Repräsentiert einen Benutzer der Plattform, der entweder als Bieter oder Auktionator aktiv sein kann.
 * Jeder Benutzer besitzt einen Benutzernamen, eine Wahrscheinlichkeit zur Teilnahme an Geboten sowie ein Budget.
 */
public class User {
    private String username;
    private double wahrscheinlichkeit;
    private double budget;

    // Gibt an, ob der Benutzer aktuell als Bieter aktiv ist
    private boolean isBieter;

    // Gibt an, ob der Benutzer aktuell als Auktionator aktiv ist
    private boolean isAuktionator;

    /**
     * Konstruktor zum Erstellen eines neuen Benutzers.
     *
     * @param username         Benutzername
     * @param wahrscheinlichkeit Wahrscheinlichkeit, mit der ein Benutzer bietet (zwischen 0 und 1)
     * @param budget           Das verfügbare Budget des Benutzers in Euro
     */
    public User(String username, double wahrscheinlichkeit, double budget) {
        this.username = username;
        this.wahrscheinlichkeit = wahrscheinlichkeit;
        this.budget = budget;
    }

    /**
     * Gibt den Benutzernamen zurück.
     *
     * @return Benutzername
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gibt die Wahrscheinlichkeit zurück, mit der der Benutzer ein Gebot abgibt.
     *
     * @return Gebotswahrscheinlichkeit
     */
    public double getWahrscheinlichkeit() {
        return wahrscheinlichkeit;
    }

    /**
     * Prüft, ob der Benutzer aktuell als Bieter aktiv ist.
     *
     * @return {@code true}, wenn als Bieter aktiv, sonst {@code false}
     */
    public boolean isBieter() {
        return isBieter;
    }

    /**
     * Setzt den Benutzer als Bieter aktiv oder inaktiv.
     *
     * @param isBieter {@code true}, wenn als Bieter aktiv sein soll, sonst {@code false}
     */
    public void setBieter(boolean isBieter) {
        this.isBieter = isBieter;
    }

    /**
     * Prüft, ob der Benutzer aktuell als Auktionator aktiv ist.
     *
     * @return {@code true}, wenn als Auktionator aktiv, sonst {@code false}
     */
    public boolean isAuktionator() {
        return isAuktionator;
    }

    /**
     * Setzt den Benutzer als Auktionator aktiv oder inaktiv.
     *
     * @param isAuktionator {@code true}, wenn als Auktionator aktiv sein soll, sonst {@code false}
     */
    public void setAuktionator(boolean isAuktionator) {
        this.isAuktionator = isAuktionator;
    }

    /**
     * Gibt das aktuelle Budget des Benutzers zurück.
     *
     * @return Budget in Euro
     */
    public double getBudget() {
        return budget;
    }
    /**
     * Reduziert das aktuelle Budget des Benutzers nach Kauf.
     *
     * 
     */
    public void reduceBudget(double betrag) {
    	budget-=betrag;
    	
    }

}

