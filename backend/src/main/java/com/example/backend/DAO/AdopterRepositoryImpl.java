package com.example.backend.DAO;

import com.example.backend.Model.Adopter;
import com.example.backend.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class AdopterRepositoryImpl extends UserRepositoryImpl {

    public AdopterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Transactional
    public String save(Adopter adopter) {
        String id = this.save((User) adopter);
        String sql = "INSERT INTO pet_adoption.adopter " +
                "(user_id) " +
                "VALUES (?)";

        jdbcTemplate.update(sql, id);

        return id;
    }
}
