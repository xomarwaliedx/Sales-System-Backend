package com.example.Sales.System.repository;

import com.example.Sales.System.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {


    @Query("SELECT t FROM Token t WHERE t.user.id = ?1 AND t.loggedOut = false")
    List<Token> findActiveTokensByUserId(Long userId);

    @Query("SELECT t FROM Token t WHERE t.token = ?1")
    Optional<Token> findByToken(String token);
}
