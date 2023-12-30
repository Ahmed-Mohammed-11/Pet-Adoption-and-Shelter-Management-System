package com.example.backend.service.registeration;

import com.example.backend.dao.Repository.UserRepository;
import com.example.backend.dao.implementation.AdopterRepositoryImpl;
import com.example.backend.dao.implementation.ManagerRepositoryImpl;
import com.example.backend.dao.implementation.ShelterRepositoryImpl;
import com.example.backend.dao.implementation.StaffRepositoryImpl;
import com.example.backend.dto.Request.StaffDTO;
import com.example.backend.dto.Request.UserDTO;
import com.example.backend.enums.Role;
import com.example.backend.exceptions.exception.UserExistsException;
import com.example.backend.model.users.Adopter;
import com.example.backend.model.users.ShelterManager;
import com.example.backend.model.users.StaffMember;
import com.example.backend.model.users.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserRepository userRepository;
    private AdopterRepositoryImpl adopterRepository;
    private StaffRepositoryImpl staffRepository;
    private ManagerRepositoryImpl managerRepository;
    private ShelterRepositoryImpl shelterRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerAdopter(UserDTO userDTO) {
        if (userExists(userDTO))
            throw new UserExistsException("User already exists");

        Adopter adopter = Adopter.builder().build();
        setUserParameters(adopter, userDTO, Role.ADOPTER);

        adopterRepository.save(adopter);
    }

    public void registerStaff(StaffDTO staffDTO) {
        if (userExists(staffDTO))
            throw new UserExistsException("User already exists");

        int shelterId = shelterRepository
                .findByName(staffDTO.getShelterName())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"))
                .getShelterId();

        StaffMember staff = StaffMember.builder()
                .shelterId(shelterId)
                .staffRole(staffDTO.getStaffRole())
                .build();
        setUserParameters(staff, staffDTO, Role.STAFF);
        staffRepository.save(staff);
    }

    public void registerManager(UserDTO userDTO) {
        if (userExists(userDTO))
            throw new UserExistsException("User already exists");

        ShelterManager manager = ShelterManager.builder().build();
        setUserParameters(manager, userDTO, Role.SHELTER_MANAGER);

        managerRepository.save(manager);
    }

    private User setUserParameters(User user, UserDTO userDTO, Role role) {

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(role);
        return user;
    }

    public boolean userExists(UserDTO userDTO) {
        return userRepository.findByUserName(userDTO.getUsername()).isPresent() ||
                userRepository.findByEmail(userDTO.getEmail()).isPresent();
    }

}

