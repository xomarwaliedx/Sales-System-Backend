package com.example.Sales.System.controller;

import com.example.Sales.System.dto.CategoryDTO;
import com.example.Sales.System.dto.ProductDTO;
import com.example.Sales.System.service.ProductService;
import com.example.Sales.System.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    @Autowired
    private final ReportService reportService;

    @GetMapping("/sales")

}
