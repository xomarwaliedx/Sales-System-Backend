package com.example.Sales.System.dto;

import com.example.Sales.System.entity.HasLongId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class ProductDTO extends HasLongId {
    private String name;

    private String description;

    private Set<CategoryDTO> category;

    private LocalDateTime createdAt;

    private UserDTO seller;

    private Integer availableQuantity;

    private Double price;

}
