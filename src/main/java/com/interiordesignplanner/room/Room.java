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

@Entity
@Table(name = "rooms")
public class Room extends AbstractEntity {

    // Room | Instances
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @JsonBackReference
    public Project project; // Project foreign key

    @Enumerated(EnumType.STRING)
    public RoomType type = null;

    public Double length;
    public Double height;
    public Double width;
    public String unit;
    public String checklist;
    public String changes;

    // Room | Constructor
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

    // Room | Parameterless constructor
    public Room() {

    }

    // Room | Getters
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
