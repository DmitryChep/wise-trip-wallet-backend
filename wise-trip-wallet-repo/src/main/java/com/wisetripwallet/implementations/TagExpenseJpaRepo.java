package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.TagExpense;
import com.wisetripwallet.interfaces.TagExpenseRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TagExpenseJpaRepo implements TagExpenseRepo {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<TagExpense> findAll() throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<TagExpense> query = entityManager.createQuery("SELECT t FROM TagExpense t", TagExpense.class);
            return query.getResultList();
        }
    }

    @Override
    public Optional<TagExpense> findByTagAndExpense(Long tagId, Long expenseId) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<TagExpense> query = entityManager.createQuery(
                    "SELECT te FROM TagExpense te WHERE te.tag.id = :tagId AND te.expense.id = :expenseId", TagExpense.class);
            query.setParameter("tagId", tagId);
            query.setParameter("expenseId", expenseId);
            query.setMaxResults(1);
            TagExpense tagExpense = query.getSingleResult();
            return Optional.ofNullable(tagExpense);
        }
    }

    @Override
    public Optional<TagExpense> save(TagExpense tagExpense) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(tagExpense);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(tagExpense);
        }
    }

    @Override
    public Optional<TagExpense> delete(TagExpense tagExpense) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(tagExpense);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(tagExpense);
        }
    }
}
