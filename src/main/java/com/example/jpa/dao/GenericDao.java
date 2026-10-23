
package com.example.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericDao<T> {

    private static final Logger LOGGER =
            Logger.getLogger(GenericDao.class.getName());

    private final Class<T> entityClass;
    protected EntityManager em;

    public GenericDao(Class<T> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    // CREATE : Ajouter une entité
    public void create(T entity) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(entity);
            tx.commit();

            LOGGER.info("Entité créée : "
                    + entityClass.getSimpleName());

        } catch (RuntimeException e) {
            rollback(tx);
            LOGGER.log(Level.SEVERE,
                    "Erreur lors de la création", e);
            throw e;
        }
    }

    // READ : Rechercher par identifiant
    public T find(Object id) {
        try {
            return em.find(entityClass, id);

        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE,
                    "Erreur lors de la recherche", e);
            throw e;
        }
    }

    // UPDATE : Modifier une entité
    public T update(T entity) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            T merged = em.merge(entity);
            tx.commit();

            LOGGER.info("Entité modifiée : "
                    + entityClass.getSimpleName());

            return merged;

        } catch (RuntimeException e) {
            rollback(tx);
            LOGGER.log(Level.SEVERE,
                    "Erreur lors de la modification", e);
            throw e;
        }
    }

    // DELETE : Supprimer une entité
    public void delete(T entity) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            T managed = em.contains(entity)
                    ? entity : em.merge(entity);

            em.remove(managed);
            tx.commit();

            LOGGER.info("Entité supprimée : "
                    + entityClass.getSimpleName());

        } catch (RuntimeException e) {
            rollback(tx);
            LOGGER.log(Level.SEVERE,
                    "Erreur lors de la suppression", e);
            throw e;
        }
    }

    // FIND ALL : Lister les entités
    public List<T> findAll() {
        try {
            return em.createQuery(
                    "SELECT e FROM "
                            + entityClass.getSimpleName() + " e",
                    entityClass
            ).getResultList();

        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE,
                    "Erreur lors du listage", e);
            throw e;
        }
    }

    // SMARTSALLE : Compter les entités
    public long count() {
        try {
            return em.createQuery(
                    "SELECT COUNT(e) FROM "
                            + entityClass.getSimpleName() + " e",
                    Long.class
            ).getSingleResult();

        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE,
                    "Erreur lors du comptage", e);
            throw e;
        }
    }

    // Gestion centralisée du rollback
    private void rollback(EntityTransaction tx) {
        try {
            if (tx.isActive()) {
                tx.rollback();
                LOGGER.warning("Transaction annulée (rollback)");
            }
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE,
                    "Échec du rollback", e);
        }
    }
}
