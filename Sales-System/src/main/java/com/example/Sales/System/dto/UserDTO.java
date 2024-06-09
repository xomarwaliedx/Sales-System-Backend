package com.example.Sales.System.dto;

import com.example.Sales.System.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends HasLongIdDTO {

    private String name;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private String password;

    private Double totalSpending;

    private Role role;

}
