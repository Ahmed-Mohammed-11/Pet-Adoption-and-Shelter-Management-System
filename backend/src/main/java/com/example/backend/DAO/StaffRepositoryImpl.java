package com.example.backend.DAO;

import com.example.backend.Model.users.StaffMember;
import com.example.backend.Model.users.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class StaffRepositoryImpl extends UserRepositoryImpl {

    public StaffRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Optional<StaffMember> findByUserName(String userName) {
        String sql = """
                SELECT u.user_id, u.userName, u.password,
                    u.phone, u.firstName, u.lastName,
                    u.email, u.role, sm.staff_role,
                    s.name AS shelterName
                FROM pet_adoption.staff_member AS sm
                JOIN pet_adoption.user AS u ON sm.user_id = u.user_id
                JOIN shelter AS s ON sm.shelter_id = s.shelter_id
                WHERE u.userName = ?
                """;

        return jdbcTemplate
                .query(sql, new Object[]{userName}, new BeanPropertyRowMapper<>(StaffMember.class))
                .stream()
                .findFirst();
    }

    @Transactional
    public String save(StaffMember staff) {
        String user_id = this.save((User)staff);

        String sql = """
                INSERT INTO pet_adoption.staff_member
                (user_id, shelter_id, staff_role)
                SELECT ?, shelter_id, ?
                FROM pet_adoption.shelter
                WHERE name = ?
                """;


        jdbcTemplate.update(sql, user_id, staff.getStaffRole(), staff.getShelterName());

        return user_id;
    }
}