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

@RestController
public class ClientController {

    public ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Tag(name = "clients", description = "Information about the clients")
    @Operation(summary = "Retrieves all clients", description = "Retrieves all the clients details, including their name, email, phoneNo, address, projects and other details")
    @GetMapping("/clients")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @Tag(name = "clients", description = "Information about the clients")
    @Operation(summary = "Create a new client", description = "Creates a new client and add's their details")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client) {

        try {
            return clientService.createClient(client);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (OptimisticLockingFailureException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @Tag(name = "clients", description = "Information about the clients")
    @Operation(summary = "Update client", description = "Updates the client's records")
    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client updateClient) {

        try {
            return clientService.updateClient(id, updateClient);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Tag(name = "clients", description = "Information about the clients")
    @Operation(summary = "Deletes client", description = "Deletes the client's records")
    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
