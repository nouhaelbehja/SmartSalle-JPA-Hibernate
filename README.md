# 🏢 SmartSalle — Room Management System

### Java | JPA | Hibernate | Maven | H2 | JUnit 4

SmartSalle est une application Java de gestion des salles et des utilisateurs, développée dans le cadre d'un TP sur la persistance des données avec **JPA et Hibernate**.

Le projet repose sur une architecture en couches (Entités, DAO, Service) et utilise une base de données H2. Il intègre également des fonctionnalités avancées de recherche et de filtrage pour faciliter la gestion des salles.

## 🎯 Objectifs du projet

- Comprendre le fonctionnement de JPA et Hibernate.
- Implémenter les opérations CRUD avec une DAO générique.
- Gérer les relations entre les entités.
- Manipuler les transactions avec `EntityManager`.
- Écrire des requêtes JPQL.
- Tester les opérations de persistance avec JUnit 4.
- Améliorer la gestion des erreurs et des exceptions.

## 🛠️ Technologies utilisées

| Technologie | Utilisation |
|---|---|
| Java | Développement de l'application |
| Maven | Gestion des dépendances |
| JPA 2.2 | API de persistance |
| Hibernate | Implémentation ORM |
| H2 Database | Base de données relationnelle |
| JUnit 4 | Tests automatisés |
| IntelliJ IDEA | Environnement de développement |

## 🏗️ Architecture du projet

```text
gestion-salles/
├── src/
│   ├── main/
│   │   ├── java/com/example/jpa/
│   │   │   ├── entities/
│   │   │   │   ├── Salle.java
│   │   │   │   └── Utilisateur.java
│   │   │   ├── dao/
│   │   │   │   ├── GenericDao.java
│   │   │   │   ├── SalleDao.java
│   │   │   │   └── UtilisateurDao.java
│   │   │   └── service/
│   │   │       └── ServiceGestion.java
│   │   └── resources/
│   │       └── META-INF/
│   │           └── persistence.xml
│   └── test/java/com/example/jpa/test/
│       └── ServiceGestionTest.java
├── screenshots/
├── pom.xml
├── .gitignore
└── README.md
```

## 📌 Fonctionnalités

### Gestion des salles

- Ajouter, consulter, modifier et supprimer une salle.
- Rechercher les salles selon leur capacité.
- Filtrer les salles disponibles.
- Rechercher les salles équipées d'un projecteur.
- Filtrer les salles accessibles aux personnes à mobilité réduite (PMR).
- Effectuer des recherches multicritères.
- Consulter le nombre total de salles.

### Gestion des utilisateurs

- Ajouter, consulter, modifier et supprimer un utilisateur.
- Associer un utilisateur à une salle.
- Rechercher les utilisateurs par salle.
- Rechercher par nom ou adresse email.
- Filtrer les utilisateurs par département ou rôle.
- Consulter les utilisateurs actifs.
- Consulter le nombre total d'utilisateurs.

## 🗃️ Modélisation des données

### Entité Salle

| Attribut | Description |
|---|---|
| id | Identifiant unique |
| nom | Nom de la salle |
| capacite | Capacité maximale |
| localisation | Bâtiment ou étage |
| typeSalle | Type de salle |
| disponible | Statut de disponibilité |
| projecteur | Présence d'un projecteur |
| accessiblePMR | Accessibilité PMR |

### Entité Utilisateur

| Attribut | Description |
|---|---|
| id | Identifiant unique |
| nom | Nom de l'utilisateur |
| prenom | Prénom |
| email | Adresse email unique |
| telephone | Numéro de téléphone |
| departement | Département |
| role | Rôle de l'utilisateur |
| actif | Statut du compte |
| salle | Salle associée |

**Relation :** plusieurs utilisateurs peuvent être associés à une même salle grâce à l'annotation `@ManyToOne`.

## 🔍 Requêtes JPQL

Le projet comprend plusieurs requêtes JPQL pour effectuer des recherches ciblées.

Exemple : rechercher les salles disponibles avec projecteur et capacité suffisante.

```java
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
```

## 🔐 Transactions et gestion des exceptions

Les transactions sont gérées manuellement avec `EntityTransaction`.

- `begin()` : démarrage de la transaction.
- `commit()` : validation des modifications.
- `rollback()` : annulation en cas d'erreur.
- `Logger` : journalisation des opérations et des exceptions.

## 🧪 Tests JUnit

Les tests couvrent notamment :

- La création et la recherche de salles.
- La création d'utilisateurs associés à une salle.
- La recherche d'utilisateurs par salle.
- La recherche de salles disponibles.
- La recherche de salles avec projecteur.
- La recherche multicritère.
- Le filtrage par département.
- Le comptage des salles.
- Les recherches JPQL par capacité et par nom, si les tests complémentaires ont été ajoutés.

Les résultats des tests sont visibles dans IntelliJ IDEA.

## 🚀 Installation et exécution

### Prérequis

- JDK compatible avec le projet (Java 17 recommandé).
- Maven.
- IntelliJ IDEA ou un autre IDE Java.

### Installation

Cloner le dépôt :

```bash
git clone https://github.com/USERNAME/SmartSalle-JPA-Hibernate.git
```

Accéder au dossier :

```bash
cd SmartSalle-JPA-Hibernate
```

Installer les dépendances et compiler :

```bash
mvn clean compile
```

Exécuter les tests :

```bash
mvn test
```

Remplacer `USERNAME` par le nom du compte GitHub une fois le dépôt créé.

## 📸 Captures d'écran
![Capture d'écran 2026-10-23 143729.png](../../Pictures/Screenshots/Capture%20d%27%C3%A9cran%202026-10-23%20143729.png)
![Capture d'écran 2026-10-23 143851.png](../../Pictures/Screenshots/Capture%20d%27%C3%A9cran%202026-10-23%20143851.png)
## ✨ Améliorations apportées

Par rapport à l'implémentation initiale du TP, SmartSalle introduit :

- Des attributs supplémentaires pour les salles et utilisateurs.
- Des recherches multicritères en JPQL.
- Des fonctionnalités de filtrage métier.
- Des statistiques simples sur les entités.
- Une gestion centralisée des exceptions.
- Des tests complémentaires pour les fonctionnalités personnalisées.

## 📚 Contexte académique!
Projet réalisé dans le cadre d'un TP de développement Java Enterprise Edition (JEE), consacré à la persistance des données avec JPA et Hibernate.