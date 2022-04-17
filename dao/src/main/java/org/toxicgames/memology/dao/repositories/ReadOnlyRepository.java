package org.toxicgames.memology.dao.repositories;

import java.util.List;
import java.util.Optional;

public interface ReadOnlyRepository<T, ID> {

    boolean exists(ID id);

    List<T> findAll();

    Optional<T> findById(ID id);

}
