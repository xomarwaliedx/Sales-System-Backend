package com.example.Sales.System.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO extends HasLongIdDTO {

    private String name;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private Double totalSpending;

    private String password;

}
