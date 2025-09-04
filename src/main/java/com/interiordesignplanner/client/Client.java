package com.interiordesignplanner.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.interiordesignplanner.AbstractEntity;
import com.interiordesignplanner.project.Project;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends AbstractEntity {

    // Client | Instances
    public String firstName; // Client's fullname
    public String lastName; // Client's fullname
    public String email; // Client's email address
    public String phone; // Client's phone number
    public String address; // Client's address
    public String notes; // Notes specific for the Client

    // Creates One to many relationship with Project table
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    public List<Project> projects;

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

    }

    // Client | Getters

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
