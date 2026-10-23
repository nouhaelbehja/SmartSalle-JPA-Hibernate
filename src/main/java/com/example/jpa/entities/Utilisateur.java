
package com.example.jpa.entities;

import javax.persistence.*;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    // Champs supplémentaires SmartSalle
    private String prenom;
    private String telephone;
    private String departement;
    private String role;
    private boolean actif = true;

    // Relation avec Salle
    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    // Constructeur obligatoire JPA
    public Utilisateur() {}

    // Constructeur demandé par le TP
    public Utilisateur(String nom, String email, Salle salle) {
        this.nom = nom;
        this.email = email;
        this.salle = salle;
    }

    // Constructeur personnalisé
    public Utilisateur(String nom, String prenom,
                       String email, String telephone,
                       String departement, String role,
                       boolean actif, Salle salle) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.departement = departement;
        this.role = role;
        this.actif = actif;
        this.salle = salle;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id +
                ", nom=" + nom +
                ", prenom=" + prenom +
                ", email=" + email +
                ", telephone=" + telephone +
                ", departement=" + departement +
                ", role=" + role +
                ", actif=" + actif +
                ", salle=" +
                (salle != null ? salle.getNom() : "aucune") +
                "]";
    }
}
