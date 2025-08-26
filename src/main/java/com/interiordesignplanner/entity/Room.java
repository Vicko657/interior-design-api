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

    private String roomName;
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

    // Room | Constructor
    public Room(String roomName, Double length, Double height, Double width, String unit, String checklist,
            String changes) {

        this.roomName = roomName;
        this.length = length;
        this.height = height;
        this.width = width;
        this.unit = unit;
        this.checklist = checklist;
        this.changes = changes;

    }

    // Room | Parameterless constructor
    public Room() {
        this("Room Name:", 7.5, 4.5, 5.5, "m", "Room checklist", "Room changes");
    }

    // Room | Getters
    public Long getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public Double getLength() {
        return length;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public String getUnit() {
        return unit;
    }

    public String getChecklist() {
        return checklist;
    }

    public String getChanges() {
        return changes;
    }

    public Project getProject() {
        return project;
    }

    // Room | Setters
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setChecklist(String checklist) {
        this.checklist = checklist;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
