package com.example.Sales.System.dto;

import com.example.Sales.System.entity.HasLongId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO extends HasIntIdDTO {

    private String name;

    private String description;

}
