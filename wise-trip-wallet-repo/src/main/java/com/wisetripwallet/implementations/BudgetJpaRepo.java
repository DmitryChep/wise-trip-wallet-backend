package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Budget;
import com.wisetripwallet.model.Trip;
import com.wisetripwallet.interfaces.BudgetRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BudgetJpaRepo implements BudgetRepo {
    private final EntityManagerFactory entityManagerFactory;


    @Override
    public Optional<Budget> findById(Long id) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Budget budget = entityManager.find(Budget.class, id);
            return Optional.ofNullable(budget);
        }
    }

    @Override
    public List<Budget> findByTrip(Trip trip) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Budget> query = entityManager.createQuery("SELECT b FROM Budget b WHERE b.trip = :trip", Budget.class);
            query.setParameter("trip", trip);
            return query.getResultList();
        }
    }

    @Override
    public Optional<Budget> save(Budget budget) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(budget);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(budget);
        }
    }

    @Override
    public Optional<Budget> update(Budget budget) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(budget);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(budget);
        }
    }

    @Override
    public Optional<Budget> delete(Budget budget) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(budget);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(budget);
        }
    }
}