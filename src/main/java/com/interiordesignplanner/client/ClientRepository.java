package com.interiordesignplanner.client;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Client} entities.
 * <p>
 * Provides custom CRUD operations and query methods for accessing client data.
 */
@Repository
public interface ClientRepository extends ListCrudRepository<Client, Long> {

    /**
     * Finds a client by last name.
     *
     * @param lastName the last name of the client
     * @return an {@link List} containing the client if found, otherwise empty
     */
    List<Client> findByLastNameIgnoreCase(String lastName);

}
