package com.example.backend.DAO;

import com.example.backend.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findById(String id) {
        String sql = "SELECT * FROM pet_adoption.user WHERE user_id = ?";
        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(User.class) , id)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        String sql = "SELECT * FROM pet_adoption.user WHERE userName = ?";
        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(User.class) , userName)
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
    public void save(User user) {
        String sql = "INSERT INTO pet_adoption.user " +
                "(user_id, userName, password, phone, firstName, lastName, email, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getUserId(), user.getUsername(), user.getPassword(),
                user.getPhone(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getRole().name());
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
    public void deleteById(String id) {
        String sql = "DELETE FROM pet_adoption.user WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM pet_adoption.user WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getUserId());
    }
}