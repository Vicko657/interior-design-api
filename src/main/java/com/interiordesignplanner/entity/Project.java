package com.interiordesignplanner.entity;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client; // Clients foreign key

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room; // Room foreign key

    private String projectName; // The projects name

    // Project Enum - The projects status default is planning
    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.PLANNING;

    private Integer budget; // The budget for the project
    private LocalDate startDate; // The date the project started
    private LocalDate dueDate; // The projects deadline
    private String description; // The description of the project
    private String meetingURL; // The meeting link - gmeets
    private Instant completedAt; // The date the project is completed

    // JPA Auditing
    @CreatedDate
    private Instant createdAt; // Date the Project was added on the system
    @LastModifiedDate
    private Instant updatedAt; // Updates the date, each time the project's information is modified

    // Project | Constructor
    public Project(String projectName, Integer budget, String description, String meetingURL) {

        this.projectName = projectName;
        this.budget = budget;
        this.description = description;
        this.meetingURL = meetingURL;

    }

    // Project | Parameterless constructor
    public Project() {
        this("Project Name:", 120000, "Aims for the project", "Meeting URL");
    }

    // Project | Getters
    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public ProjectStatus getProjectStatus() {
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

    // Project | Setters
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectStatus(ProjectStatus status) {
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

}
