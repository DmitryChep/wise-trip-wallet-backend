package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Expense;
import com.wisetripwallet.model.User;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepo {

    Optional<Expense> findById(Long id) throws RepoException;

    List<Expense> findByUser(User user) throws RepoException;

    Optional<Expense> save(Expense expense) throws RepoException;

    Optional<Expense> update(Expense expense) throws RepoException;

    Optional<Expense> delete(Expense expense) throws RepoException;
}

