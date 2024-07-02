package com.example.Sales.System.entity;

import com.example.Sales.System.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user")
public class User extends HasLongId implements UserDetails {

    @Column(name = "name", nullable = false)
    @Pattern(regexp = "^(?=.*\\S).{2,}$", message = "Name must have at least 2 non-space characters")
    protected String name;

    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = "^(?=.*\\S).{2,}$", message = "Name must have at least 2 non-space characters")
    protected String lastName;

    @Column(name = "email", nullable = false,unique = true)
    @Email(message = "Please provide a valid email address")
    protected String email;

    @Column(name = "phone", nullable = false,unique = true)
    @Pattern(regexp = "\\+?\\d{10,}", message = "Phone number must be at least 10 digits and may start with a '+'")
    protected String phone;

    @Column(name = "address", nullable = false)
    @Pattern(regexp = "^(?=.*\\S).{2,}$", message = "Name must have at least 2 non-space characters")
    protected String address;

    @Column(name="password",nullable = false)
    protected String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> product;

    @Column(name = "total_spending")
    private Double totalSpending;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
