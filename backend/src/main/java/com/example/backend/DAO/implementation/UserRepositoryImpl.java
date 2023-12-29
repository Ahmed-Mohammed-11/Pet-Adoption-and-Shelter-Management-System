package com.example.backend.DAO.implementation;

import com.example.backend.DAO.Repository.UserRepository;
import com.example.backend.Model.users.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    protected final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM pet_adoption.user WHERE user_id = ?";
        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(User.class) , id)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<? extends User> findByUserName(String userName) {
        String sql = "SELECT * FROM pet_adoption.user WHERE userName = ?";
        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(User.class) , userName)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM pet_adoption.user WHERE email = ?";
        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(User.class), email)
                .stream()
                .findFirst();
    }


    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM pet_adoption.user";
        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Integer save(User user) {
        String sql = "INSERT INTO pet_adoption.user " +
                "(userName, password, phone, firstName, lastName, email, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getPhone());
            pst.setString(4, user.getFirstName());
            pst.setString(5, user.getLastName());
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getRole().name());
            return pst;
        }, keyHolder);

        return (Integer) keyHolder.getKey();
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE pet_adoption.user SET userName = ?, password = ?, phone = ?, " +
                "firstName = ?, lastName = ?, email = ?, role = ? WHERE user_id = ?";

        jdbcTemplate.update(
                sql, user.getUsername(), user.getPassword(), user.getPhone(),
                user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getRole().name(), user.getUserId());
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM pet_adoption.user WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM pet_adoption.user WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getUserId());
    }
}