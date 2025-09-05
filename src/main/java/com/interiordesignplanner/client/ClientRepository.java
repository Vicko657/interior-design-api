package com.interiordesignplanner.client;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends ListCrudRepository<Client, Long> {

    @Query("select c from Client c where c.lastName = :lastName")
    List<Client> findByLastNameIgnoreCase(@Param("lastName") String lastName);

}
