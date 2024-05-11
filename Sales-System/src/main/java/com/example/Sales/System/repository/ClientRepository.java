package com.example.Sales.System.repository;

import com.example.Sales.System.entity.Client;
import com.example.Sales.System.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select c from Client c order by c.totalSpending DESC")
    List<Client> findByOrderByTotalSpendingDesc();
}
