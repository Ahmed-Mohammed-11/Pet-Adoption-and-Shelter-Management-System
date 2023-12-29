package com.example.backend.Service;

import com.example.backend.DAO.implementation.StaffRepositoryImpl;
import com.example.backend.DAO.Repository.UserRepository;
import com.example.backend.DTO.Response.UserResponseDTO;
import com.example.backend.Enums.Role;
import com.example.backend.Model.users.StaffMember;
import com.example.backend.Model.users.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StaffRepositoryImpl staffRepository;

    public UserResponseDTO getUser(String username) {
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();

        if(user.getRole().equals(Role.STAFF)){
            StaffMember staffMember = staffRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
            userResponseDTO.setShelterName(staffMember.getShelterName());
            userResponseDTO.setStaffRole(staffMember.getStaffRole());
        }

        return userResponseDTO;
    }

}
