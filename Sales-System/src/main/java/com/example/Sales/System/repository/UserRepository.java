package com.example.Sales.System.repository;

import com.example.Sales.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select c from User c where c.role = com.example.Sales.System.enums.Role.CLIENT order by c.totalSpending DESC")
    List<User> findByRoleClientOrderByTotalSpendingDesc();

    @Query("select count(c) from User c where c.role = 'client'")
    long countClients();

    Optional<User> findByEmail(String email);
}
