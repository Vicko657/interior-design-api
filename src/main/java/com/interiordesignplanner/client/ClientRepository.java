package com.interiordesignplanner.client;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ListCrudRepository<Client, Long> {

}
