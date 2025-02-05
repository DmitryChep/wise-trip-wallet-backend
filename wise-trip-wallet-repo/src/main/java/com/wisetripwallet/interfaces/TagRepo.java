package com.wisetripwallet.interfaces;

import com.wisetripwallet.exception.RepoException;
import com.wisetripwallet.model.Tag;
import com.wisetripwallet.model.TagColour;

import java.util.List;
import java.util.Optional;

public interface TagRepo {

    Optional<Tag> findById(Long id) throws RepoException;

    List<Tag> findByColour(TagColour colour) throws RepoException;

    Optional<Tag> save(Tag tag) throws RepoException;

    Optional<Tag> update(Tag tag) throws RepoException;

    Optional<Tag> delete(Tag tag) throws RepoException;
}
