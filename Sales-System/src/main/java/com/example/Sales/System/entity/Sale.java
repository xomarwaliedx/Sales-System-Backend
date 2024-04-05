package com.example.Sales.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="sale")
public class Sale extends HasLongId{
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "sale")
    private Set<SaleProduct> saleProducts;

    @Column(name = "total", nullable = false)
    @PositiveOrZero(message = "Total must not be negative")
    private Double total;
}
