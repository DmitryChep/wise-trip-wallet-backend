package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.interfaces.CurrencyRepo;
import com.wisetripwallet.model.Currency;
import com.wisetripwallet.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class CurrencyJpaRepo implements CurrencyRepo {
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<Currency> findALl(Long id) throws RepoException {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            String query = "SELECT c FROM Currency c";
            return entityManager.createQuery(query, Currency.class).getResultList();
        } catch (Exception e) {
            throw new RepoException("Could not find all currencies", e);
        }
    }

    @Override
    public Optional<Currency> findById(Long id) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Currency currency = entityManager.find(Currency.class, id);
            return Optional.ofNullable(currency);
        }catch (Exception e) {
            throw new RepoException("Could not find currency by id", e);
        }
    }

    @Override
    public List<Currency> findByUser(User user) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Currency> query = entityManager.createQuery("SELECT c FROM Report c WHERE c.user = :user", Currency.class);
            query.setParameter("user", user);

            return query.getResultList();
        } catch (Exception e) {
            throw new RepoException("Could not find currency with user: " + user, e);
        }
    }

    @Override
    public Optional<Currency> save(Currency currency) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(currency);
            entityManager.getTransaction().commit();
            return Optional.of(currency);
        }catch (Exception e) {
            throw new RepoException("Could not save currency", e);
        }
    }

    @Override
    public Optional<Currency> update(Currency currency) throws RepoException {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(currency);
            entityManager.getTransaction().commit();
            return Optional.of(currency);
        }catch (Exception e) {
            throw new RepoException("Could not update currency", e);
        }
    }

    @Override
    public Optional<Currency> delete(Currency currency) throws RepoException {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(currency);
            entityManager.getTransaction().commit();
            return Optional.of(currency);
        }catch (Exception e) {
            throw new RepoException("Could not delete currency", e);
        }
    }
}
