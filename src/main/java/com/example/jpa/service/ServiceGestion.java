
package com.example.jpa.service;

import com.example.jpa.dao.SalleDao;
import com.example.jpa.dao.UtilisateurDao;
import com.example.jpa.entities.Salle;
import com.example.jpa.entities.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ServiceGestion {

    private EntityManagerFactory emf;
    private EntityManager em;
    private SalleDao salleDao;
    private UtilisateurDao utilisateurDao;

    public ServiceGestion() {
        emf = Persistence.createEntityManagerFactory("gestionPU");
        em = emf.createEntityManager();
        salleDao = new SalleDao(em);
        utilisateurDao = new UtilisateurDao(em);
    }

    // Fermer les ressources
    public void fermer() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    // ===== GESTION DES SALLES =====

    public void ajouterSalle(Salle salle) {
        salleDao.create(salle);
    }

    public List<Salle> listerSalles() {
        return salleDao.findAll();
    }

    // SmartSalle : salles disponibles
    public List<Salle> sallesDisponibles() {
        return salleDao.findDisponibles();
    }

    // SmartSalle : salles avec projecteur
    public List<Salle> sallesAvecProjecteur() {
        return salleDao.findAvecProjecteur();
    }

    // SmartSalle : rechercher selon la capacité
    public List<Salle> sallesParCapacite(int capaciteMin) {
        return salleDao.findByCapaciteMin(capaciteMin);
    }

    // SmartSalle : salles adaptées aux réunions
    public List<Salle> rechercherSallesAdaptees(int capaciteMin) {
        return salleDao.findSallesAdaptees(capaciteMin);
    }

    // SmartSalle : nombre total de salles
    public long nombreSalles() {
        return salleDao.count();
    }

    // ===== GESTION DES UTILISATEURS =====

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurDao.create(utilisateur);
    }

    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurDao.findAll();
    }

    public List<Utilisateur> utilisateursParSalle(String nomSalle) {
        return utilisateurDao.findBySalleNom(nomSalle);
    }

    // SmartSalle : utilisateurs actifs
    public List<Utilisateur> utilisateursActifs() {
        return utilisateurDao.findActifs();
    }

    // SmartSalle : recherche par département
    public List<Utilisateur> utilisateursParDepartement(String departement) {
        return utilisateurDao.findByDepartement(departement);
    }

    // SmartSalle : recherche par email
    public Utilisateur rechercherUtilisateurParEmail(String email) {
        return utilisateurDao.findByEmail(email);
    }

    // SmartSalle : nombre total d'utilisateurs
    public long nombreUtilisateurs() {
        return utilisateurDao.count();
    }

    // Rechercher les grandes salles
    public List<Salle> rechercherGrandesSalles(int capacite) {
        return salleDao.findGrandesSalles(capacite);
    }

    // Rechercher les utilisateurs par nom
    public List<Utilisateur> rechercherUtilisateursParNom(String nom) {
        return utilisateurDao.findByNomContenant(nom);
    }

    // Rechercher les salles accessibles PMR
    public List<Salle> sallesAccessiblesPMR() {
        return salleDao.findAccessiblesPMR();
    }

    // Rechercher les salles accessibles selon la capacité
    public List<Salle> rechercherSallesAccessibles(int capacite) {
        return salleDao.findSallesAccessibles(capacite);
    }

    // Rechercher les utilisateurs actifs d'un département
    public List<Utilisateur> utilisateursActifsParDepartement(String dep) {
        return utilisateurDao.findActifsParDepartement(dep);
    }

}
