package com.interiordesignplanner.room;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends ListCrudRepository<Room, Long> {

}
