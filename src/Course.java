import java.math.BigDecimal;
import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Course est une classe
 *
 * @author Abdeslam bourzine
 * @version 1.0
 * @see Pilote
 * @see Ville
 */
public class Course {
    /**
     * nom de la course
     */
    private String nom;
    /**
     *  Prix de course a gagner
     */
    private BigDecimal priceMoney;
    /**
     * Date de course
     */
    private Date dateCourse;
    /**
     * Distance total de course
     */
    private int km;
    /**
     *Ville où le course va se dérouler
     */
    private Ville ville;
    /**
     * Liste de classement, place et gain de chaque pilote participant
     */
    private List<Classement> listeCoureursPlaceGain;

    private Set<String> matriculesUniques;

    /**
     * constructeur parametré
     * @param nom nom de la course
     * @param priceMoney la valeur du prix de course a gagner
     * @param dateCourse la date de course
     * @param km la distance de course en km
     * @param ville la ville où le course va se dérouler
     */
    public Course(String nom, BigDecimal priceMoney, Date dateCourse, int km, Ville ville) {
        this.nom = nom;
        this.priceMoney = priceMoney;
        this.dateCourse = dateCourse;
        this.km = km;
        this.ville = ville;
        this.listeCoureursPlaceGain = new ArrayList<>();
        this.matriculesUniques = new HashSet<>();
    }

    /**
     * methode qui recois en parametre un pilote, puis nous verifions que le pilote n'est pas deja present et si ca le cas on l'ajoute a la liste listeCoureursPlaceGain
     * @param pilote
     */
    public void addCoureur(Pilote pilote) {
        if (!matriculesUniques.contains(pilote.getMatricule())) {
            matriculesUniques.add(pilote.getMatricule());
            Classement nouveauClassement = new Classement(0, BigDecimal.ZERO, pilote);
            listeCoureursPlaceGain.add(nouveauClassement);
        }
    }

    /**
     * Supprime un pilote de la liste des classement de pilotes
     * @param pilote
     */
    public void supCoureur(Pilote pilote) {
        Classement toRemove = null;
        for (Classement classement : listeCoureursPlaceGain) {
            if (classement.getPilote().equals(pilote)) {
                toRemove = classement;
                break; // Trouvez le premier classement correspondant et sortez de la boucle
            }
        }
        if (toRemove != null) {
            listeCoureursPlaceGain.remove(toRemove); // Supprimez le classement trouvé de la liste
        }
    }

    /**
     * methode enregistre le résultat d'un pilote
     * @param pilote
     * @param place
     * @param gain
     */
    public void resultat(Pilote pilote, int place, BigDecimal gain) {
        boolean piloteTrouve = false;
        for (Classement classement : listeCoureursPlaceGain) {
            if (classement.getPilote().equals(pilote)) {
                classement.setPlace(place); // Mise à jour de la place avec la valeur spécifiée
                classement.setGain(gain);   // Mise à jour du gain avec la valeur spécifiée
                piloteTrouve = true;
                break; // Sortie de la boucle après la mise à jour
            }
        }
    }

    /**
     * methode pour modifie le résultat d'un pilote
     * @param pilote
     * @param nouvellePlace
     * @param gain
     */
    public Pilote modif(Pilote pilote, int nouvellePlace, BigDecimal gain) {
        // Vérifier si le pilote existe dans la course
        Classement classementPilote = null;
        for (Classement classement : listeCoureursPlaceGain) {
            if (classement.getPilote().equals(pilote)) {
                classementPilote = classement;
                break;
            }
        }

        // Si le pilote n'existe pas, retourner null
        if (classementPilote == null) {
            return null;
        }

        // Vérifier si la nouvelle place est déjà prise
        for (Classement classement : listeCoureursPlaceGain) {
            if (classement.getPlace() == nouvellePlace) {
                // Retourner le pilote qui occupe déjà cette place
                return classement.getPilote();
            }
        }

        // Aucun conflit, on met à jour la place et le gain du pilote
        classementPilote.setPlace(nouvellePlace);
        classementPilote.setGain(gain);
        return null; // Retourner null pour indiquer que la modification a réussi sans conflit
    }



    /**
     * methode pour afficher les coureurs avec leur place et gain
     * @return listeCoureursPlaceGain
     */
    public List<Classement> listeCoureursPlaceGain() {
        return listeCoureursPlaceGain;
    }

