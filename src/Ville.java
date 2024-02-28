
/**
 * @author Abdeslam
 * @see Course
 * @see Pays
 */
public class Ville {

    /**
     * nom du ville
     */
    private String nom;
    /**
     * latitude de ville
     */
    private double latitude;
    /**
     * lonngitude de ville
     */
    private double longitude;
    /**
     * Pays de ville
     */
    private Pays pays;

    /**
     * constructeur paramétré
     * @param nom nom du ville
     * @param latitude latitude de ville
     * @param longitude longitude de ville
     * @param pays pays de ville
     */
    public Ville(String nom, double latitude, double longitude, Pays pays) {
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pays = pays;
    }

    /**
     * getter nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter
     * @param nom
     */

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter latitude
     * @return latitude
     */

    public double getLatitude() {
        return latitude;
    }

    /**
     * setter latitude
     * @param latitude
     */

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * getter longitude
     * @return longitude
     */

    public double getLongitude() {
        return longitude;
    }

    /**
     * setter longitude
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * getter pays
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
        return "Ville{" +
                "nom='" + nom + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", pays=" + pays +
                '}';
    }
}
