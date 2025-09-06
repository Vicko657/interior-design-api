package com.interiordesignplanner.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.interiordesignplanner.AbstractEntity;
import com.interiordesignplanner.project.Project;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Models a Client of the interior designer. Stores the client's
 * contact details and preferences to manage relationships.
 * One client can have multiple projects and is extending the
 * AbstractEntity class, which provides their unique identifier
 * and timestamps for creation and updates to their data.
 */

@Entity
@Table(name = "clients")
public class Client extends AbstractEntity {

    // Client's firstname
    public String firstName;

    // Client's lastname
    public String lastName;

    // Client's email address
    public String email;

    // Client's phone number
    public String phone;

    // Client's full address including streetname, city and postcode
    public String address;

    // Notes on specific preferences for the client
    public String notes;

    // Creates One to Many Bidirectional relationship with the project entity
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    public List<Project> projects;

    // Constructor
    public Client(String firstName, String lastName, String email, String phone, String address, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.notes = notes;
    }

    // Parameterless constructor
    public Client() {

    }

    // Getters
    @Override
    public Long getId() {
        return super.getId();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
