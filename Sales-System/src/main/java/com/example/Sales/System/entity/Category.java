package com.example.Sales.System.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends HasIntId {
    @NonNull
    @Column(name = "name", nullable = false, unique = true)
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9]).{1,}$", message = "Category name must have at least 1 character or number")
    private String name;

    @Column(name = "description")
    private String description;

}
