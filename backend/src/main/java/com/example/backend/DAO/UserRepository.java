package com.example.backend.DAO;

import com.example.backend.Model.users.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<? extends User> findByUserName(String userName);
    Optional<? extends User> findByEmail(String email);

}
