package com.example.Sales.System.controller;

import com.example.Sales.System.dto.UserDTO;
import com.example.Sales.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        logger.info("Creating user");
        userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getUsers() {
        logger.info("Getting all users");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO) {
        logger.info("Updating user");
        userService.updateUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Deleting client");
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
