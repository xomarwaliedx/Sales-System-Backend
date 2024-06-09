package com.example.Sales.System.service;

import com.example.Sales.System.dto.*;
import com.example.Sales.System.entity.Product;
import com.example.Sales.System.entity.Sale;
import com.example.Sales.System.entity.SaleProduct;
import com.example.Sales.System.entity.User;
import com.example.Sales.System.mapper.Mapper;
import com.example.Sales.System.repository.ProductRepository;
import com.example.Sales.System.repository.SaleProductRepository;
import com.example.Sales.System.repository.SalesRepository;
import com.example.Sales.System.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final SalesRepository salesRepository;

    private final UserRepository userRepository;

    private final SaleProductRepository saleProductRepository;

    private final ProductRepository productRepository;

    private final Mapper mapper;

    public SalesReportResponseDTO salesReport(SalesReportRequestDTO salesReportRequestDTO) {
        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(salesReportRequestDTO.getStart()), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(salesReportRequestDTO.getEnd()), ZoneId.systemDefault());
        SalesReportResponseDTO response = new SalesReportResponseDTO();
        response.setNumberOfSales(salesRepository.countByCreatedAtBetween(start, end));
        response.setRevenue(salesRepository.sumTotalByCreatedAtBetween(start, end));
        List<SaleProduct> saleProducts = saleProductRepository.findByCreatedAtBetween(start, end);

        Map<Product, Long> productQuantities = saleProducts.stream()
                .collect(Collectors.groupingBy(SaleProduct::getProduct,
                        Collectors.summingLong(SaleProduct::getQuantity)));

        List<Product> topSellingProducts = productQuantities.entrySet().stream()
                .sorted(Map.Entry.<Product, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<Product> products = saleProducts.stream().map(SaleProduct::getProduct).toList();
        List<ProductDTO> topSellingProductsDTO = mapper.productListToProductDTOList(topSellingProducts);
        response.setTopSellingProducts(topSellingProductsDTO);
        List<User> sellers = products.stream().map(Product::getSeller).toList();
        List<User> topPerformingSellers = sellers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<User, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
        List<UserDTO> topPerformingSellersDTO = mapper.userListToUserDTOList(topPerformingSellers);
        response.setTopPerformingSellers(topPerformingSellersDTO);
        return response;
    }

    @Transactional
    public ClientReportResponseDTO clientReport() {
        ClientReportResponseDTO response = new ClientReportResponseDTO();
        response.setTotalNumberOfClients(userRepository.countClients());
        response.setTopSpendingClients(userRepository.findByRoleClientOrderByTotalSpendingDesc().stream().map(mapper::clientToClientDTO).toList());
        List<Sale> sales = salesRepository.findAll();
        Map<Long, List<SaleProductDTO>> clientActivity = sales.stream()
                .flatMap(sale -> sale.getSaleProducts().stream())
                .collect(Collectors.groupingBy(saleProduct -> saleProduct.getSale().getClient().getId(),
                        Collectors.mapping(mapper::saleProductToSaleProductDTO, Collectors.toList())));
        response.setClientActivity(clientActivity);
        response.setTopBuyingCities(salesRepository.findTopCities());
        return response;
    }

    @Transactional
    public ProductReportResponseDTO productReport() {
        ProductReportResponseDTO response = new ProductReportResponseDTO();
        response.setInventoryStatus(productRepository.findAll().stream().map(mapper::productToProductDTO).toList());
        response.setSalesPerformance(saleProductRepository.findAll().stream()
                .collect(Collectors.groupingBy(saleProduct -> saleProduct.getProduct().getId(),
                        Collectors.summingLong(SaleProduct::getQuantity))));

        response.setPricingAnalysis(saleProductRepository.findAll().stream()
                .collect(Collectors.groupingBy(saleProduct -> saleProduct.getProduct().getId(),
                        Collectors.mapping(SaleProduct::getPrice, Collectors.toList()))));
        return response;

    }

}



