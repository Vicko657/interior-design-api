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

@Entity
@Table(name = "projects")
public class Project extends AbstractEntity {

    // Project | Instances
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference
    public Client client; // Clients foreign key

    public String projectName; // The projects name

    // Project Enum - The projects status default is planning
    @Enumerated(EnumType.STRING)
    public ProjectStatus status = ProjectStatus.PLANNING;

    public Integer budget; // The budget for the project
    public LocalDate startDate; // The date the project started
    public LocalDate dueDate; // The projects deadline
    public String description; // The description of the project
    public String meetingURL; // The meeting link - gmeets
    public Instant completedAt; // The date the project is completed

    @OneToOne(mappedBy = "project")
    @JsonManagedReference
    public Room room; // Room foreign key

    // Project | Constructor
    public Project(String projectName, Integer budget, String description, String meetingURL, LocalDate startDate,
            LocalDate dueDate) {

        this.projectName = projectName;
        this.budget = budget;
        this.description = description;
        this.meetingURL = meetingURL;
        this.startDate = startDate;
        this.dueDate = dueDate;

    }

    // Project | Parameterless constructor
    public Project() {

    }

    // Project | Getters
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

    // Project | Setters
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
