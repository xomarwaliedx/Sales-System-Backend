package com.example.Sales.System.mapper;

import com.example.Sales.System.dto.*;
import com.example.Sales.System.entity.*;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface Mapper {
    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);

    Set<CategoryDTO> categoryListToCategoryDTOList(Set<Category> categories);

    Set<Category> categoryDTOListToCategoryList(Set<CategoryDTO> categoryDTOs);

    Seller sellerDTOToSeller(SellerDTO sellerDTO);

    SellerDTO sellerToSellerDTO(Seller seller);

    List<SellerDTO> sellerListToSellerDTOList(List<Seller> sellers);

    Set<Seller> sellerDTOListToSellerList(Set<SellerDTO> sellerDTOs);

    @Mapping(target = "category", ignore = true)
    Product productDTOToProduct(ProductDTO productDTO);

    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productListToProductDTOList(List<Product> products);

    List<Product> productDTOListToProductList(List<ProductDTO> productDTOs);

    Client clientDTOToClient(ClientDTO clientDTO);

    ClientDTO clientToClientDTO(Client client);

    List<ClientDTO> clientListToClientDTOList(List<Client> clients);

    List<Client> clientDTOListToClientList(List<ClientDTO> clientDTOs);

    SaleDTO salesToSalesDTO(Sale sales);

    Sale salesDTOToSales(SaleDTO saleDTO);

    List<SaleDTO> salesListToSalesDTOList(List<Sale> sales);

    List<Sale> salesDTOListToSalesList(List<SaleDTO> saleDTOS);

    @Mapping(target = "clientId", source = "client.id")
    CreateSaleDTO salesToCreateSalesDTO(Sale sales);

    @Mapping(target = "client.id", source = "clientId")
    Sale createSalesDTOToSales(CreateSaleDTO createSaleDTO);

    List<CreateSaleDTO> salesListToCreateSalesDTOList(List<Sale> sales);

    List<Sale> createSalesDTOListToSalesList(List<CreateSaleDTO> createSaleDTOList);

    @Mapping(target = "saleId", source = "sale.id")
    SaleProductDTO saleProductToSaleProductDTO(SaleProduct saleProduct);

    @Mapping(target = "sale.id", source = "saleId")
    SaleProduct saleProductDTOToSaleProduct(SaleProductDTO saleProductDTO);

    List<SaleProductDTO> saleProductListToSaleProductDTOList(List<SaleProduct> saleProducts);

    List<SaleProduct> saleProductDTOListToSaleProductList(List<SaleProductDTO> saleProductDTOs);
}
