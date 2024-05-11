package com.example.Sales.System.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProductReportResponseDTO {

    private List<ProductDTO> inventoryStatus;

    private Map<Long,Long> salesPerformance;

    private Map<Long,List<Double>> pricingAnalysis;

}
