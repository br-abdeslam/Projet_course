import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import static java.time.LocalDate.*;

public class Main {
    public static void main(String[] args) {
        // Création de pays
        Pays france = new Pays("FR","France","Francais");
        Pays italie = new Pays("IT","Italie","Italian");
        Pays belgique = new Pays("BE","Belgique","Francais/dutch");

        // Création de la ville et de la course
        Ville ville = new Ville("Paris", 12.33,14.55,france);
        Course course = new Course("Grand Prix de France", new BigDecimal(1000000), new Date(), 500, ville);

        // Création des pilotes
        Pilote pilote1 = new Pilote("AAA","Jean","Dupont","12-01-2000",france);
        Pilote pilote2 = new Pilote("BBB","thomas","tonda","1-06-2002",italie);
        Pilote pilote3 = new Pilote("CCC","leroy","sami","13-10-2001",belgique);

        // Création des classements
        Classement classement1 = new Classement(0, new BigDecimal("0"), pilote1);
        Classement classement2 = new Classement(0, new BigDecimal("0"), pilote2);
        Classement classement3 = new Classement(0, new BigDecimal("0"), pilote3);

        // Ajout des pilotes à la course
        course.addCoureur(classement1);
        course.addCoureur(classement2);
        course.addCoureur(classement3);

        //enregistre les resultat de course
        course.resultat(pilote1,1,new BigDecimal(500000));
        course.resultat(pilote2,3,new BigDecimal(100000));
        course.resultat(pilote3,2,new BigDecimal(300000));


        Pilote vainqueur = course.getVainqueur();

        // Liste des coureurs
        System.out.println("\nListe des coureurs et leur gain : \n" + course.listeCoureursPlaceGain());

        //vainqueur du course
        if (vainqueur != null) {
            System.out.println("Le vainqueur est : " + vainqueur.getNom());
        } else {
            System.out.println("Aucun vainqueur n'a été déterminé pour le moment.");
        }

        //gain total de course
        System.out.println("\nGain total de la course : " + course.gainTotal());



        //Liste total de course
        System.out.println("\nListe des pays des coureurs : " + course.listePaysCoureurs());


        //les coureurs du pays France
        System.out.println("\nLes coureurs du pays France : " + course.listeCoureursDuPays(france));

        // Modification d'un résultat
        Pilote piloteConflit = course.modif(pilote2, 1, new BigDecimal(100000));

        // Gestion du conflit
        if (piloteConflit != null) {
            System.out.println("Conflit détecté : La place 2 est déjà occupée par " + piloteConflit.getNom() + ".");
            // Mettre le pilote en conflit à la place 0 pour résoudre le conflit
            course.modif(piloteConflit, 0, new BigDecimal(0));
            System.out.println(piloteConflit.getNom() + " a été déplacé pour résoudre le conflit.");
            // Réessayer la modification initiale après avoir résolu le conflit
            course.modif(pilote2, 2, new BigDecimal(100000));
        } else {
            System.out.println("La modification de la place a été effectuée avec succès.");
        }

        // affichage de classement aprés la moification
        System.out.println("\n\nListe des coureurs après modification : \n" + course.listeCoureursPlaceGain());


        // Vérification si le classement est complet
        System.out.println("\nLe classement est-il complet ? " + course.classementComplet());

        // Suppression d'un pilote
        course.supCoureur(pilote1);
        System.out.println("\nListe des coureurs après suppression : \n" + course.listeCoureursPlaceGain());

    }
}