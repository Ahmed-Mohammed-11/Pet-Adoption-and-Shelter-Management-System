package com.example.backend.Service.Registeration;

import com.example.backend.DAO.implementation.AdopterRepositoryImpl;
import com.example.backend.DAO.implementation.ManagerRepositoryImpl;
import com.example.backend.DAO.implementation.StaffRepositoryImpl;
import com.example.backend.DAO.Repository.UserRepository;
import com.example.backend.DTO.Request.StaffDTO;
import com.example.backend.DTO.Request.UserDTO;
import com.example.backend.Enums.Role;
import com.example.backend.Exceptions.UserExistsException;
import com.example.backend.Model.users.Adopter;
import com.example.backend.Model.users.ShelterManager;
import com.example.backend.Model.users.StaffMember;
import com.example.backend.Model.users.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserRepository userRepository;
    private AdopterRepositoryImpl adopterRepository;
    private StaffRepositoryImpl staffRepository;
    private ManagerRepositoryImpl managerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerAdopter(UserDTO userDTO) {
        if(userExists(userDTO))
            throw new UserExistsException("User already exists");

        Adopter adopter = Adopter.builder().build();
        setUserParameters(adopter, userDTO, Role.ADOPTER);

        adopterRepository.save(adopter);
    }

    public void registerStaff(StaffDTO staffDTO) {
        if(userExists(staffDTO))
            throw new UserExistsException("User already exists");

        StaffMember staff = StaffMember.builder()
                        .shelterName(staffDTO.getShelterName())
                        .staffRole(staffDTO.getStaffRole())
                        .build();
        setUserParameters(staff, staffDTO, Role.STAFF);
        staffRepository.save(staff);
    }

    public void registerManager(UserDTO userDTO) {
        if(userExists(userDTO))
            throw new UserExistsException("User already exists");

        ShelterManager manager = ShelterManager.builder().build();
        setUserParameters(manager, userDTO, Role.SHELTER_MANAGER);

        managerRepository.save(manager);
    }

    private User setUserParameters(User user, UserDTO userDTO, Role role) {

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

