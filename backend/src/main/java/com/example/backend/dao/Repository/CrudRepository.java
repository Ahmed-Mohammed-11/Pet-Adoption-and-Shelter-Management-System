package com.example.backend.dao.Repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    ID save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void update(T entity);

    void deleteById(ID id);

    void delete(T entity);
}
