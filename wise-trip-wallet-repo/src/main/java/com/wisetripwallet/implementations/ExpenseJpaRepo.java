package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Expense;
import com.wisetripwallet.model.User;
import com.wisetripwallet.interfaces.ExpenseRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExpenseJpaRepo implements ExpenseRepo {
    private final EntityManagerFactory entityManagerFactory;


    @Override
    public Optional<Expense> findById(Long id) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Expense expense = entityManager.find(Expense.class, id);
            return Optional.ofNullable(expense);
        }
    }

    @Override
    public List<Expense> findByUser(User user) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Expense> query = entityManager.createQuery("SELECT e FROM Expense e WHERE e.user = :user", Expense.class);
            query.setParameter("user", user);
            return query.getResultList();
        }
    }

    @Override
    public Optional<Expense> save(Expense Expense) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(Expense);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(Expense);
        }
    }

    @Override
    public Optional<Expense> update(Expense Expense) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(Expense);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(Expense);
        }
    }

    @Override
    public Optional<Expense> delete(Expense Expense) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(Expense);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(Expense);
        }
    }
}
