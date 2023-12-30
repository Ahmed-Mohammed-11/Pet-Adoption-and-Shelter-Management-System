package com.example.backend.service;

import com.example.backend.dao.implementation.StaffRepositoryImpl;
import com.example.backend.dao.Repository.UserRepository;
import com.example.backend.dto.Response.UserResponseDTO;
import com.example.backend.enums.Role;
import com.example.backend.model.users.StaffMember;
import com.example.backend.model.users.User;
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
            userResponseDTO.setShelterId(staffMember.getShelterId());
            userResponseDTO.setStaffRole(staffMember.getStaffRole());
        }

        return userResponseDTO;
    }
    public UserResponseDTO getUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("username not found"));
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
            StaffMember staffMember = staffRepository.findByUserId(id).orElseThrow(() -> new UsernameNotFoundException("username not found"));
            userResponseDTO.setShelterId(staffMember.getShelterId());
            userResponseDTO.setStaffRole(staffMember.getStaffRole());
        }

        return userResponseDTO;
    }

}
