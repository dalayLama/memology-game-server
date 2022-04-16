package org.toxicgames.memology.dao.repositories;

public interface Repository<T, ID> extends ReadOnlyRepository<T, ID> {

    <S extends T> S save(S entity);

    void delete(T entity);

    void deleteById(ID id);

}
