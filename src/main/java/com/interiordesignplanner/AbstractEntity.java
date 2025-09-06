package com.interiordesignplanner;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Provides shared properties for all entities. Stores the unique
 * identifier (long) and timestamps for creation and updates.
 * This entity is extended by Client, Project and Room entities
 * to help with consistent auditing and reduce repetetive code.
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

    // The entities auto generated primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Timestamp of the creation of the entity
    @JsonIgnore
    @CreatedDate
    public Instant createdAt;

    // Timestamp of when the entity was last modified
    @JsonIgnore
    @LastModifiedDate
    public Instant updatedAt;

    // Unique identifier has a getter and cannot be set
    public Long getId() {
        return id;
    }

}
