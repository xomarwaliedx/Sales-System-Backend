package com.example.Sales.System.service;

import com.example.Sales.System.dto.ClientDTO;
import com.example.Sales.System.entity.Client;
import com.example.Sales.System.mapper.Mapper;
import com.example.Sales.System.repository.CategoryRepository;
import com.example.Sales.System.repository.ClientRepository;
import com.example.Sales.System.repository.ProductRepository;
import com.example.Sales.System.repository.SellerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final Mapper mapper;
    private final ClientRepository clientRepository;

    public List<ClientDTO> getAllClient() {
        return mapper.clientListToClientDTOList(clientRepository.findAll());
    }

    public void createClient(ClientDTO clientDTO) {
        Client client = mapper.clientDTOToClient(clientDTO);
        client.setTotalSpending(0.0);
        clientRepository.save(client);
    }

    @Transactional
    public void updateClient(ClientDTO clientDTO) {
        Client client = clientRepository.findById(clientDTO.getId()).orElseThrow();
        if (clientDTO.getName() != null)
            client.setName(clientDTO.getName());
        if (clientDTO.getLastName() != null)
            client.setLastName(clientDTO.getLastName());
        if (clientDTO.getEmail() != null)
            client.setEmail(clientDTO.getEmail());
        if (clientDTO.getPhone() != null)
            client.setPhone(clientDTO.getPhone());
        if (clientDTO.getAddress() != null)
            client.setAddress(clientDTO.getAddress());
        clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }


}
