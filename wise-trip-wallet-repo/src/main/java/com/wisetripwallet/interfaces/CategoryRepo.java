package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Category;
import com.wisetripwallet.model.Expense;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo {

    Optional<Category> findById(Long id) throws RepoException;

    List<Category> findByExpense(Expense expense) throws RepoException;

    Optional<Category> save(Category category) throws RepoException;

    Optional<Category> update(Category category) throws RepoException;

    Optional<Category> delete(Category category) throws RepoException;
}