    /**
     * methode pour calculer le gain total distribué dans la course
     * @return total
     */
    public BigDecimal gainTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Classement classement : listeCoureursPlaceGain) {
            total = total.add(classement.getGain());
        }
        return total;
    }

    /**
     * méthode pour obtenir une liste des pays uniques des coureurs
     * @return paysListe
     */
    public List<String> listePaysCoureurs() {
        List<String> paysListe = new ArrayList<>();
        for (Classement classement : listeCoureursPlaceGain) {
            Pilote pilote = classement.getPilote();
            String pays = pilote.getPays().getNom(); // Supposons que getPays() retourne une String
            if (!paysListe.contains(pays)) { // Vérifie si le pays est déjà dans la liste
                paysListe.add(pays);
            }
        }
        return paysListe;
    }

    /**
     * methode pour trouver le pilote vainqueur de la course
     * @return pilote
     */
    public Pilote getVainqueur() {
        for (Classement classement : listeCoureursPlaceGain) {
            if (classement.getPlace() == 1) {
                return classement.getPilote();
            }
        }
        return null; // Retourne null si aucun coureur n'est à la place 1
    }

    /**
     * methode pour trouver les pilotes d'un pays spécifique
     * @param nomPays
     * @return pilotesDuPays
     */
    public List<Pilote> listeCoureursDuPays(String nomPays) {
        List<Pilote> pilotesDuPays = new ArrayList<>();
        for (Classement classement : listeCoureursPlaceGain) {
            Pilote pilote = classement.getPilote();
            if (pilote.getPays().getNom().equals(nomPays)) {
                pilotesDuPays.add(pilote);
            }
        }
        return pilotesDuPays;
    }



    /**
     * methode pour vérifie si tous les coureurs ont une place assignée
     * @return boolean
     */

    public boolean classementComplet() {
        for (Classement classement : listeCoureursPlaceGain) {
            if (classement.getPlace() == 0) {
                return false; // Dès qu'on trouve un coureur sans place, on retourne false
            }
        }
        return true; // Si tous les coureurs ont une place, le classement est complet
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
     * getter priceMoney
     * @return priceMoney
     */
    public BigDecimal getPriceMoney() {
        return priceMoney;
    }

    /**
     * setter priceMoney
     * @param priceMoney
     */

    public void setPriceMoney(BigDecimal priceMoney) {
        this.priceMoney = priceMoney;
    }

    /**
     * getter dateCourse
     * @return dateCourse
     */

    public Date getDateCourse() {
        return dateCourse;
    }

    /**
     * setter dateCourse
     * @param dateCourse
     */

    public void setDateCourse(Date dateCourse) {
        this.dateCourse = dateCourse;
    }

    /**
     * getter km
     * @return km
     */
    public int getKm() {
        return km;
    }

    /**
     * setter km
     * @param km
     */
    public void setKm(int km) {
        this.km = km;
    }

    /**
     * getter ville
     * @return ville
     */
    public Ville getVille() {
        return ville;
    }

    /**
     * setter ville
     * @param ville
     */

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    /**
     * getter listeCoureursPlaceGain
     * @return listeCoureursPlaceGain
     */

    public List<Classement> getListeCoureursPlaceGain() {
        return listeCoureursPlaceGain;
    }

    /**
     * setter listeCoureursPlaceGain
     * @param listeCoureursPlaceGain
     */
    public void setListeCoureursPlaceGain(List<Classement> listeCoureursPlaceGain) {
        this.listeCoureursPlaceGain = listeCoureursPlaceGain;
    }

    /**
     * methide toString
     * @return informations complètes
     */
    @Override
    public String toString() {
        return "Course: " + " Nom de course:  " + nom + "priceMoney= " + priceMoney + "\tDate de course=" + dateCourse + "\tKm total= " + km + "\tVille=" + ville + "\tlisteCoureursPlaceGain=" + listeCoureursPlaceGain+ "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return km == course.km && Objects.equals(nom, course.nom) && Objects.equals(priceMoney, course.priceMoney) && Objects.equals(dateCourse, course.dateCourse) && Objects.equals(ville, course.ville) && Objects.equals(listeCoureursPlaceGain, course.listeCoureursPlaceGain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, priceMoney, dateCourse, km, ville, listeCoureursPlaceGain);
    }
}
