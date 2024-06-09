package com.example.Sales.System.controller;

import com.example.Sales.System.dto.CreateSaleDTO;
import com.example.Sales.System.dto.SaleDTO;
import com.example.Sales.System.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {

    private final Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private final SalesService salesService;

    @GetMapping("")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        logger.info("Getting all sales");
        return ResponseEntity.status(HttpStatus.OK).body(salesService.getAllSales());
    }

    @PostMapping("")
    public ResponseEntity<Void> createSale(@RequestBody CreateSaleDTO createSaleDTO) {
        logger.info("Creating sale");
        salesService.createSale(createSaleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateSale(@RequestBody SaleDTO saleDTO) {
        logger.info("Updating sale");
        salesService.updateSale(saleDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
