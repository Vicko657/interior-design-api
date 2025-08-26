package com.interiordesignplanner.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class) // Enables auditingentitylistener
@Table(name = "rooms")
public class Room {

    // Room | Instances
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Room's Id

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project; // Project foreign key

    private Double length;
    private Double height;
    private Double width;
    private String unit;
    private String checklist;
    private String changes;

    // JPA Auditing
    @CreatedDate
    private Instant createdAt; // Date the Room was linked to the project
    @LastModifiedDate
    private Instant updatedAt; // Updates the date, each time the room's information is modified

}
