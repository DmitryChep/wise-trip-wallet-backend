package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.User;
import com.wisetripwallet.interfaces.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaRepo implements UserRepo {
    private final EntityManagerFactory entityManagerFactory;


    @Override
    public List<User> findALl() throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            String query = "SELECT u FROM User u";
            return entityManager.createQuery(query, User.class).getResultList();
        }
    }

    @Override
    public Optional<User> findById(Long id) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            User user = entityManager.find(User.class, id);
            return Optional.ofNullable(user);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            query.setMaxResults(1);
            User userByEmail = query.getSingleResult();
            return Optional.ofNullable(userByEmail);
        }
    }

    @Override
    public Optional<User> save(User user) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(user);
        }
    }


    @Override
    public Optional<User> update(User user) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(user);
        }
    }

    @Override
    public Optional<User> delete(User user) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(user);
        }
    }
}
