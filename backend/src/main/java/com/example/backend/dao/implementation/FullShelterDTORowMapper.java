package com.example.backend.dao.implementation;

import com.example.backend.dto.Response.FullShelterDTO;
import com.example.backend.dto.Response.StaffMemberDTO;
import com.example.backend.enums.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FullShelterDTORowMapper implements RowMapper<FullShelterDTO> {

    @Override
    public FullShelterDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FullShelterDTO fullShelterDTO = null;

        do {
            if (fullShelterDTO == null) {
                fullShelterDTO = new FullShelterDTO();
                fullShelterDTO.setShelterId(rs.getInt("shelter_id"));
                fullShelterDTO.setName(rs.getString("name"));
                fullShelterDTO.setEmail(rs.getString("email"));
                fullShelterDTO.setLocation(rs.getString("location"));
                fullShelterDTO.setPhone(rs.getString("phone"));
                fullShelterDTO.setStaffMembers(new ArrayList<>());
            }

            StaffMemberDTO staffMemberDTO = new StaffMemberDTO();
            staffMemberDTO.setUserId(rs.getInt("user_id"));
            staffMemberDTO.setEmail(rs.getString("email"));
            staffMemberDTO.setUsername(rs.getString("userName"));
            staffMemberDTO.setFirstName(rs.getString("firstName"));
            staffMemberDTO.setLastName(rs.getString("lastName"));
            staffMemberDTO.setPhone(rs.getString("phone"));
            staffMemberDTO.setRole(Role.valueOf(rs.getString("role")));
            staffMemberDTO.setStaffRole(rs.getString("staff_role"));

            fullShelterDTO.getStaffMembers().add(staffMemberDTO);
        } while (rs.next());

        return fullShelterDTO;
    }
}