package com.interiordesignplanner.project;

import java.time.Instant;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.interiordesignplanner.AbstractEntity;
import com.interiordesignplanner.client.Client;
import com.interiordesignplanner.room.Room;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Models a design project for a client. Each project will have details
 * such as the name, status, budget, meeting link and description.
 * A project belongs to one client and can belong to one room and
 * is extending the AbstractEntity class, which provides their unique
 * identifier and timestamps for creation and updates to their data.
 */

@Entity
@Table(name = "projects")
public class Project extends AbstractEntity {

    // Foreign key to Client entity, many to one bidirectional relationship.
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference
    public Client client;

    // Name of the project
    public String projectName;

    // Status of the project - Default: Planning
    @Enumerated(EnumType.STRING)
    public ProjectStatus status = ProjectStatus.PLANNING;

    // Estimated cost of project
    public Integer budget;

    // The start date and due date to help with planning and progress tracking
    public LocalDate startDate;
    public LocalDate dueDate;

    // Brief project description to help the designer have an overview
    public String description;

    // Video conference link for remote project meetings (Google Meets)
    public String meetingURL;

    // The timestamp of project completion
    public Instant completedAt;

    // Creates One to One Bidirectional relationship with the room entity
    @OneToOne(mappedBy = "project")
    @JsonManagedReference
    public Room room;

    // Constructor
    public Project(String projectName, Integer budget, String description, String meetingURL, LocalDate startDate,
            LocalDate dueDate) {

        this.projectName = projectName;
        this.budget = budget;
        this.description = description;
        this.meetingURL = meetingURL;
        this.startDate = startDate;
        this.dueDate = dueDate;

    }

    // Parameterless constructor
    public Project() {

    }

    // Getters
    @Override
    public Long getId() {
        return super.getId();
    }

    public String getProjectName() {
        return projectName;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public Integer getBudget() {
        return budget;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public String getMeetingURL() {
        return meetingURL;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public Client getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }

    // Setters
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMeetingURL(String meetingURL) {
        this.meetingURL = meetingURL;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
