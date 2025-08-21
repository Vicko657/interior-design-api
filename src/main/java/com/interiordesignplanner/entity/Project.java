package com.interiordesignplanner.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "projects")
public class Project {

    // Project | Instances
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Project's Id

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client; // Clients foreign key

}
