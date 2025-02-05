package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Currency;
import com.wisetripwallet.model.User;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepo {

    List<Currency> findALl(Long id) throws RepoException;

    Optional<Currency> findById(Long id) throws RepoException;

    List<Currency> findByUser(User user) throws RepoException;

    Optional<Currency> save(Currency currency) throws RepoException;

    Optional<Currency> update(Currency currency) throws RepoException;

    Optional<Currency> delete(Currency currency) throws RepoException;
}

