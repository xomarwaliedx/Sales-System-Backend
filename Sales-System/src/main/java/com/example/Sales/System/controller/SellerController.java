package com.example.Sales.System.controller;

import com.example.Sales.System.dto.ClientDTO;
import com.example.Sales.System.dto.SellerDTO;
import com.example.Sales.System.service.ClientService;
import com.example.Sales.System.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private final SellerService sellerService;

    @PostMapping("")
    public ResponseEntity<Void> createSeller(@RequestBody SellerDTO sellerDTO) {
        logger.info("Creating seller");
        sellerService.createSeller(sellerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
