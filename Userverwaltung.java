import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Verwaltet die Benutzer der Plattform.
 */
public class Userverwaltung {
    private List<User> users = new ArrayList<>();
    private Random random = new Random();
    private List<User> bieterList;
    private List<User> auktionatorList;

    public void addUser(User user) {
        users.add(user);
    }

    public void assignRandomRolesDisjoint(int bieterAnzahl, int auktionatorAnzahl) {
        // Kopie der Liste mischen
        List<User> shuffled = new ArrayList<>(users);
        Collections.shuffle(shuffled, random);
        
        // Zuerst die Bieter auswählen:
        int aktuelleBieterAnzahl = Math.min(bieterAnzahl, shuffled.size());
        this.bieterList = new ArrayList<>(shuffled.subList(0, aktuelleBieterAnzahl));
        for (User user : bieterList) {
            user.setBieter(true);
        }
         
        // Entferne die schon zugewiesenen Bieter
        shuffled.removeAll(bieterList);
        Collections.shuffle(shuffled, random);
        
        // Wähle nun die Auktionatoren aus dem Rest:
        int aktuelleAuktionatorAnzahl = Math.min(auktionatorAnzahl, shuffled.size());
        this.auktionatorList = new ArrayList<>(shuffled.subList(0, aktuelleAuktionatorAnzahl));
        for (User user : auktionatorList) {
            user.setAuktionator(true);
        }
    }
    

    

    // Getter-Methode für die Bieter-Liste
    public List<User> getBieterList() {
        return bieterList;
    }

    // Getter-Methode für die Auktionator-Liste
    public List<User> getAuktionatorList() {
        return auktionatorList;
    }
    public User getAuktionator(int i) {
    	
    	return auktionatorList.get(i);
   
    }

}