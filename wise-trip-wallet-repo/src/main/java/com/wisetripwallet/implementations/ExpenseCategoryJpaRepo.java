package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.ExpenseCategory;
import com.wisetripwallet.interfaces.ExpenseCategoryRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExpenseCategoryJpaRepo implements ExpenseCategoryRepo {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<ExpenseCategory> findAll() throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<ExpenseCategory> query = entityManager.createQuery("SELECT ec FROM ExpenseCategory ec", ExpenseCategory.class);
            return query.getResultList();
        }
    }

    @Override
    public Optional<ExpenseCategory> findByExpenseAndCategory(Long expenseId, Long categoryId) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<ExpenseCategory> query = entityManager.createQuery(
                    "SELECT ec FROM ExpenseCategory ec WHERE ec.expense.id = :expenseId AND ec.category.id = :categoryId", ExpenseCategory.class);
            query.setParameter("expenseId", expenseId);
            query.setParameter("categoryId", categoryId);
            query.setMaxResults(1);
            ExpenseCategory expenseCategory = query.getSingleResult();
            return Optional.ofNullable(expenseCategory);
        }
    }

    @Override
    public Optional<ExpenseCategory> save(ExpenseCategory expenseCategory) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(expenseCategory);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(expenseCategory);
        }
    }

    @Override
    public Optional<ExpenseCategory> delete(ExpenseCategory expenseCategory) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(expenseCategory);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(expenseCategory);
        }
    }
}
