package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Budget;
import com.wisetripwallet.model.Trip;


import java.util.List;
import java.util.Optional;

public interface BudgetRepo {

    Optional<Budget> findById(Long id) throws RepoException;

    List<Budget> findByTrip(Trip trip) throws RepoException;

    Optional<Budget> save(Budget budget) throws RepoException;

    Optional<Budget> update(Budget budget) throws RepoException;

    Optional<Budget> delete(Budget budget) throws RepoException;
}


