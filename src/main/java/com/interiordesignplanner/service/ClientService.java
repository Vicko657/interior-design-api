package com.interiordesignplanner.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.interiordesignplanner.dto.ClientDTO;
import com.interiordesignplanner.dto.ClientDTOMapper;
import com.interiordesignplanner.entity.Client;
import com.interiordesignplanner.exception.ClientNotFoundException;
import com.interiordesignplanner.repository.ClientRepository;

@Service
public class ClientService {

    public ClientRepository clientRepository;
    private final ClientDTOMapper clientDTOMapper;

    public ClientService(ClientRepository clientRepository, ClientDTOMapper clientDTOMapper) {
        this.clientRepository = clientRepository;
        this.clientDTOMapper = clientDTOMapper;
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientDTOMapper)
                .collect(Collectors.toList());
    }

    public ClientDTO getClient(Long id) {
        return clientRepository.findById(id)
                .map(clientDTOMapper)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public Client getClientEntity(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public Client createClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client must not be null");
        }

        if (client.getId() != null && clientRepository.existsById(client.getId())) {
            throw new OptimisticLockingFailureException("ID" + client.getId() + "was not found");
        }
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updateClient) {
        Client existingClientId = getClientEntity(id);
        existingClientId.setFirstName(updateClient.getFirstName());
        existingClientId.setLastName(updateClient.getLastName());
        existingClientId.setEmail(updateClient.getEmail());
        existingClientId.setPhoneNo(updateClient.getPhoneNo());
        existingClientId.setAddress(updateClient.getAddress());
        existingClientId.setNotes(updateClient.getNotes());

        return clientRepository.save(existingClientId);
    }

    public Client deleteClient(Long id) {
        Client client = getClientEntity(id);
        clientRepository.deleteById(id);
        return client;
    }

}
