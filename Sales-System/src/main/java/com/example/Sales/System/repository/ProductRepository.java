package com.example.Sales.System.repository;

import com.example.Sales.System.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
