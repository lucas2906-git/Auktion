import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Verwaltet die Benutzer der Auktionsplattform. Diese Klasse ermöglicht das Hinzufügen von Benutzern,
 * die zufällige Zuweisung von Rollen (Bieter oder Auktionator) sowie das Abrufen von Benutzerlisten.
 */
public class Userverwaltung {
    private List<User> users = new ArrayList<>();
    private Random random = new Random();
    private List<User> bieterList;
    private List<User> auktionatorList;

    /**
     * Fügt einen neuen Benutzer zur Plattform hinzu.
     *
     * @param user Der hinzuzufügende Benutzer
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Weist den Benutzern zufällig disjunkte Rollen als Bieter oder Auktionatoren zu.
     * Kein Benutzer kann gleichzeitig Bieter und Auktionator sein.
     *
     * @param bieterAnzahl        Die Anzahl der Benutzer, die als Bieter zugewiesen werden sollen
     * @param auktionatorAnzahl   Die Anzahl der Benutzer, die als Auktionatoren zugewiesen werden sollen
     */
    public void assignRandomRolesDisjoint(int bieterAnzahl, int auktionatorAnzahl) {
        // Kopie der Liste mischen
        List<User> shuffled = new ArrayList<>(users);
        Collections.shuffle(shuffled, random);

        // Zuerst die Bieter auswählen
        int aktuelleBieterAnzahl = Math.min(bieterAnzahl, shuffled.size());
        this.bieterList = new ArrayList<>(shuffled.subList(0, aktuelleBieterAnzahl));
        for (User user : bieterList) {
            user.setBieter(true);
        }

        // Entferne die Bieter und mische erneut für die Auktionatoren
        shuffled.removeAll(bieterList);
        Collections.shuffle(shuffled, random);

        // Dann die Auktionatoren auswählen
        int aktuelleAuktionatorAnzahl = Math.min(auktionatorAnzahl, shuffled.size());
        this.auktionatorList = new ArrayList<>(shuffled.subList(0, aktuelleAuktionatorAnzahl));
        for (User user : auktionatorList) {
            user.setAuktionator(true);
        }
    }

    /**
     * Gibt die Liste der aktuell zugewiesenen Bieter zurück.
     *
     * @return Liste der Bieter
     */
    public List<User> getBieterList() {
        return bieterList;
    }

    /**
     * Gibt die Liste der aktuell zugewiesenen Auktionatoren zurück.
     *
     * @return Liste der Auktionatoren
     */
    public List<User> getAuktionatorList() {
        return auktionatorList;
    }

    /**
     * Gibt einen bestimmten Auktionator anhand des Index zurück.
     *
     * @param i Index in der Auktionatorliste
     * @return Der Auktionator an der angegebenen Position
     * @throws IndexOutOfBoundsException wenn der Index ungültig ist
     */
    public User getAuktionator(int i) {
        return auktionatorList.get(i);
    }
}
