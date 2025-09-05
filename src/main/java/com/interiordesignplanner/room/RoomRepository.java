package com.interiordesignplanner.room;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends ListCrudRepository<Room, Long> {

    @Query("select r from Room r where r.type = :type")
    List<Room> getByType(@Param("type") RoomType type);

}
