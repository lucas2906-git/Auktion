/**
 * Repräsentiert einen Benutzer der Plattform.
 */

public class User  {
    private String username;
    private double wahrscheinlichkeit;
    private double budget;
    
 // Attribut ob der User als Bieter aktuell aktiv ist
    private boolean isBieter;
    
    // Attribut ob der User als Auktionator aktuell aktiv ist
    private boolean isAuktionator;
    
    public User(String username, double wahrscheinlichkeit, double budget) {
        this.username = username;
        this.wahrscheinlichkeit = wahrscheinlichkeit;
        this.budget = budget;
    }

	public String getUsername() {
        return username;
    }
	public double getWahrscheinlichkeit() {
		return wahrscheinlichkeit;
	}
    public boolean isBieter() {
        return isBieter;
    }

    public void setBieter(boolean isBieter) {
        this.isBieter = isBieter;
    }

    public boolean isAuktionator() {
        return isAuktionator;
    }

    public void setAuktionator(boolean isAuktionator) {
        this.isAuktionator = isAuktionator;
    }
    public double getBudget() {
    	return budget;
    }
    public void reduceBudget(double betrag) {
    	budget=-betrag;
    	
    }
  
}

