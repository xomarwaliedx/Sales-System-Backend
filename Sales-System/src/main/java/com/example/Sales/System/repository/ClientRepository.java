package com.example.Sales.System.repository;

import com.example.Sales.System.entity.Client;
import com.example.Sales.System.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
