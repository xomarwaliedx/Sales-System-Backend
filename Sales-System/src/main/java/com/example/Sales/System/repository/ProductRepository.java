package com.example.Sales.System.repository;

import com.example.Sales.System.entity.Product;
import com.example.Sales.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySeller(User seller);
}
