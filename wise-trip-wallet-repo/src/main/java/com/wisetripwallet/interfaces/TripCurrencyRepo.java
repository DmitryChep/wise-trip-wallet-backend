package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.TripCurrency;

import java.util.List;
import java.util.Optional;

public interface TripCurrencyRepo {

    List<TripCurrency> findAll() throws RepoException;

    Optional<TripCurrency> findById(Long id) throws RepoException;

    Optional<TripCurrency> save(TripCurrency tripCurrency) throws RepoException;

    Optional<TripCurrency> update(TripCurrency tripCurrency) throws RepoException;

    Optional<TripCurrency> delete(TripCurrency tripCurrency) throws RepoException;
}