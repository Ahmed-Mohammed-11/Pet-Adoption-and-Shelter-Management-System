package com.example.backend.dao.implementation;

import com.example.backend.model.users.Adopter;
import com.example.backend.model.users.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class AdopterRepositoryImpl extends UserRepositoryImpl {

    public AdopterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Transactional
    public Integer save(Adopter adopter) {
        Integer id = this.save((User) adopter);
        String sql = "INSERT INTO pet_adoption.adopter " +
                "(user_id) " +
                "VALUES (?)";

        jdbcTemplate.update(sql, id);

        return id;
    }

    @Override
    public Optional<Adopter> findByUserName(String userName) {

        String sql = """
                SELECT * FROM pet_adoption.adopter
                JOIN pet_adoption.user
                ON adopter.user_id = user.user_id
                WHERE user.userName = ?
                """;

        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(Adopter.class) , userName)
                .stream()
                .findFirst();
    }
}
