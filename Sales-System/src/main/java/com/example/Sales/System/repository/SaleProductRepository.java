package com.example.Sales.System.repository;

import com.example.Sales.System.entity.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {
}
