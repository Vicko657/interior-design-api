package com.interiordesignplanner.room;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.interiordesignplanner.AbstractEntity;
import com.interiordesignplanner.project.Project;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Models a room within a project, like a bedroom, living room or bathroom.
 * The room entity helps track dimensions,specifications, design elements
 * and styling. One room belongs to one project and is extending the
 * AbstractEntity class, which provides their unique identifier and
 * timestamps for creation and updates to their data.
 */

@Entity
@Table(name = "rooms")
public class Room extends AbstractEntity {

    // Foreign key to Project entity, one to one bidirectional relationship.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @JsonBackReference
    public Project project;

    // Categories the specific type of room
    @Enumerated(EnumType.STRING)
    public RoomType type;

    // Dimemsions of the room
    public Double length;
    public Double height;
    public Double width;
    public String unit;

    // Tracks key tasks and items specific to the room
    public String checklist;

    // Records design updates to the room over time
    public String changes;

    // Constructor
    public Room(RoomType type, Double length, Double height, Double width, String unit, String checklist,
            String changes) {

        this.type = type;
        this.length = length;
        this.height = height;
        this.width = width;
        this.unit = unit;
        this.checklist = checklist;
        this.changes = changes;

    }

    // Parameterless constructor
    public Room() {

    }

    // Getters
    @Override
    public Long getId() {
        return super.getId();
    }

    public RoomType getType() {
        return type;
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
    public void setType(RoomType type) {
        this.type = type;
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
