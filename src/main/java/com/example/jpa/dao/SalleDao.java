
package com.example.jpa.dao;

import com.example.jpa.entities.Salle;
import javax.persistence.EntityManager;
import java.util.List;

public class SalleDao extends GenericDao<Salle> {

    public SalleDao(EntityManager em) {
        super(Salle.class, em);
    }

    // Méthode demandée par le TP
    public List<Salle> findByCapaciteMin(int capaciteMin) {
        return em.createQuery(
                        "SELECT s FROM Salle s WHERE s.capacite >= :capaciteMin",
                        Salle.class
                )
                .setParameter("capaciteMin", capaciteMin)
                .getResultList();
    }

    // SmartSalle : rechercher les salles disponibles
    public List<Salle> findDisponibles() {
        return em.createQuery(
                "SELECT s FROM Salle s WHERE s.disponible = true",
                Salle.class
        ).getResultList();
    }

    // SmartSalle : rechercher les salles avec projecteur
    public List<Salle> findAvecProjecteur() {
        return em.createQuery(
                "SELECT s FROM Salle s WHERE s.projecteur = true",
                Salle.class
        ).getResultList();
    }

    // SmartSalle : rechercher par type de salle
    public List<Salle> findByType(String type) {
        return em.createQuery(
                        "SELECT s FROM Salle s WHERE s.typeSalle = :type",
                        Salle.class
                )
                .setParameter("type", type)
                .getResultList();
    }

    // SmartSalle : recherche multicritère
    public List<Salle> findSallesAdaptees(int capaciteMin) {
        return em.createQuery(
                        "SELECT s FROM Salle s " +
                                "WHERE s.capacite >= :capacite " +
                                "AND s.disponible = true " +
                                "AND s.projecteur = true",
                        Salle.class
                )
                .setParameter("capacite", capaciteMin)
                .getResultList();
    }

    // TP : salles dont la capacité est supérieure à une valeur
    public List<Salle> findGrandesSalles(int minCapacite) {
        return em.createQuery(
                        "SELECT s FROM Salle s WHERE s.capacite > :minCapacite",
                        Salle.class
                )
                .setParameter("minCapacite", minCapacite)
                .getResultList();
    }

    // SmartSalle : salles accessibles PMR
    public List<Salle> findAccessiblesPMR() {
        return em.createQuery(
                "SELECT s FROM Salle s WHERE s.accessiblePMR = true",
                Salle.class
        ).getResultList();
    }

    // SmartSalle : salles disponibles selon capacité et accessibilité
    public List<Salle> findSallesAccessibles(int capaciteMin) {
        return em.createQuery(
                        "SELECT s FROM Salle s " +
                                "WHERE s.capacite >= :capacite " +
                                "AND s.disponible = true " +
                                "AND s.accessiblePMR = true",
                        Salle.class
                )
                .setParameter("capacite", capaciteMin)
                .getResultList();
    }

}
