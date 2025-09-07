package com.interiordesignplanner.client;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

/**
 * Client service class provides business logic and operations relating to a
 * client.
 * 
 * <p>
 * Creates, updates, retrieves and deletes client records, validating client
 * information, and managing relationships between clients and projects. Serves
 * as an interface between controllers and the persistence layer.
 * </p>
 */
@Service
public class ClientService {

    // Client CRUD Interface
    public ClientRepository clientRepository;

    // Constructor
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Returns all active clients and their details on the system.
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Returns a client using their clientId.
     * 
     * <p>
     * This method is used to retrieve a specific client
     * entity for CRUD operations.
     * </p>
     * 
     * @param id client's unique identifier
     * @throws ClientNotFoundException if the client is not found
     */
    public Client getClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " was not found"));
    }

    /**
     * Retrives a client using their lastname.
     * 
     * <p>
     * Retrieves a specific client entity by
     * their lastName. Custom query created in the repository.
     * </p>
     * 
     * @param lastName client's lastname
     * @throws ClientNotFoundException if the client is not found
     */
    public List<Client> getClientsByLastName(String lastName) throws ClientNotFoundException {

        List<Client> clients = clientRepository.findByLastNameIgnoreCase(lastName);
        if (clients == null) {
            throw new ClientNotFoundException("No clients found with the lastname" + lastName);
        }
        return clients;
    }

    /**
     * Create a new client on the system.
     * 
     * <p>
     * Used to register a new client on the system and
     * automatically assigned a unique identifier.
     * </p>
     * 
     * @param client the client object is created
     * @return client with a generated unique Id
     * @throws IllegalArgumentException if the client fields is null
     */
    public Client createClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client must not be null");
        }
        if (client.getId() != null && clientRepository.existsById(client.getId())) {
            throw new OptimisticLockingFailureException("ID" + client.getId() + "was not found");
        }
        return clientRepository.save(client);
    }

    /**
     * Updates existing client details on the system.
     * 
     * <p>
     * Use this method to modify details of a existing client on
     * the system and automatically assigned a unique identifier.
     * </p>
     * 
     * @param updateClient the client object is created
     * @return the updated client object
     */
    public Client updateClient(Long id, Client updateClient) throws ClientNotFoundException {

        Client existingClientId = getClient(id);

        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client with id " + id + " was not found");
        } else {
            existingClientId.setFirstName(updateClient.getFirstName());
            existingClientId.setLastName(updateClient.getLastName());
            existingClientId.setEmail(updateClient.getEmail());
            existingClientId.setPhone(updateClient.getPhone());
            existingClientId.setAddress(updateClient.getAddress());
            existingClientId.setNotes(updateClient.getNotes());
        }

        return clientRepository.save(existingClientId);
    }

    /**
     * Deletes client details from the system.
     * 
     * <p>
     * This method is used to remove a non active client.
     * The linked projects would also need to handled.
     * </p>
     * 
     * @param id retrieves the client object to be deleted
     * @return client is removed
     */
    public void deleteClient(Long id) throws ClientNotFoundException {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client with id " + id + " was not found");
        }
        clientRepository.deleteById(id);
    }

}
