package com.example.Sales.System.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Client")
public class Client extends HasLongId {

    @Column(name = "name", nullable = false)
    @Pattern(regexp = "^(?=.*\\S).{2,}$", message = "Name must have at least 2 non-space characters")
    private String name;

    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = "^(?=.*\\S).{2,}$", message = "Name must have at least 2 non-space characters")
    private String lastName;

    @Column(name = "email", nullable = false,unique = true)
    @Email(message = "Please provide a valid email address")
    private String email;

    @Column(name = "phone", nullable = false,unique = true)
    @Pattern(regexp = "\\+?\\d{10,}", message = "Phone number must be at least 10 digits and may start with a '+'")
    private String phone;

    @Column(name = "address", nullable = false)
    @Pattern(regexp = "^(?=.*\\S).{2,}$", message = "Name must have at least 2 non-space characters")
    private String address;

}
