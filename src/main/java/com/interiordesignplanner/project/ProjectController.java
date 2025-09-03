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

@RestController
public class ProjectController {

    public ProjectService projectService;

    public ProjectController(ProjectService projectService) {

        this.projectService = projectService;

    }

    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Finds project by ID", description = "Returns one project, including their name, the budget, project status, start date, deadline and meeting links")
    @GetMapping(value = "/clients/{id}")
    public ProjectDTO getProject(@PathVariable Long id) {
        try {
            return projectService.getProject(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Retrieves all of the client's projects", description = "Retrieves all the project information, including the which clients project it is, name, the budget, project status, start date, deadline and meeting links")
    @GetMapping("/projects")
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

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

    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Reassigns project to a different client", description = "Updates to a different client for the project")
    @PatchMapping("/projects/{projectId}/clients/{clientId}")
    public Project reassignClient(@PathVariable("projectId") Long projectId, @PathVariable("clientId") Long clientId) {

        try {
            return projectService.reassignClient(clientId, projectId);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Tag(name = "projects", description = "Client's Project directory")
    @Operation(summary = "Deletes project", description = "Deletes the project and its information")
    @DeleteMapping("/projects/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        try {
            projectService.deleteProject(projectId);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
