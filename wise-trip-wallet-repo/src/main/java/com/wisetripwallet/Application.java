package com.wisetripwallet;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.implementations.ReportJpaRepo;
import com.wisetripwallet.implementations.UserJpaRepo;
import com.wisetripwallet.model.Report;
import com.wisetripwallet.model.ReportType;
import com.wisetripwallet.model.User;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {

        Map<String, String> config = new HashMap<>();
        config.put("javax.persistence.jdbc.url", System.getenv("DB_URL"));
        config.put("javax.persistence.jdbc.user", System.getenv("DB_USER"));
        config.put("javax.persistence.jdbc.password", System.getenv("DB_PASSWORD"));

        try (EntityManagerFactory entityManagerFactory
                     = Persistence.createEntityManagerFactory("wise-trip-wallet-unit", config)) {

            UserJpaRepo userJpaRepo = new UserJpaRepo(entityManagerFactory);
            Optional<User> userByEmail = userJpaRepo.findByEmail("jane.smith@gmail.com");

            ReportJpaRepo reportJpaRepo = new ReportJpaRepo(entityManagerFactory);
            List<Report> reportByReportType = reportJpaRepo.findByReportType(ReportType.OVERALL_REPORT);

        } catch (RepoException e){
            throw new RepoException(e.getMessage());
        }
    }
}
