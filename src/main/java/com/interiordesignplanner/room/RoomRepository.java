package com.interiordesignplanner.room;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Room} entities.
 *
 * <p>
 * Provides custom CRUD operations and query methods for accessing project data.
 * </p>
 */
@Repository
public interface RoomRepository extends ListCrudRepository<Room, Long> {

    /**
     * Finds a room by type.
     *
     * @param type the type of room
     * @return an {@link List} containing all the rooms with the specific type if
     *         found, otherwise empty
     */
    @Query("select r from Room r where r.type = :type")
    List<Room> getByType(@Param("type") RoomType type);

}
