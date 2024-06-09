package com.example.Sales.System.service;

import com.example.Sales.System.dto.UserDTO;
import com.example.Sales.System.entity.User;
import com.example.Sales.System.enums.Role;
import com.example.Sales.System.mapper.Mapper;
import com.example.Sales.System.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Mapper mapper;
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;


    public List<UserDTO> getAllUsers() {
        return mapper.userListToUserDTOList(userRepository.findAll());
    }

    public void createUser(UserDTO userDTO) {

        User user = mapper.userDTOToUser(userDTO);
//        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        String encodedPassword =userDTO.getPassword();
        user.setPassword(encodedPassword);
        if (userDTO.getRole() == Role.BOTH || userDTO.getRole() == Role.CLIENT) user.setTotalSpending(0.0);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow();
        if (userDTO.getName() != null) user.setName(userDTO.getName());
        if (userDTO.getLastName() != null) user.setLastName(userDTO.getLastName());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getPhone() != null) user.setPhone(userDTO.getPhone());
        if (userDTO.getAddress() != null) user.setAddress(userDTO.getAddress());
        if (userDTO.getPassword() != null) {
//            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            String encodedPassword = userDTO.getPassword();
            user.setPassword(encodedPassword);
        }
        if (userDTO.getRole() != null && userDTO.getTotalSpending() != null)
            if (userDTO.getRole() == Role.BOTH || userDTO.getRole() == Role.CLIENT)
                user.setTotalSpending(user.getTotalSpending());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
