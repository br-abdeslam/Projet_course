import java.util.ArrayList;
import java.util.List;

/**
 * @author Abdeslam
 * @see Pilote
 * @see Ville
 */
public class Pays {

    /**
     * Sigle de pays
     */
    private String sigle;
    /**
     * nom du pays
     */
    private String nom;
    /**
     * langue du pays
     */
    private String langue;


    /**
     * liste des pilotes
     */
    private List<Pilote> listeDesPilotes ;



    /**
     * constructeur paramétré
     * @param sigle sigle du pays
     * @param nom nom du pays
     * @param langue langue du pays
     */
    // Constructor
    public Pays(String sigle, String nom, String langue) {
        this.sigle = sigle;
        this.nom = nom;
        this.langue = langue;
        this.listeDesPilotes = new ArrayList<>();
    }

    /**
     * getter sigle
     * @return sigle
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * setter sigle
     * @param sigle
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * getter nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter langue
     * @return langue
     */
    public String getLangue() {
        return langue;
    }

    /**
     * setter langue
     * @param langue
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    // Méthode pour les pilotes dans la liste
    public void ajouterPilote(Pilote pilote) {
        this.listeDesPilotes.add(pilote);
    }

    // Méthode pour les retire les pilotes depuis la liste
    public void retirerPilote(Pilote pilote) {
        this.listeDesPilotes.remove(pilote);
    }


    /**
     * getter ListeDesPilotes
     * @return ListeDesPilotes
     */
    public List<Pilote> getListeDesPilotes() {
        return this.listeDesPilotes;
    }
    /**
     * setter ListeDesPilotes
     * @return ListeDesPilotes
     */
    public void setListeDesPilotes(List<Pilote> listeDesPilotes) {
        this.listeDesPilotes = listeDesPilotes;
    }

    /**
     * méthode toString
     * @return informations complètes
     */
    @Override
    public String toString() {
        return "Sigle: " + sigle + " Nom: " + nom + " langue: " + langue +"\n" ;
    }
}
