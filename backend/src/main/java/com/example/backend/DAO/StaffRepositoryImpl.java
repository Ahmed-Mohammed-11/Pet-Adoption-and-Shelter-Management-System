package com.example.backend.DAO;

import com.example.backend.Model.Adopter;
import com.example.backend.Model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StaffRepositoryImpl extends UserRepositoryImpl {

    public StaffRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Transactional
    public String save(Adopter adopter) {
        String id = this.save((User) adopter);
        String sql = "INSERT INTO pet_adoption.staff_member " +
                "(user_id) " +
                "VALUES (?)";

        jdbcTemplate.update(sql, id);

        return id;
    }
}
