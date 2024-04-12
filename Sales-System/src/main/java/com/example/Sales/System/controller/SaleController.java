package com.example.Sales.System.controller;

import com.example.Sales.System.dto.ClientDTO;
import com.example.Sales.System.dto.CreateSaleDTO;
import com.example.Sales.System.dto.SaleDTO;
import com.example.Sales.System.service.ClientService;
import com.example.Sales.System.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {

    @Autowired
    private final SalesService salesService;

    @GetMapping("")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        return ResponseEntity.status(HttpStatus.OK).body(salesService.getAllSales());
    }

    @PostMapping("")
    public ResponseEntity<Void> createSale(@RequestBody CreateSaleDTO createSaleDTO) {
        salesService.createSale(createSaleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateSale(@RequestBody SaleDTO saleDTO) {
        salesService.updateSale(saleDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
