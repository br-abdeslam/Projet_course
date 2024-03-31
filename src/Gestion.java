import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class Gestion {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choix;

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
        Classement classement1 = new Classement(1, new BigDecimal("10000"), pilote1);
        Classement classement2 = new Classement(2, new BigDecimal("7500"), pilote2);
        Classement classement3 = new Classement(3, new BigDecimal("5000"), pilote3);

        // Ajout des pilotes à la course
        course.addCoureur(pilote1);
        course.addCoureur(pilote2);
        course.addCoureur(pilote3);

        //enregistre les resultat de course
        course.resultat(pilote1,1,new BigDecimal(500000));
        course.resultat(pilote2,3,new BigDecimal(100000));
        course.resultat(pilote3,2,new BigDecimal(300000));

        do{
            System.out.println("1- Afficher vainqueur \n2- Liste des coureurs \n3- Gain tottal de course \n4- Liste des pays de courreurs \n5- Liste des courreurs d'un pays \n6- Modification d'un resultat \n7- Vérification si le classement est complet \n8- Supprimer un pilote \n0- Quitter");
            System.out.print("choix: ");
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix){
                case 1:
                    Pilote vainqueur = course.getVainqueur();
                    if (vainqueur != null) {
                        System.out.println("Le vainqueur est : " + vainqueur.getNom());
                    } else {
                        System.out.println("Aucun vainqueur n'a été déterminé pour le moment.");
                    }
                    ;break;
                case 2:
                    System.out.println("\nListe des coureurs et leur gain : \n" + course.listeCoureursPlaceGain());
                    break;
                case 3:
                    System.out.println("\nGain total de la course : " + course.gainTotal());
                    break;
                case 4:
                    System.out.println("\nListe des pays des coureurs : " + course.listePaysCoureurs());
                    ;break;
                case 5:
                    Pays pays;
                    String nomPays;
                    System.out.print("Pays : ");
                    nomPays=sc.nextLine();
                    pays= new Pays(null,nomPays,null);
                    System.out.println("\nLes coureurs du pays "+nomPays+ " : " + course.listeCoureursDuPays(nomPays));
                    ;break;
                case 6:
                    //AFFICHAGE DE LISTE DES COURREURS
                    System.out.println("Liste des coureurs actuels : \n" + course.listeCoureursPlaceGain());

                    System.out.print("Entrez le matricule du pilote à modifier : ");
                    String matricule = sc.nextLine();

                    // Trouver le pilote par son matricule
                    Pilote piloteSelectionne = null;
                    for (Classement classement : course.getListeCoureursPlaceGain()) {
                        if (classement.getPilote().getMatricule().equals(matricule)) {
                            piloteSelectionne = classement.getPilote();
                            break;
                        }
                    }

                    if (piloteSelectionne == null) {
                        System.out.println("Pilote non trouvé.");
                        return; // Sortie de la méthode si le pilote n'est pas trouvé
                    }

                    System.out.print("Entrez la nouvelle place pour le pilote : ");
                    int nouvellePlace = sc.nextInt();

                    System.out.print("Entrez le nouveau gain pour le pilote : ");
                    BigDecimal gain = sc.nextBigDecimal();

                    // Effectuer la modification
                    Pilote piloteConflit = course.modif(piloteSelectionne, nouvellePlace, gain);

                    // Gestion du conflit
                    if (piloteConflit != null) {
                        System.out.println("Conflit détecté : La place " + nouvellePlace + " est déjà occupée par " + piloteConflit.getNom() + ".");
                    } else {
                        System.out.println("La modification de la place a été effectuée avec succès.");
                    }

                    // Affichage de classement après la modification
                    System.out.println("\n\nListe des coureurs après modification : \n" + course.listeCoureursPlaceGain());

                    ;break;
                case 7:
                    System.out.println("\nLe classement est-il complet ? " + course.classementComplet());
                    ;break;
                case 8:
                    //AFFICHAGE DE LISTE DES COURREURS
                    System.out.println("Liste des coureurs actuels : \n" + course.listeCoureursPlaceGain());
                    System.out.print("Entrez le matricule du pilote à supprimer : ");
                    String mat = sc.nextLine();
                    // Trouver le pilote par son matricule
                    Pilote piloteSelec = null;
                    for (Classement classement : course.getListeCoureursPlaceGain()) {
                        if (classement.getPilote().getMatricule().equals(mat)) {
                            piloteSelectionne = classement.getPilote();
                            break;
                        }
                    }
                    if (piloteSelec == null) {
                        System.out.println("Pilote non trouvé.");
                        return; // Sortie de la méthode si le pilote n'est pas trouvé
                    }
                    course.supCoureur(pilote1);
                    System.out.println("\nListe des coureurs après suppression : \n" + course.listeCoureursPlaceGain());
                    ;break;

                default:
                    System.out.println("FIN");
            }
        }while(choix!=0);










    }
}