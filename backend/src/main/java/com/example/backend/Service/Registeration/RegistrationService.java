package com.example.backend.Service.Registeration;

import com.example.backend.DAO.AdopterRepositoryImpl;
import com.example.backend.DAO.StaffRepositoryImpl;
import com.example.backend.DAO.UserRepository;
import com.example.backend.DTO.Request.StaffDTO;
import com.example.backend.DTO.Request.UserDTO;
import com.example.backend.Enums.Role;
import com.example.backend.Exceptions.UserExistsException;
import com.example.backend.Model.Adopter;
import com.example.backend.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserRepository userRepository;
    private AdopterRepositoryImpl adopterRepository;
    private StaffRepositoryImpl staffRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerAdopter(UserDTO userDTO) {
        Adopter adopter = Adopter.builder().build();
        adopterRepository.save(setUserParameters(adopter, userDTO, Role.ADOPTER));
    }

    public void registerStaff(StaffDTO userDTO) {
        Staff staff = Staff.builder()
                        .shelterName(userDTO.getShelterName())
                        .staffRole(userDTO.getStaffRole())
                        .build();
        staffRepository.save(createUser((UserDTO) userDTO, Role.STAFF));
    }

    private User setUserParameters(User user, UserDTO userDTO, Role role) {
//        if (userExists(userDTO))
//            throw new UserExistsException("User already exists");

        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(role);
        user.setEnabled(true);
        return user;
    }

    public boolean userExists(UserDTO userDTO) {
        return userRepository.findByUserName(userDTO.getUserName()).isPresent() ||
                userRepository.findByEmail(userDTO.getEmail()).isPresent();
    }

}

