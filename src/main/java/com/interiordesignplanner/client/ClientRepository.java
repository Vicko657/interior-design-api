package com.interiordesignplanner.client;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends ListCrudRepository<Client, Long> {

    List<Client> findByLastNameIgnoreCase(String lastName);

}
