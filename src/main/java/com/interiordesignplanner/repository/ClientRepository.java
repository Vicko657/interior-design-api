package com.interiordesignplanner.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.interiordesignplanner.entity.Client;

@Repository
public interface ClientRepository extends ListCrudRepository<Client, Long> {

}
