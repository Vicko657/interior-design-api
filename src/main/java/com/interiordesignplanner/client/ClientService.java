package com.interiordesignplanner.client;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClient(Long id) throws NoSuchElementException {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public List<Client> getByLastNameIgnoreCase(String lastName) {

        lastName.equalsIgnoreCase(lastName);

        return clientRepository.findByLastNameIgnoreCase(lastName);

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
        Client existingClientId = getClient(id);
        existingClientId.setFirstName(updateClient.getFirstName());
        existingClientId.setLastName(updateClient.getLastName());
        existingClientId.setEmail(updateClient.getEmail());
        existingClientId.setPhone(updateClient.getPhone());
        existingClientId.setAddress(updateClient.getAddress());
        existingClientId.setNotes(updateClient.getNotes());

        return clientRepository.save(existingClientId);
    }

    public Client deleteClient(Long id) {
        Client client = getClient(id);
        clientRepository.deleteById(id);
        return client;
    }

}
