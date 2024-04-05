package com.example.Sales.System.dto;

import com.example.Sales.System.entity.Product;
import com.example.Sales.System.entity.Sale;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SaleProductDTO extends HasLongIdDTO {

    private SaleDTO sale;

    private ProductDTO product;

    private Double price;

    private Integer quantity;

}
