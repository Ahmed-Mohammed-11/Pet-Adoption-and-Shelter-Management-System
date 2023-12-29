package com.example.backend.DAO;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    ID save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    int update(T entity);

    int deleteById(ID id);

    int delete(T entity);
}
