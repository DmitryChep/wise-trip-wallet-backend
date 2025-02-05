package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Report;
import com.wisetripwallet.model.ReportType;
import com.wisetripwallet.model.User;

import java.util.List;
import java.util.Optional;

public interface ReportRepo {

    Optional<Report> findById(Long id) throws RepoException;

    List<Report> findByReportType(ReportType reportType) throws RepoException;

    List<Report> findByUser(User user) throws RepoException;

    Optional<Report> save(Report report) throws RepoException;

    Optional<Report> update(Report report) throws RepoException;

    Optional<Report> delete(Report report) throws RepoException;
}


