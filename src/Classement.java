import java.math.BigDecimal;

/**
 *
 * classe classement pour la gestion d'un classement
 * @author abdeslam
 * @see Course
 * @see Pilote
 *
 */



public class Classement {

    /**
     * place obtenu dans la course
     */
    private int place;
    /**
     * Montant de gain
     */
    private BigDecimal gain;
    /**
     * pilote qui a participé au course
     */
    private Pilote pilote;

    /**
     * constructeur paramétré
     * @param place place du pilote realisé dans le course
     * @param gain gain de course
     * @param pilote pilote qui a participé au course
     */
    public Classement(int place, BigDecimal gain, Pilote pilote) {
        this.place = place;
        this.gain = gain;
        this.pilote = pilote;
    }

    /**
     * getter place
     * @return place
     */
    public int getPlace() {
        return place;
    }

    /**
     * setter place
     * @param place
     */
    public void setPlace(int place) {
        this.place = place;
    }

    /**
     * getter gain
     * @return gain
     */
    public BigDecimal getGain() {
        return gain;
    }

    /**
     * setter gain
     * @param gain
     */

    public void setGain(BigDecimal gain) {
        this.gain = gain;
    }

    /**
     * getter pilote
     * @return
     */
    public Pilote getPilote() {
        return pilote;
    }

    /**
     * setter pilote
     * @param pilote
     */
    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    /**
     * méthode toString
     * @return informations complètes
     */
    @Override
    public String toString() {
        return "Classement : \tPlace= " + place + "\tGain= " + gain + "\tPilote: " + pilote;
    }
}
