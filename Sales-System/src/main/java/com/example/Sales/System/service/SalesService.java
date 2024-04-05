package com.example.Sales.System.service;

import com.example.Sales.System.dto.ProductDTO;
import com.example.Sales.System.dto.SaleDTO;
import com.example.Sales.System.dto.SaleProductDTO;
import com.example.Sales.System.entity.Client;
import com.example.Sales.System.entity.Product;
import com.example.Sales.System.entity.Sale;
import com.example.Sales.System.entity.SaleProduct;
import com.example.Sales.System.mapper.Mapper;
import com.example.Sales.System.repository.ClientRepository;
import com.example.Sales.System.repository.ProductRepository;
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
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;


    public List<SaleDTO> getAllSales() {
        return mapper.salesListToSalesDTOList(salesRepository.findAll());
    }

    @Transactional
    public void createSales(SaleDTO saleDTO) {
        Sale sale = mapper.salesDTOToSales(saleDTO);
        Client client = clientRepository.findById(saleDTO.getClient().getId()).orElse(null);
        sale.setClient(client);
        sale = salesRepository.save(sale);
        Set<SaleProduct> saleProducts = new HashSet<>();
        for (SaleProductDTO saleProductDTO : saleDTO.getProducts()) {
            Long productId = saleProductDTO.getProduct().getId();
            Product product = productRepository.findById(productId).orElse(null);
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setProduct(product);
            if(saleProductDTO.getPrice()!=null)
                saleProduct.setPrice(saleProductDTO.getPrice());
            else
                saleProduct.setPrice(product.getPrice());
            saleProduct.setQuantity(saleProductDTO.getQuantity());
            saleProduct.setSale(sale);
            saleProducts.add(saleProduct);
        }
        sale.setSaleProducts(saleProducts);
        salesRepository.save(sale);
    }

    public void updateSale(SaleDTO saleDTO) {
        Sale sale = salesRepository.findById(saleDTO.getId()).orElse(null);
        Set<SaleProduct> saleProducts = new HashSet<>();
        for (SaleProductDTO saleProductDTO : saleDTO.getProducts()) {
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setPrice(saleProductDTO.getPrice());
            saleProduct.setQuantity(saleProductDTO.getQuantity());
            saleProducts.add(saleProduct);
        }
        sale.setSaleProducts(saleProducts);
        salesRepository.save(sale);
    }
}
