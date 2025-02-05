package com.wisetripwallet.implementations;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.*;
import com.wisetripwallet.interfaces.ReportRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ReportJpaRepo implements ReportRepo {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<Report> findById(Long id) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Report report = entityManager.find(Report.class, id);

            return Optional.ofNullable(report);
        } catch (PersistenceException e) {
            throw new RepoException("Could not find report with id: " + id, e);
        }
    }

    @Override
    public List<Report> findByReportType(ReportType reportType) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Report> query = entityManager.createQuery("SELECT r FROM Report r WHERE r.reportType = :reportType", Report.class);
            query.setParameter("reportType", reportType);

            return query.getResultList();
        } catch (Exception e) {
            throw new RepoException("Could not find report with type: " + reportType, e);
        }
    }

    @Override
    public List<Report> findByUser(User user) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Report> query = entityManager.createQuery("SELECT r FROM Report r WHERE r.user = :user", Report.class);
            query.setParameter("user", user);

            return query.getResultList();
        } catch (Exception e) {
            throw new RepoException("Could not find report with user: " + user, e);
        }
    }

    @Override
    public Optional<Report> save(Report report) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(report);
            entityManager.getTransaction().commit();
            return Optional.of(report);
        } catch (Exception e) {
            throw new RepoException("Could not save report", e);
        }
    }

    @Override
    public Optional<Report> update(Report report) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(report);
            entityManager.getTransaction().commit();
            return Optional.of(report);
        } catch (Exception e) {
            throw new RepoException("Could not update report", e);
        }
    }

    @Override
    public Optional<Report> delete(Report report) throws RepoException {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(report) ? report : entityManager.merge(report));
            entityManager.getTransaction().commit();
            return Optional.of(report);
        } catch (Exception e) {
            throw new RepoException("Could not delete report", e);
        }
    }
}

