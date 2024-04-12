package com.example.Sales.System.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class CreateSaleDTO extends HasLongIdDTO {

    private Long clientId;

    private Set<SaleProductDTO> products;

    private LocalDateTime createdAt;

}
