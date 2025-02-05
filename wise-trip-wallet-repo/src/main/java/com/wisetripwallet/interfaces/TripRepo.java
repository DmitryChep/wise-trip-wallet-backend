package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Trip;
import com.wisetripwallet.model.User;

import java.util.List;
import java.util.Optional;

public interface TripRepo {

    Optional<Trip> findById(Long id) throws RepoException;

    Optional<Trip> findByTripName(String tripName) throws RepoException;

    List<Trip> findAllByUser(User user) throws RepoException;

    Optional<Trip> save(Trip trip) throws RepoException;

    Optional<Trip> update(Trip trip) throws RepoException;

    Optional<Trip> delete(Trip trip) throws RepoException;
}

