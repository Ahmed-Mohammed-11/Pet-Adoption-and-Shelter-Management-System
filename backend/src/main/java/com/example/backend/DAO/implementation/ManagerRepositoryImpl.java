package com.example.backend.DAO.implementation;

import com.example.backend.Model.users.ShelterManager;
import com.example.backend.Model.users.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ManagerRepositoryImpl extends UserRepositoryImpl{
    public ManagerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Transactional
    public Integer save(ShelterManager shelterManager) {
        Integer id = this.save((User) shelterManager);
        String sql = "INSERT INTO shelter_manager " +
                "(user_id) " +
                "VALUES (?)";

        jdbcTemplate.update(sql, id);
        return id;
    }

}
