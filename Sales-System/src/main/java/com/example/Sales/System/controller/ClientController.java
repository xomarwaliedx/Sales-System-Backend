package com.example.Sales.System.controller;

import com.example.Sales.System.dto.ClientDTO;
import com.example.Sales.System.dto.ProductDTO;
import com.example.Sales.System.service.ClientService;
import com.example.Sales.System.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private final ClientService clientService;

    @GetMapping("")
    public ResponseEntity<List<ClientDTO>> getBuildProperties() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClient());
    }
    @PostMapping("")
    public ResponseEntity<Void> createProduct(@RequestBody ClientDTO clientDTO) {
        clientService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody ClientDTO clientDTO) {
        clientService.updateClient(clientDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
