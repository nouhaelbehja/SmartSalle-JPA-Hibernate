
package com.example.jpa.dao;

import com.example.jpa.entities.Utilisateur;
import javax.persistence.EntityManager;
import java.util.List;

public class UtilisateurDao extends GenericDao<Utilisateur> {

    public UtilisateurDao(EntityManager em) {
        super(Utilisateur.class, em);
    }

    // Méthode demandée par le TP
    public List<Utilisateur> findBySalleNom(String nomSalle) {
        return em.createQuery(
                        "SELECT u FROM Utilisateur u WHERE u.salle.nom = :nomSalle",
                        Utilisateur.class
                )
                .setParameter("nomSalle", nomSalle)
                .getResultList();
    }

    // SmartSalle : rechercher par département
    public List<Utilisateur> findByDepartement(String departement) {
        return em.createQuery(
                        "SELECT u FROM Utilisateur u WHERE u.departement = :dep",
                        Utilisateur.class
                )
                .setParameter("dep", departement)
                .getResultList();
    }

    // SmartSalle : rechercher les utilisateurs actifs
    public List<Utilisateur> findActifs() {
        return em.createQuery(
                "SELECT u FROM Utilisateur u WHERE u.actif = true",
                Utilisateur.class
        ).getResultList();
    }

    // SmartSalle : rechercher par rôle
    public List<Utilisateur> findByRole(String role) {
        return em.createQuery(
                        "SELECT u FROM Utilisateur u WHERE u.role = :role",
                        Utilisateur.class
                )
                .setParameter("role", role)
                .getResultList();
    }

    // SmartSalle : rechercher un utilisateur par email
    public Utilisateur findByEmail(String email) {
        List<Utilisateur> resultats = em.createQuery(
                        "SELECT u FROM Utilisateur u WHERE u.email = :email",
                        Utilisateur.class
                )
                .setParameter("email", email)
                .getResultList();

        return resultats.isEmpty() ? null : resultats.get(0);
    }

    // TP : rechercher les utilisateurs dont le nom contient un texte
    public List<Utilisateur> findByNomContenant(String nom) {
        return em.createQuery(
                        "SELECT u FROM Utilisateur u WHERE u.nom LIKE :pattern",
                        Utilisateur.class
                )
                .setParameter("pattern", "%" + nom + "%")
                .getResultList();
    }

    // SmartSalle : utilisateurs actifs dans un département
    public List<Utilisateur> findActifsParDepartement(String departement) {
        return em.createQuery(
                        "SELECT u FROM Utilisateur u " +
                                "WHERE u.departement = :dep AND u.actif = true",
                        Utilisateur.class
                )
                .setParameter("dep", departement)
                .getResultList();
    }

}
