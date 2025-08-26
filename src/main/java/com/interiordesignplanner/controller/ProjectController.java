package com.interiordesignplanner.controller;

import java.util.List;

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

import com.interiordesignplanner.entity.Project;
import com.interiordesignplanner.exception.ProjectNotFoundException;
import com.interiordesignplanner.service.ProjectService;

@RestController
public class ProjectController {

    public ProjectService projectService;

    public ProjectController(ProjectService projectService) {

        this.projectService = projectService;

    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return this.projectService.getAllProjects();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/projects/{clientId}")
    public Project createProject(@RequestBody Project project, @PathVariable("clientId") Long clientId) {

        try {
            return this.projectService.createProject(project, clientId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (OptimisticLockingFailureException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @PutMapping("projects/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project updateProject) {

        try {
            return this.projectService.updateProject(id, updateProject);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping("projects/{projectId}/clients/{clientId}")
    public Project reassignClient(@PathVariable("projectId") Long projectId, @PathVariable("clientId") Long clientId) {

        try {
            return this.projectService.reassignClient(clientId, projectId);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("projects/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable Long id) {
        try {
            this.projectService.deleteProject(id);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
