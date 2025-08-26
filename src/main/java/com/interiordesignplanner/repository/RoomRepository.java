package com.interiordesignplanner.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.interiordesignplanner.entity.Room;

@Repository
public interface RoomRepository extends ListCrudRepository<Room, Long> {

}
