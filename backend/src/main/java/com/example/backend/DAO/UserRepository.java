package com.example.backend.DAO;

import com.example.backend.Model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUserName(String userName);

}
