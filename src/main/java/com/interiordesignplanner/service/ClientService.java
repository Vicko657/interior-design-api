package com.interiordesignplanner.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.interiordesignplanner.entity.Client;
import com.interiordesignplanner.repository.ClientRepository;

@Service
public class ClientService {

    public ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return this.clientRepository.findAll();
    }

    public Client getClient(Long id) throws NoSuchElementException {
        return this.clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID" + id + "was not found"));
    }

    public Client createClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client must not be null");
        }

        if (client.getId() != null && this.clientRepository.existsById(client.getId())) {
            throw new OptimisticLockingFailureException("ID" + client.getId() + "was not found");
        }

        return this.clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updateClient) throws NoSuchElementException {
        Client existingClientId = this.clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID" + id + "was not found"));
        existingClientId.setName(updateClient.getName());
        existingClientId.setEmail(updateClient.getEmail());
        existingClientId.setPhoneNo(updateClient.getPhoneNo());
        existingClientId.setAddress(updateClient.getAddress());
        existingClientId.setNotes(updateClient.getNotes());

        return this.clientRepository.save(existingClientId);
    }

    public void deleteClient(Long id) {

        if (!this.clientRepository.existsById(id)) {
            throw new NoSuchElementException("ID" + id + "was not found");
        }
        this.clientRepository.deleteById(id);
    }

}
