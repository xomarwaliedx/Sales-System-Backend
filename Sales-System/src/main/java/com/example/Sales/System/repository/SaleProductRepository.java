package com.example.Sales.System.repository;

import com.example.Sales.System.entity.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {


    List<SaleProduct> findByCreatedAtBetween(LocalDateTime createdAtStart, LocalDateTime createdAtEnd);
}
