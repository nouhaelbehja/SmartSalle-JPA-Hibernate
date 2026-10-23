
package com.example.jpa.test;

import com.example.jpa.entities.Salle;
import com.example.jpa.entities.Utilisateur;
import com.example.jpa.service.ServiceGestion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ServiceGestionTest {

    private ServiceGestion service;

    // Génère un nom unique pour éviter les doublons
    private String unique(String prefix) {
        return prefix + "_" +
                UUID.randomUUID().toString().substring(0, 8);
    }

    @Before
    public void setUp() {
        service = new ServiceGestion();
    }

    @After
    public void tearDown() {
        if (service != null) {
            service.fermer();
        }
    }

    // TEST 1 : Création et recherche d'une salle
    @Test
    public void testCreationEtRechercheSalle() {
        String nom = unique("Salle_Test");

        Salle salle = new Salle(nom, 20);
        service.ajouterSalle(salle);

        List<Salle> salles = service.listerSalles();

        assertTrue(salles.stream()
                .anyMatch(s -> s.getNom().equals(nom)));
        assertNotNull(salle.getId());
    }

    // TEST 2 : Création d'un utilisateur avec salle
    @Test
    public void testCreationUtilisateurAvecSalle() {
        Salle salle = new Salle(unique("Salle_B"), 15);
        service.ajouterSalle(salle);

        String email = unique("alice") + "@example.com";

        Utilisateur utilisateur =
                new Utilisateur("Alice", email, salle);

        service.ajouterUtilisateur(utilisateur);

        Utilisateur resultat =
                service.rechercherUtilisateurParEmail(email);

        assertNotNull(resultat);
        assertEquals("Alice", resultat.getNom());
        assertEquals(salle.getNom(),
                resultat.getSalle().getNom());
    }

    // TEST 3 : Recherche des utilisateurs par salle
    @Test
    public void testRechercheUtilisateursParSalle() {
        Salle salle = new Salle(unique("Salle_C"), 30);
        service.ajouterSalle(salle);

        Utilisateur u1 = new Utilisateur(
                "Bob", unique("bob") + "@example.com", salle);

        Utilisateur u2 = new Utilisateur(
                "Carol", unique("carol") + "@example.com", salle);

        service.ajouterUtilisateur(u1);
        service.ajouterUtilisateur(u2);

        List<Utilisateur> utilisateurs =
                service.utilisateursParSalle(salle.getNom());

        assertEquals(2, utilisateurs.size());
    }

    // TEST 4 : Recherche des salles disponibles
    @Test
    public void testSallesDisponibles() {
        Salle salle = new Salle(unique("Salle_Libre"), 25);
        salle.setDisponible(true);

        service.ajouterSalle(salle);

        List<Salle> salles = service.sallesDisponibles();

        assertTrue(salles.stream()
                .anyMatch(s -> s.getId().equals(salle.getId())));
    }

    // TEST 5 : Recherche des salles avec projecteur
    @Test
    public void testSallesAvecProjecteur() {
        Salle salle = new Salle(unique("Salle_Multimedia"), 40);
        salle.setProjecteur(true);

        service.ajouterSalle(salle);

        List<Salle> salles = service.sallesAvecProjecteur();

        assertTrue(salles.stream()
                .anyMatch(s -> s.getId().equals(salle.getId())));
    }

    // TEST 6 : Recherche multicritère SmartSalle
    @Test
    public void testRechercheSallesAdaptees() {
        Salle salle = new Salle(unique("Salle_Innovation"), 50);
        salle.setDisponible(true);
        salle.setProjecteur(true);
        salle.setAccessiblePMR(true);

        service.ajouterSalle(salle);

        List<Salle> resultats =
                service.rechercherSallesAdaptees(30);

        assertTrue(resultats.stream()
                .anyMatch(s -> s.getId().equals(salle.getId())));
    }

    // TEST 7 : Recherche des utilisateurs par département
    @Test
    public void testUtilisateursParDepartement() {
        Salle salle = new Salle(unique("Salle_IT"), 20);
        service.ajouterSalle(salle);

        String email = unique("nouha") + "@example.com";

        Utilisateur utilisateur = new Utilisateur(
                "Test",
                "SmartSalle",
                email,
                "0600000000",
                "IT",
                "Administrateur",
                true,
                salle
        );

        service.ajouterUtilisateur(utilisateur);

        List<Utilisateur> utilisateurs =
                service.utilisateursParDepartement("IT");

        assertTrue(utilisateurs.stream()
                .anyMatch(u -> u.getEmail().equals(email)));
    }

    // TEST 8 : Vérifier le nombre de salles
    @Test
    public void testNombreSalles() {
        long avant = service.nombreSalles();

        Salle salle = new Salle(unique("Salle_Stat"), 10);
        service.ajouterSalle(salle);

        long apres = service.nombreSalles();

        assertEquals(avant + 1, apres);
    }

    @Test
    public void testRechercheGrandesSalles() {
        Salle salle = new Salle(unique("Grande_Salle"), 50);
        service.ajouterSalle(salle);

        List<Salle> resultats =
                service.rechercherGrandesSalles(20);

        assertTrue(resultats.stream()
                .anyMatch(s -> s.getId().equals(salle.getId())));
    }

    @Test
    public void testRechercheUtilisateurParNom() {
        Salle salle = new Salle(unique("Salle_Recherche"), 20);
        service.ajouterSalle(salle);

        String email = unique("jean") + "@example.com";
        Utilisateur utilisateur =
                new Utilisateur("Jean Dupont", email, salle);

        service.ajouterUtilisateur(utilisateur);

        List<Utilisateur> resultats =
                service.rechercherUtilisateursParNom("Jean");

        assertTrue(resultats.stream()
                .anyMatch(u -> u.getEmail().equals(email)));
    }

}
