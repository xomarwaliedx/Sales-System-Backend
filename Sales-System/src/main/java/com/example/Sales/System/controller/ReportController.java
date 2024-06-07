package com.example.Sales.System.controller;

import com.example.Sales.System.dto.*;
import com.example.Sales.System.service.ProductService;
import com.example.Sales.System.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private final ReportService reportService;

    @GetMapping("/sales")
    public ResponseEntity<SalesReportResponseDTO> getSalesReport(@RequestBody SalesReportRequestDTO salesReportRequestDTO) {
        logger.info("Getting sales report");
        return ResponseEntity.status(HttpStatus.OK).body(reportService.salesReport(salesReportRequestDTO));
    }

    @GetMapping("/client")
    public ResponseEntity<ClientReportResponseDTO> getSalesReport() {
        logger.info("Getting client report");
        return ResponseEntity.status(HttpStatus.OK).body(reportService.clientReport());
    }

    @GetMapping("/product")
    public ResponseEntity<ProductReportResponseDTO> getProductReport() {
        logger.info("Getting product report");
        return ResponseEntity.status(HttpStatus.OK).body(reportService.productReport());
    }

}
