package com.example.Sales.System.service;

import com.example.Sales.System.dto.CreateSaleDTO;
import com.example.Sales.System.dto.SaleDTO;
import com.example.Sales.System.dto.SaleProductDTO;
import com.example.Sales.System.entity.Client;
import com.example.Sales.System.entity.Product;
import com.example.Sales.System.entity.Sale;
import com.example.Sales.System.entity.SaleProduct;
import com.example.Sales.System.mapper.Mapper;
import com.example.Sales.System.repository.ClientRepository;
import com.example.Sales.System.repository.ProductRepository;
import com.example.Sales.System.repository.SaleProductRepository;
import com.example.Sales.System.repository.SalesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final Mapper mapper;
    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;
    private final SaleProductRepository saleProductRepository;
    private final ClientRepository clientRepository;


    public List<SaleDTO> getAllSales() {
        System.err.println(mapper.salesListToSalesDTOList(salesRepository.findAll()).get(3).toString());
        return mapper.salesListToSalesDTOList(salesRepository.findAll());
    }

    @Transactional
    public void createSale(CreateSaleDTO createSaleDTO) {
        Sale sale = mapper.createSalesDTOToSales(createSaleDTO);
        sale.setTotal(0.0);
        sale = salesRepository.save(sale);
        Set<SaleProduct> saleProducts = new HashSet<>();
        double total = 0;
        for (SaleProductDTO saleProductDTO : createSaleDTO.getProducts()) {
            Long productId = saleProductDTO.getProduct().getId();
            Product product = productRepository.findById(productId).orElse(null);
            if(product.getAvailableQuantity()<saleProductDTO.getQuantity()){
                throw new RuntimeException("Not enough available quantity for product with id "+productId);
            }
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setProduct(product);
            if (saleProductDTO.getPrice() != null) {
                double price = saleProductDTO.getPrice();
                saleProduct.setPrice(price);
                total += price;
            } else {
                double price=product.getPrice();
                saleProduct.setPrice(price);
                total+=price;
            }
            saleProduct.setQuantity(saleProductDTO.getQuantity());
            saleProduct.setSale(sale);
            saleProducts.add(saleProduct);
            saleProductRepository.save(saleProduct);
            assert product != null;
            product.setAvailableQuantity(product.getAvailableQuantity() - saleProductDTO.getQuantity());
        }
        sale.setTotal(total);
        sale.setAddress(createSaleDTO.getAddress());
        sale.setCity(createSaleDTO.getCity());
        sale.setSaleProducts(saleProducts);
        Client client = clientRepository.findById(createSaleDTO.getClientId()).orElse(null);
        client.setTotalSpending(client.getTotalSpending() + total);
        clientRepository.save(client);
        salesRepository.save(sale);
    }

    public void updateSale(SaleDTO saleDTO) {
        Sale sale = salesRepository.findById(saleDTO.getId()).orElse(null);
        Set<SaleProduct> saleProducts = new HashSet<>();
        double total=0;
        for (SaleProductDTO saleProductDTO : saleDTO.getSaleProducts()) {
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setPrice(saleProductDTO.getPrice());
            total+=saleProduct.getPrice();
            saleProduct.setQuantity(saleProductDTO.getQuantity());
            saleProducts.add(saleProduct);
        }
        sale.setTotal(total);
        if (saleDTO.getAddress() != null) {
            sale.setAddress(saleDTO.getAddress());
        }
        if (saleDTO.getCity() != null) {
            sale.setCity(saleDTO.getCity());
        }
        sale.setSaleProducts(saleProducts);
        salesRepository.save(sale);
    }
}
