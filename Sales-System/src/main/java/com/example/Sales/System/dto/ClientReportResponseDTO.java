package com.example.Sales.System.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ClientReportResponseDTO {

    private Long totalNumberOfClients;

    private List<ClientDTO>  topSpendingClients;

    private Map<Long,List<SaleProductDTO>>  clientActivity;

    private List<String>  topBuyingCities;
}
