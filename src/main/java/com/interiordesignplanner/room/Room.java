package com.interiordesignplanner.room;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.interiordesignplanner.AbstractEntity;
import com.interiordesignplanner.project.Project;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room extends AbstractEntity {

    // Room | Instances
    @OneToOne(mappedBy = "room")
    @JsonBackReference
    private Project project; // Project foreign key

    private String type;
    private Double length;
    private Double height;
    private Double width;
    private String unit;
    private String checklist;
    private String changes;

    // Room | Constructor
    public Room(String type, Double length, Double height, Double width, String unit, String checklist,
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
        this("Type of Room:", 7.5, 4.5, 5.5, "m", "Room checklist", "Room changes");
    }

    // Room | Getters
    public Long getId() {
        return super.getId();
    }

    public String getType() {
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
    public void setType(String type) {
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
