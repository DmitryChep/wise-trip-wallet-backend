package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Trip;
import com.wisetripwallet.model.User;
import com.wisetripwallet.interfaces.TripRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TripJpaRepo implements TripRepo {
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<Trip> findById(Long id) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Trip trip = entityManager.find(Trip.class, id);
            return Optional.ofNullable(trip);
        }
    }


    @Override
    public Optional<Trip> findByTripName(String tripName) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Trip> query = entityManager.createQuery("SELECT t FROM Trip t WHERE t.tripName = :tripName", Trip.class);
            query.setParameter("tripName", tripName);
            query.setMaxResults(1);
            Trip tripByUser = query.getSingleResult();
            return Optional.ofNullable(tripByUser);
        }
    }

    @Override
    public List<Trip> findAllByUser(User user) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Trip> query = entityManager.createQuery("SELECT t FROM Trip t WHERE t.user = :user", Trip.class);
            query.setParameter("user", user);
            return query.getResultList();
        }
    }

    @Override
    public Optional<Trip> save(Trip trip) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(trip);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(trip);
        }
    }

    @Override
    public Optional<Trip> update(Trip trip) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(trip);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(trip);
        }
    }

    @Override
    public Optional<Trip> delete(Trip trip) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(trip);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(trip);
        }
    }
}

