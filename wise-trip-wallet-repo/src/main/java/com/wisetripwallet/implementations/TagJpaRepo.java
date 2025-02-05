package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Tag;
import com.wisetripwallet.model.TagColour;
import com.wisetripwallet.interfaces.TagRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TagJpaRepo implements TagRepo {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<Tag> findById(Long id) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Tag tag = entityManager.find(Tag.class, id);
            return Optional.ofNullable(tag);
        }
    }

    @Override
    public List<Tag> findByColour(TagColour colour) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Tag> query = entityManager.createQuery("SELECT t FROM Tag t WHERE t.colour = :colour", Tag.class);
            query.setParameter("colour", colour);
            return query.getResultList();
        }
    }

    @Override
    public Optional<Tag> save(Tag tag) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(tag);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(tag);
        }
    }

    @Override
    public Optional<Tag> update(Tag tag) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(tag);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(tag);
        }
    }

    @Override
    public Optional<Tag> delete(Tag tag) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(tag);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(tag);
        }
    }
}
