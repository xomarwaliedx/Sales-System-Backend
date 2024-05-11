package com.example.Sales.System.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalesReportResponseDTO {

    private Long numberOfSales;

    private Double revenue;

    private List<ProductDTO> topSellingProducts;

    private List<SellerDTO> topPerformingSellers;

}
