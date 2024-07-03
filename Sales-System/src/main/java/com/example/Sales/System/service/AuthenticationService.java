package com.example.Sales.System.service;

import com.example.Sales.System.dto.AuthenticationDTO;
import com.example.Sales.System.dto.AuthenticationResponseDTO;
import com.example.Sales.System.dto.UserDTO;
import com.example.Sales.System.entity.User;
import com.example.Sales.System.enums.Role;
import com.example.Sales.System.mapper.Mapper;
import com.example.Sales.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final Mapper mapper;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(UserDTO userDTO) {
        if (userDTO.getRole() == Role.ADMIN) {
            if (userRepository.countByRole(Role.ADMIN) > 0)
                throw new RuntimeException("Admin cannot be registered");
        }
        User user = mapper.userDTOToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return new AuthenticationResponseDTO(jwtService.generateToken(user));
    }

    public AuthenticationResponseDTO login(AuthenticationDTO authenticationDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword()));
        User user = userRepository.findByEmail(authenticationDTO.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(token);
    }
}
