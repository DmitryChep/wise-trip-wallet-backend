package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {

    List<User> findALl() throws RepoException;

    Optional<User> findById(Long id) throws RepoException;

    Optional<User> findByEmail(String email) throws RepoException;

    Optional<User> save(User user) throws RepoException;

    Optional<User> update(User user) throws RepoException;

    Optional<User> delete(User user) throws RepoException;
}
