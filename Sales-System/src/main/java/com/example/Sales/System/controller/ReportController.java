package com.example.Sales.System.controller;

import com.example.Sales.System.dto.ClientReportResponseDTO;
import com.example.Sales.System.dto.ProductReportResponseDTO;
import com.example.Sales.System.dto.SalesReportRequestDTO;
import com.example.Sales.System.dto.SalesReportResponseDTO;
import com.example.Sales.System.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

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
