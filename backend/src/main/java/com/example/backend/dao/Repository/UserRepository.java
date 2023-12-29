package com.example.backend.dao.Repository;

import com.example.backend.model.users.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<? extends User> findByUserName(String userName);
    Optional<? extends User> findByEmail(String email);

}
