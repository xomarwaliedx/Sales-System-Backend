package com.example.Sales.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="sale")
public class Sale extends HasLongId{
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "sale",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SaleProduct> saleProducts;

    @Column(name = "total", nullable = false)
    @PositiveOrZero(message = "Total must not be negative")
    private Double total;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;
}
