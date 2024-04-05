package com.example.Sales.System.service;

import com.example.Sales.System.dto.CategoryDTO;
import com.example.Sales.System.dto.ProductDTO;
import com.example.Sales.System.entity.Category;
import com.example.Sales.System.entity.Product;
import com.example.Sales.System.entity.Seller;
import com.example.Sales.System.mapper.Mapper;
import com.example.Sales.System.repository.CategoryRepository;
import com.example.Sales.System.repository.ProductRepository;
import com.example.Sales.System.repository.SellerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final Mapper mapper;
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts() {
        return mapper.productListToProductDTOList(productRepository.findAll());
    }

    public void createProduct(ProductDTO productDTO) {
        Product product = mapper.productDTOToProduct(productDTO);
        Seller seller = sellerRepository.findById(productDTO.getSeller().getId()).orElseThrow();
        product.setSeller(seller);
        HashSet<Category> categories = new HashSet<>();
        for (CategoryDTO categoryDTO : productDTO.getCategory()) {
            Category category = categoryRepository.findByName(categoryDTO.getName());
            if (category == null) {
                category = new Category(categoryDTO.getName());
                category.setDescription(categoryDTO.getDescription());
                category = categoryRepository.save(category);
            }
            categories.add(category);
        }
        product.setCategory(categories);
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId()).orElseThrow();
        if (productDTO.getName() != null)
            product.setName(productDTO.getName());
        if (productDTO.getPrice() != null)
            product.setPrice(productDTO.getPrice());
        if (productDTO.getAvailableQuantity() != null)
            product.setAvailableQuantity(productDTO.getAvailableQuantity());
        if (productDTO.getSeller() != null)
            product.setSeller(sellerRepository.findById(productDTO.getSeller().getId()).orElseThrow());
        if (productDTO.getPrice() != null)
            product.setPrice(productDTO.getPrice());
        if (productDTO.getCategory() != null) {
            HashSet<Category> categories = new HashSet<>();
            for (CategoryDTO categoryDTO : productDTO.getCategory()) {
                Category category = categoryRepository.findByName(categoryDTO.getName());
                if (category == null) {
                    category = new Category(categoryDTO.getName());
                    category.setDescription(categoryDTO.getDescription());
                    category = categoryRepository.save(category);
                }
                categories.add(category);
            }
            product.setCategory(categories);
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
