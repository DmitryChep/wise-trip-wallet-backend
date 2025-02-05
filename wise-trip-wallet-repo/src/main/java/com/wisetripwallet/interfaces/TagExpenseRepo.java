package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.TagExpense;

import java.util.List;
import java.util.Optional;

public interface TagExpenseRepo {

    List<TagExpense> findAll() throws RepoException;

    Optional<TagExpense> findByTagAndExpense(Long tagId, Long expenseId) throws RepoException;

    Optional<TagExpense> save(TagExpense tagExpense) throws RepoException;

    Optional<TagExpense> delete(TagExpense tagExpense) throws RepoException;
}
