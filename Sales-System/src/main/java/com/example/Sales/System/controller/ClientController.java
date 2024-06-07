package com.example.Sales.System.controller;

import com.example.Sales.System.dto.ClientDTO;
import com.example.Sales.System.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private final ClientService clientService;

    @GetMapping("")
    public ResponseEntity<List<ClientDTO>> getBuildProperties() {
        logger.info("Getting all clients");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClient());
    }
    @PostMapping("")
    public ResponseEntity<Void> createProduct(@RequestBody ClientDTO clientDTO) {
        logger.info("Creating client");
        clientService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody ClientDTO clientDTO) {
        logger.info("Updating client");
        clientService.updateClient(clientDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("Deleting client");
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
