package com.interiordesignplanner.client;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Rest Controller for managing clients
 * 
 * API endpoints to complete CRUD operations.
 */
@RestController("api/")
public class ClientController {

    // Client Service layer
    public ClientService clientService;

    // Constructor
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * GET: Returns all Clients
     * 
     * @return all clients entities on the system
     * @response 201 if all clients are found
     */
    @Tag(name = "clients", description = "Information about the clients")
    @Operation(summary = "Retrieves all clients", description = "Retrieves all the clients details, including their name, email, phoneNo, address, projects and other details")
    @GetMapping(value = "/clients", produces = "application/json")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    /**
     * GET: Returns Client with Id
     * 
     * @param id the client's unique identifier
     * @return client's entity
     * @response 200 if client was successfully found
     * @response 404 not found is the client doesnt exist
     */
    @Tag(name = "clients", description = "Information about the clients")
    @Operation(summary = "Finds client by ID", description = "Returns one clients details, including their name, email, phoneNo, address, projects and other details")
    @GetMapping(value = "/clients/{id}", produces = "application/json")
    public Client getClient(@PathVariable Long id) {
        try {
            return clientService.getClient(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    /**
     * GET: Returns Client with LastName
     * 
     * @param lastName the client's lastname
     * @return client's entity
     * @response 200 if client was successfully found
     */
    @Tag(name = "clients", description = "Information about the clients")
    @Operation(summary = "Finds client by lastname", description = "Returns the client details, including their name, email, phoneNo, address, projects and other details")
    @GetMapping(value = "/clients/lastName/{lastName}", produces = "application/json")
    public List<Client> getByLastNameIgnoreCase(@PathVariable("lastName") String lastName) {
        return clientService.getByLastNameIgnoreCase(lastName);
    }

    /**
     * POST: Creates a new Client
     * 
     * @param client the client's object to be created
     * @return saved client with generated unique identifier
     * @response 201 if client was successfully created
     * @response 400 bad request is input data is invalid
     */
    @Tag(name = "clients", description = "Information about the clients")
    @Operation(summary = "Create a new client", description = "Creates a new client and add's their details")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/clients", produces = "application/json")
    public Client createClient(@RequestBody Client client) {

        try {
            return clientService.createClient(client);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (OptimisticLockingFailureException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * PUT: Updates existing client details
     * 
     * @param id           the client's unique identifier
     * @param updateClient the client's object to be updated
     * @return updated client entity
     * @response 201 if client was successfully updated
     * @response 404 not found is the client doesnt exist
     */
    @Tag(name = "clients", description = "Information about the clients")
    @Operation(summary = "Update client", description = "Updates the client's records")
    @PutMapping(value = "/clients/{id}", produces = "application/json")
    public Client updateClient(@PathVariable Long id, @RequestBody Client updateClient) {

        try {
            return clientService.updateClient(id, updateClient);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    /**
     * DELETE: Deletes existing Client
     * 
     * @param id the client's unique identifier
     * @return deleted client entity off the system
     * @response 204 if client was successfully deleted
     * @response 404 Not found is the client doesnt exist
     */
    @Tag(name = "clients", description = "Information about the clients")
    @Operation(summary = "Deletes client", description = "Deletes the client's records")
    @DeleteMapping(value = "/clients/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
