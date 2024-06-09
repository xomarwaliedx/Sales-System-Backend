package com.example.Sales.System.service;

import com.example.Sales.System.dto.CategoryDTO;
import com.example.Sales.System.dto.ProductDTO;
import com.example.Sales.System.entity.Category;
import com.example.Sales.System.entity.Product;
import com.example.Sales.System.entity.User;
import com.example.Sales.System.enums.Role;
import com.example.Sales.System.errors.WrongUserType;
import com.example.Sales.System.mapper.Mapper;
import com.example.Sales.System.repository.CategoryRepository;
import com.example.Sales.System.repository.ProductRepository;
import com.example.Sales.System.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts() {
        return mapper.productListToProductDTOList(productRepository.findAll());
    }

    public void createProduct(ProductDTO productDTO) {
        Product product = mapper.productDTOToProduct(productDTO);
        User user = userRepository.findById(productDTO.getSeller().getId()).orElseThrow();
        if (user.getRole()!= Role.SELLER &&user.getRole()!= Role.BOTH)
            throw new WrongUserType("not a seller");
        product.setSeller(user);
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
        if (productDTO.getSeller() != null) {
            User user=userRepository.findById(productDTO.getSeller().getId()).orElseThrow();
            if(user.getRole()!=Role.SELLER &&user.getRole()!=Role.BOTH)
                throw new WrongUserType("not a seller");
            product.setSeller(user);
        }
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

    public void addCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findByName(categoryDTO.getName());
        if (category == null) {
            category = mapper.categoryDTOToCategory(categoryDTO);
            categoryRepository.save(category);
        }else {
            throw new RuntimeException("Category already exists");
        }
    }
}
