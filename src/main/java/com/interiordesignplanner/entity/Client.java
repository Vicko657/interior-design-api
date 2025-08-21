package com.interiordesignplanner.entity;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class) // Enables auditingentitylistener
@Table(name = "clients")
public class Client {

    // Client | Instances
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Client's Id
    protected String firstName; // Client's fullname
    protected String lastName; // Client's fullname
    protected String email; // Client's email address
    protected String phone; // Client's phone number
    protected String address; // Client's address
    protected String notes; // Notes specific for the Client

    // JPA Auditing
    @CreatedDate
    private Instant createdAt; // Date the Client was added on the system
    @LastModifiedDate
    private Instant updatedAt; // Updates the date, each time the client's information is modified

    // Creates One to many relationship with Project table
    @OneToMany(mappedBy = "client")
    private List<Project> projects;

    // Client | Constructor
    public Client(String firstName, String lastName, String email, String phone, String address, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.notes = notes;

    }

    // Client | Parameterless constructor
    public Client() {
        this("firstname", "lastname", "client@gmail.com", "01234567891", "No. address line, city, postcode",
                "additional details");
    }

    // Client | Getters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNo() {
        return this.phone;
    }

    public String getAddress() {
        return this.address;
    }

    public String getNotes() {
        return this.notes;
    }

    // Client | Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
