package com.example.backend.DAO.Repository;

import com.example.backend.DAO.Repository.CrudRepository;
import com.example.backend.Model.users.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<? extends User> findByUserName(String userName);
    Optional<? extends User> findByEmail(String email);

}
