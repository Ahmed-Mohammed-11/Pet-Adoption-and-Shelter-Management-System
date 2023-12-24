package com.example.backend.pet;

import com.example.backend.entities.Pet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PetRowMapper implements RowMapper<Pet> {
    @Override
    public Pet mapRow(ResultSet resultSet,int i) throws SQLException {
        return new Pet(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("species"),
                resultSet.getInt("age"),
                resultSet.getBoolean("gender"),
                resultSet.getString("description"),
                resultSet.getString("breed"),
                resultSet.getInt("house_training"),
                resultSet.getString("behavior")
        );
    }
}
