package com.example.Sales.System.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class SaleDTO extends HasLongIdDTO {

    private ClientDTO client;

    private Set<SaleProductDTO> saleProducts;

    private LocalDateTime createdAt;

    private Double total;

    private String address;

    private String city;


}
