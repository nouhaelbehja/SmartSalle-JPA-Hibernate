
package com.example.jpa.entities;

import javax.persistence.*;

@Entity
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private int capacite;

    // Champs supplémentaires pour SmartSalle
    private String localisation;
    private String typeSalle;
    private boolean disponible;
    private boolean projecteur;
    private boolean accessiblePMR;

    // Constructeur obligatoire JPA
    public Salle() {}

    // Constructeur principal
    public Salle(String nom, int capacite) {
        this.nom = nom;
        this.capacite = capacite;
        this.disponible = true;
    }

    // Constructeur personnalisé
    public Salle(String nom, int capacite,
                 String localisation, String typeSalle,
                 boolean disponible, boolean projecteur,
                 boolean accessiblePMR) {
        this.nom = nom;
        this.capacite = capacite;
        this.localisation = localisation;
        this.typeSalle = typeSalle;
        this.disponible = disponible;
        this.projecteur = projecteur;
        this.accessiblePMR = accessiblePMR;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getTypeSalle() {
        return typeSalle;
    }

    public void setTypeSalle(String typeSalle) {
        this.typeSalle = typeSalle;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isProjecteur() {
        return projecteur;
    }

    public void setProjecteur(boolean projecteur) {
        this.projecteur = projecteur;
    }

    public boolean isAccessiblePMR() {
        return accessiblePMR;
    }

    public void setAccessiblePMR(boolean accessiblePMR) {
        this.accessiblePMR = accessiblePMR;
    }

    @Override
    public String toString() {
        return "Salle [id=" + id +
                ", nom=" + nom +
                ", capacite=" + capacite +
                ", localisation=" + localisation +
                ", typeSalle=" + typeSalle +
                ", disponible=" + disponible +
                ", projecteur=" + projecteur +
                ", accessiblePMR=" + accessiblePMR + "]";
    }
}
