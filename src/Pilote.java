import java.util.Date;

/**
 * classe pilote de gestion d'un pilote
 * @author Abdeslam
 * @see Course
 * @see Pays
 */

public class Pilote {
    /**
     * matricule unique de chaque pilote
     */
    private String matricule;
    /**
     * nom de pilote
     */
    private String nom;
    /**
     * prénom de pilote
     */
    private String prenom;
    /**
     * Date de naissance de pilote
     */
    private String dateNaiss;
    /**
     * Pays de pilote
     */
    private Pays pays;

    /**
     * constructeur paramétré
     * @param matricule matricule unique de pilote
     * @param nom nom de pilote
     * @param prenom prenom de pilote
     * @param dateNaiss date de naissance de pilote
     * @param pays pays de naissance de pilote
     */
    public Pilote(String matricule, String nom, String prenom, String dateNaiss, Pays pays) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.pays = pays;
    }

    /**
     * getter matricule
     * @return matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * setter matricule
     * @param matricule
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
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
     * getter prenom
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * setter prenom
     * @param prenom
     */

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * getter dateNaiss
     * @return dateNaiss
     */

    public String getDateNaiss() {
        return dateNaiss;
    }

    /**
     * setter dateNaiss
     * @param dateNaiss
     */
    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    /**
     * getter Pays
     * @return pays
     */

    public Pays getPays() {
        return pays;
    }

    /**
     * setter pays
     * @param pays
     */

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * méthode toString
     * @return informations complètes
     */
    @Override
    public String toString() {
        return "\tMatricule: " + matricule + "\tNom: " + nom + "\tPrenom: " + prenom + "\tDate de Naissance: " + dateNaiss + "\tPays: " + pays;
    }
}
