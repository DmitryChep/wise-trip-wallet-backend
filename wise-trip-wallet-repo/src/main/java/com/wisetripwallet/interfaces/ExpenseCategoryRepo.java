package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.ExpenseCategory;

import java.util.List;
import java.util.Optional;

public interface ExpenseCategoryRepo {

    List<ExpenseCategory> findAll() throws RepoException;

    Optional<ExpenseCategory> findByExpenseAndCategory(Long expenseId, Long categoryId) throws RepoException;

    Optional<ExpenseCategory> save(ExpenseCategory expenseCategory) throws RepoException;

    Optional<ExpenseCategory> delete(ExpenseCategory expenseCategory) throws RepoException;
}
