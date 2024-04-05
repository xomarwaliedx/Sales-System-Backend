package com.example.Sales.System.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class SaleDTO extends HasLongIdDTO {

    private ClientDTO client;

    private Set<SaleProductDTO> products;

    private LocalDateTime createdAt;

    private Double total;

}
