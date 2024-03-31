package Db;

import myconnections.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class GestPays {

    private final Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public GestPays() {
        this.dbConnect = DBConnection.getConnection();
        if (this.dbConnect == null) {
            System.err.println("La connexion n'a pas pu être établie. Vérifiez vos paramètres de connexion.");
            System.exit(1);
        }
    }

    public void ajout() {
        System.out.print("Entrez le sigle du pays : ");
        String sigle = sc.nextLine();
        System.out.print("Entrez le nom du pays : ");
        String nom = sc.nextLine();
        System.out.print("Entrez la langue principale du pays : ");
        String langue = sc.nextLine();

        String sql = "INSERT INTO Pays (sigle, nom, langue) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
            pstmt.setString(1, sigle);
            pstmt.setString(2, nom);
            pstmt.setString(3, langue);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Le pays a été ajouté avec succès.");
            } else {
                System.out.println("Aucune ligne ajoutée à la base de données.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du pays : " + e.getMessage());
        }
    }

    public void recherche() {
        System.out.print("Entrez le sigle du pays à rechercher : ");
        String sigle = sc.nextLine();

        String sql = "SELECT * FROM Pays WHERE sigle = ?";
        try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
            pstmt.setString(1, sigle);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String langue = rs.getString("langue");
                System.out.printf("Sigle : %s, Nom : %s, Langue : %s%n", sigle, nom, langue);
            } else {
                System.out.println("Aucun pays trouvé avec le sigle " + sigle);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche : " + e.getMessage());
        }
    }

    public void modification() {
        System.out.print("Entrez le sigle du pays à modifier : ");
        String sigle = sc.nextLine();
        System.out.print("Entrez le nouveau nom du pays : ");
        String nom = sc.nextLine();
        System.out.print("Entrez la nouvelle langue du pays : ");
        String langue = sc.nextLine();

        String sql = "UPDATE Pays SET nom = ?, langue = ? WHERE sigle = ?";
        try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, langue);
            pstmt.setString(3, sigle);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Le pays a été mis à jour avec succès.");
            } else {
                System.out.println("Aucun pays trouvé avec le sigle " + sigle + " ou aucune modification nécessaire.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification : " + e.getMessage());
        }
    }

    public void suppression() {
        System.out.print("Entrez le sigle du pays à supprimer : ");
        String sigle = sc.nextLine();

        String sql = "DELETE FROM Pays WHERE sigle = ?";
        try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
            pstmt.setString(1, sigle);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Le pays a été supprimé avec succès.");
            } else {
                System.out.println("Aucun pays trouvé avec le sigle " + sigle);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GestPays gestionPays = new GestPays();

        gestionPays.ajout();
        gestionPays.recherche();
        gestionPays.modification();
        gestionPays.suppression();
    }
}
