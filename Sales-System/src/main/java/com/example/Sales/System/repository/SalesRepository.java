package com.example.Sales.System.repository;

import com.example.Sales.System.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SalesRepository extends JpaRepository<Sale, Long> {

    @Query("select count(s) from Sale s where s.createdAt between ?1 and ?2")
    long countByCreatedAtBetween(LocalDateTime createdAtStart, LocalDateTime createdAtEnd);

    @Query("select sum(s.total) from Sale s where s.createdAt between ?1 and ?2")
    Double sumTotalByCreatedAtBetween(LocalDateTime createdAtStart, LocalDateTime createdAtEnd);

    @Query("select s.city from Sale s group by s.city order by count(s) desc")
    List<String> findTopCities();
}
