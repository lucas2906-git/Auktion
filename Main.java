import java.util.List;

/**
 * Startet die Simulation des Auktionshauses.
 */
public class Main {
	
	public static int zufallszahl() 
	{
		int zufallszahl = (int)((Math.random()) * 5 + 1);
		
		return zufallszahl;
	}
	
	
    public static void main(String[] args) {
        Auktionshaus auktionshaus = Auktionshaus.getInstance();
        Userverwaltung userverwaltung = auktionshaus.getUserverwaltung();

        // Benutzer hinzufügen
        userverwaltung.addUser(new User("Rainer Winkler", 0.12, 300.60));
        userverwaltung.addUser(new User("Walter Frosch", 0.84, 300.50));
        userverwaltung.addUser(new User("Robin Burkert", 0.04, 430.20));
        userverwaltung.addUser(new User("Deniz Undav", 0.14, 1000.10)); 
        userverwaltung.addUser(new User("Luca Bader", 0.04, 120.02)); 
        userverwaltung.addUser(new User("Lucas Ringer", 0.04, 450.20)); 
        userverwaltung.addUser(new User("Fabian Groß", 0.04, 780.21));
        userverwaltung.addUser(new User("Hansi Flick", 0.24, 9000.2)); 
        userverwaltung.addUser(new User("Jogi Löw", 0.14, 221.2));
        userverwaltung.addUser(new User("Julian Nagelsmann", 0.14, 20.20)); 
        userverwaltung.addUser(new User("Emre Abi", 0.08, 201.5));
        
        userverwaltung.assignRandomRolesDisjoint(3, 2);

        // Artikel erstellen
        Item item1 = new Item("Antike Vase", 100.0, 50.0);
        Item item2 = new Item("Döner", 300.0, 50.0);
        Item item3 = new Item("Zigaretten", 500.0, 50.0);

        // Auktion starten
      //Anzahl an zufälligen Bietern und Auktionatoren
        User auktionator = userverwaltung.getAuktionator();
        List<User> bieter = userverwaltung.getBieterList(); // 3 zufällige Bieter

        // Überprüfe, ob Bieter vorhanden sind
        if (bieter.isEmpty()) {
            System.out.println("Keine Bieter verfügbar!");
            return;
        }

        Auktion auktion1 = auktionshaus.getAuktionsverwaltung().erstelleAuktion(item1, auktionator, bieter);
        Auktion auktion2 = auktionshaus.getAuktionsverwaltung().erstelleAuktion(item2, auktionator, bieter);
        
        
        auktionshaus.getAuktionsverwaltung().starteAuktion(auktion1);
        auktionshaus.getAuktionsverwaltung().starteAuktion(auktion2);

        // Warte auf das Ende der Auktion
        try {
            Thread.sleep(9000); // Warte 5 Sekunden, um die Auktion zu beenden
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Bericht erstellen
        auktionshaus.erstelleBericht();
    }
}


//Interface 

//auktionsschritte einblenden (am ende in richtiger reihenfolge) in txt schreiben ablauf
//vereinfachen
