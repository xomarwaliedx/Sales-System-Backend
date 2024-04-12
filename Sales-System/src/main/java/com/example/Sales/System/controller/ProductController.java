package com.example.Sales.System.controller;

import com.example.Sales.System.dto.CategoryDTO;
import com.example.Sales.System.dto.ProductDTO;
import com.example.Sales.System.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getBuildProperties() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PostMapping("")
    public ResponseEntity<Void> createProduct(@RequestBody ProductDTO productDTO) {
        productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Void> addCategory(@RequestBody CategoryDTO categoryDTO) {
        productService.addCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
