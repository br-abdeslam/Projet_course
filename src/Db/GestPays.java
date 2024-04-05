package Db;

import myconnections.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class GestPays {

    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("Connexion établie.");
        int choix;
        do {
            System.out.println("1. Ajouter un pays\n2. Rechercher un pays\n3. Modifier un pays\n4. Supprimer un pays\n5. Afficher les pays\n6. Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    ajouterPays();
                    break;
                case 2:
                    recherchePays();
                    break;
                case 3:
                    modifierPays();
                    break;
                case 4:
                    supprimerPays();
                    break;
                case 5:afficherTousLesPays();
                    break;
                case 6:
                    System.out.println("Fin du programme.");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 6);
    }



    public void ajouterPays() {
        System.out.print("Sigle : ");
        String sigle = sc.nextLine();
        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Langue : ");
        String langue = sc.nextLine();

        String queryInsert = "INSERT INTO pays (sigle, nom, langue) VALUES (?, ?, ?)";
        String querySelect = "SELECT Id_pays FROM pays WHERE sigle = ? AND nom = ? AND langue = ?";

        try (PreparedStatement pstm1 = dbConnect.prepareStatement(queryInsert);
             PreparedStatement pstm2 = dbConnect.prepareStatement(querySelect)) {

            // Exécution de la requête d'insertion
            pstm1.setString(1, sigle);
            pstm1.setString(2, nom);
            pstm1.setString(3, langue);
            int n = pstm1.executeUpdate();
            System.out.println(n + " ligne insérée");

            // Récupération de l'ID du pays si l'insertion a réussi
            if (n == 1) {
                pstm2.setString(1, sigle);
                pstm2.setString(2, nom);
                pstm2.setString(3, langue);
                try (ResultSet rs = pstm2.executeQuery()) {
                    if (rs.next()) {
                        int idPays = rs.getInt(1);
                        System.out.println("Id_pays = " + idPays);
                    } else {
                        System.out.println("Pays introuvable");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
        }
    }


    public void recherchePays() {
        System.out.println("Rechercher par :\n1. Sigle\n2. Nom\n3. Langue");
        System.out.print("Votre choix : ");
        int choix = sc.nextInt();
        sc.nextLine(); // consommer le reste de la ligne

        String champRecherche;
        switch (choix) {
            case 1:
                champRecherche = "sigle";
                break;
            case 2:
                champRecherche = "nom";
                break;
            case 3:
                champRecherche = "langue";
                break;
            default:
                System.out.println("Choix invalide");
                return;
        }

        System.out.print("Entrez la valeur de recherche : ");
        String valeurRecherche = sc.nextLine();

        String query = "SELECT * FROM pays WHERE " + champRecherche + " LIKE ?";
        try (PreparedStatement stmt = dbConnect.prepareStatement(query)) {
            stmt.setString(1, "%" + valeurRecherche + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idPays = rs.getInt("Id_pays");
                String sigle = rs.getString("sigle");
                String nom = rs.getString("nom");
                String langue = rs.getString("langue");
                System.out.println("ID: " + idPays + ", Sigle: " + sigle + ", Nom: " + nom + ", Langue: " + langue);
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
        }
    }

    public void modifierPays() {
        System.out.print("ID du pays à modifier : ");
        int idPays = sc.nextInt();
        sc.nextLine(); // consommer le reste de la ligne

        System.out.print("Nouveau sigle : ");
        String nouveauSigle = sc.nextLine();

        System.out.print("Nouveau nom : ");
        String nouveauNom = sc.nextLine();

        System.out.print("Nouvelle langue : ");
        String nouvelleLangue = sc.nextLine();

        String query = "UPDATE pays SET sigle = ?, nom = ?, langue = ? WHERE Id_pays = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, nouveauSigle);
            pstm.setString(2, nouveauNom);
            pstm.setString(3, nouvelleLangue);
            pstm.setInt(4, idPays);

            int n = pstm.executeUpdate();
            if (n != 0) {
                System.out.println(n + " ligne mise à jour");
            } else {
                System.out.println("Record introuvable");
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
        }
    }



    public void supprimerPays() {
        System.out.print("ID du pays à supprimer : ");
        int idPays = sc.nextInt();
        sc.nextLine(); // consommer le reste de la ligne

        String query = "DELETE FROM pays WHERE Id_pays = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idPays);

            int n = pstm.executeUpdate();
            if (n != 0) {
                System.out.println(n + " ligne(s) supprimée(s)");
            } else {
                System.out.println("Record introuvable");
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
        }
    }

    public void afficherTousLesPays() {
        String query = "SELECT * FROM pays";

        try (Statement stmt = dbConnect.createStatement(); // Utilise la connexion existante
             ResultSet rs = stmt.executeQuery(query);) {

            while (rs.next()) {
                int idPays = rs.getInt("Id_pays");
                String sigle = rs.getString("sigle");
                String nom = rs.getString("nom");
                String langue = rs.getString("langue");
                System.out.println("ID: " + idPays + ", Sigle: " + sigle + ", Nom: " + nom + ", Langue: " + langue);
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
        }
    }


    public static void main(String[] args) {
        GestPays gestionnaire = new GestPays();
        gestionnaire.gestion();
    }
}
