package com.interiordesignplanner.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends AbstractEntity {

    // Client | Instances
    protected String firstName; // Client's fullname
    protected String lastName; // Client's fullname
    protected String email; // Client's email address
    protected String phone; // Client's phone number
    protected String address; // Client's address
    protected String notes; // Notes specific for the Client

    // Creates One to many relationship with Project table
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
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

    public String getPhoneNo() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getNotes() {
        return notes;
    }

    public List<Project> getProject() {
        return projects;
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
