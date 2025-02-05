package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Category;
import com.wisetripwallet.model.Expense;
import com.wisetripwallet.interfaces.CategoryRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryJpaRepo implements CategoryRepo {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<Category> findById(Long id) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Category category = entityManager.find(Category.class, id);
            return Optional.ofNullable(category);
        }
    }

    @Override
    public List<Category> findByExpense(Expense expenses) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.expenses = :expenses", Category.class);
            query.setParameter("expenses", expenses);
            return query.getResultList();
        }
    }

    @Override
    public Optional<Category> save(Category category) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(category);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(category);
        }
    }

    @Override
    public Optional<Category> update(Category category) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(category);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(category);
        }
    }

    @Override
    public Optional<Category> delete(Category category) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(category);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(category);
        }
    }
}
