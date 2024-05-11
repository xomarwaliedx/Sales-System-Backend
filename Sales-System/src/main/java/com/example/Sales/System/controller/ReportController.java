package com.example.Sales.System.controller;

import com.example.Sales.System.dto.*;
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
    public ResponseEntity<SalesReportResponseDTO> getSalesReport(@RequestBody SalesReportRequestDTO salesReportRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.salesReport(salesReportRequestDTO));
    }

    @GetMapping("/client")
    public ResponseEntity<ClientReportResponseDTO> getSalesReport() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.clientReport());
    }

    @GetMapping("/product")
    public ResponseEntity<ProductReportResponseDTO> getProductReport() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.productReport());
    }

}
