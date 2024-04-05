package com.example.Sales.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "product")
public class Product extends HasLongId {

    @Column(name = "name", nullable = false)
    @Pattern(regexp = "^(?=.*\\S).{2,}$", message = "Name must have at least 2 non-space characters")
    private String name;

    @Column(name = "description", nullable = false)
    @Pattern(regexp = "^(?=.*\\S).{2,}$", message = "Describtion must have at least 2 non-space characters")
    private String description;

    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id",
                    nullable = false, updatable = false, insertable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id",
                    nullable = false, updatable = false, insertable = false))
    private Set<Category> category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Column(name = "available_quantity", nullable = false)
    @PositiveOrZero(message = "Quantity must not be negative")
    private Integer availableQuantity;

    @Column(name = "price", nullable = false)
    @PositiveOrZero(message = "Price must not be negative")
    private Double price;

}
