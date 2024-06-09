package com.example.Sales.System.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO extends HasIntIdDTO {

    private String name;

    private String description;

}
