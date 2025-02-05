package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.TripCurrency;
import com.wisetripwallet.interfaces.TripCurrencyRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TripCurrencyJpaRepo implements TripCurrencyRepo {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<TripCurrency> findAll() throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<TripCurrency> query = entityManager.createQuery("SELECT t FROM TripCurrency t", TripCurrency.class);
            return query.getResultList();
        }
    }

    @Override
    public Optional<TripCurrency> findById(Long id) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TripCurrency tripCurrency = entityManager.find(TripCurrency.class, id);
            return Optional.ofNullable(tripCurrency);
        }
    }

    @Override
    public Optional<TripCurrency> save(TripCurrency tripCurrency) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(tripCurrency);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(tripCurrency);
        }
    }

    @Override
    public Optional<TripCurrency> update(TripCurrency tripCurrency) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            tripCurrency = entityManager.merge(tripCurrency);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(tripCurrency);
        }
    }

    @Override
    public Optional<TripCurrency> delete(TripCurrency tripCurrency) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(tripCurrency);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(tripCurrency);
        }
    }
}

