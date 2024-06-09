package com.example.Sales.System.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleProductDTO extends HasLongIdDTO {

    private Long saleId;

    private ProductDTO product;

    private Double price;

    private Integer quantity;

}
