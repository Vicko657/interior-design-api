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

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // Enables auditingentitylistener
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Entity's Id

    // JPA Auditing
    @JsonIgnore
    @CreatedDate
    public Instant createdAt; // The date it was added on the system
    @JsonIgnore
    @LastModifiedDate
    public Instant updatedAt; // Updates the date, each time data is modified

    public Long getId() {
        return id;
    }

}
