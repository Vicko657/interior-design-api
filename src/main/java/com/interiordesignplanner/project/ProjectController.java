package com.interiordesignplanner.project;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Rest Controller for managing projects
 * 
 * API endpoints to complete CRUD operations.
 */
@RestController
public class ProjectController {

    // Project Service layer
    public ProjectService projectService;

    // Constructor
    public ProjectController(ProjectService projectService) {

        this.projectService = projectService;

    }

    /**
     * GET: Returns Project with Id
     * 
     * @param id the project's unique identifier
     * @return project's entity
     * @response 200 if project was successfully found
     * @response 404 Not found is the client doesnt exist
     */
    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Finds project by ID", description = "Returns one project, including their name, the budget, project status, start date, deadline and meeting links")
    @GetMapping(value = "/projects/{id}", produces = "application/json")
    public Project getProject(@PathVariable Long id) {
        try {
            return projectService.getProject(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    /**
     * GET: Returns all Projects
     * 
     * @return all projects entities on the system
     * @response 200 if all projects are found
     */
    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Retrieves all of the client's projects", description = "Retrieves all the project information, including the which clients project it is, name, the budget, project status, start date, deadline and meeting links")
    @GetMapping(value = "/projects", produces = "application/json")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    /**
     * POST: Adds a new Project to a Client
     * 
     * @param clientId the client's unique identifier
     * @param project  the project's object to be created
     * @return saved project with generated unique identifier
     * @response 201 if project was successfully created
     * @response 400 bad request is input data is invalid
     */
    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Adds a project to a client", description = "Creates a new project for the client")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/projects/{clientId}")
    public Project createProject(@RequestBody Project project, @PathVariable("clientId") Long clientId) {

        try {
            return projectService.createProject(project, clientId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (OptimisticLockingFailureException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * PUT: Updates existing project details
     * 
     * @param projectId     the project's unique identifier
     * @param updateProject the project's object to be updated
     * @return updated project entity
     * @response 201 if project was successfully updated
     * @response 404 not found is the project doesn't exist
     */
    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Update project", description = "Updates the projects information")
    @PutMapping("/projects/{projectId}")
    public Project updateProject(@PathVariable("projectId") Long projectId, @RequestBody Project updateProject) {

        try {
            return projectService.updateProject(projectId, updateProject);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    /**
     * PATCH: Reassigns project with a different client
     * 
     * @param clientId  the client's unique identifier
     * @param projectId the project's unique identifier
     * @return a new many to one relationship with a client
     * @response 200 if project is reassigned
     */
    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Reassigns project to a different client", description = "Updates to a different client for the project")
    @PatchMapping(value = "/projects/{projectId}/clients/{clientId}", produces = "application/json")
    public Project reassignClient(@PathVariable("projectId") Long projectId, @PathVariable("clientId") Long clientId) {

        try {
            return projectService.reassignClient(clientId, projectId);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    /**
     * GET: Returns all Projects with the same status
     * 
     * @return all projects with the same specific status
     * @response 200 if all projects with the same status are returned
     */
    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Finds project by status", description = "Returns the projects that have the same status")
    @GetMapping(value = "projects/status/{status}", produces = "application/json")
    public List<Status> getProjectStatus(@PathVariable("status") String status) {
        return projectService.getProjectStatus(status);
    }

    /**
     * GET: Returns all Projects in order of due date
     * 
     * @return all projects in a ascending order of due date
     * @response 200 if all project's are found
     */
    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Project deadlines", description = "Returns the projects in order of deadline")
    @GetMapping(value = "projects/deadline", produces = "application/json")
    public List<Deadline> getAllProjectsDueSoonOrderByDueDateAsc() {
        return projectService.getAllProjectsDueSoonOrderByDueDateAsc();
    }

    /**
     * DELETE: Deletes existing Project
     * 
     * @param projectId the project's unique identifier
     * @return deleted project entity off the system
     * @response 204 if project was successfully deleted
     * @response 404 not found is the project doesn't exist
     */
    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Deletes project", description = "Deletes the project and its information")
    @DeleteMapping(value = "/projects/{projectId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        try {
            projectService.deleteProject(projectId);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
