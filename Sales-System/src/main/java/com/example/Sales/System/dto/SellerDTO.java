package com.example.Sales.System.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerDTO extends HasLongIdDTO {

    private String name;

    private String lastName;

    private String email;

    private String phone;

    private String address;

}
