package com.example.Sales.System.service;

import com.example.Sales.System.dto.SellerDTO;
import com.example.Sales.System.entity.Seller;
import com.example.Sales.System.mapper.Mapper;
import com.example.Sales.System.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final Mapper mapper;
    private final SellerRepository sellerRepository;

    public void createSeller(SellerDTO sellerDTO) {
        Seller seller = mapper.sellerDTOToSeller(sellerDTO);
        sellerRepository.save(seller);
    }
}
