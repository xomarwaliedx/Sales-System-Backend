package com.example.Sales.System.repository;

import com.example.Sales.System.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


    Category findByName(String name);
}
