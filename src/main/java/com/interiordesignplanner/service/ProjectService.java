package com.interiordesignplanner.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.interiordesignplanner.entity.Client;
import com.interiordesignplanner.entity.Project;
import com.interiordesignplanner.exception.ProjectNotFoundException;
import com.interiordesignplanner.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientService clientService;

    public ProjectService(ProjectRepository projectRepository, ClientService clientService) {
        this.projectRepository = projectRepository;
        this.clientService = clientService;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(Long id) throws NoSuchElementException {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public Project createProject(Project project, Long clientId) {
        if (project == null && clientId == null) {
            throw new IllegalArgumentException("Project and clientId must not be null");
        }

        if (project.getId() != null && projectRepository.existsById(project.getId())) {
            throw new OptimisticLockingFailureException("ID" + project.getId() + "was not found");
        }

        // Assiging Client Id to the project
        Client client = clientService.getClient(clientId);
        project.setClient(client);
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
        Project existingProjectId = getProject(id);
        existingProjectId.setProjectName(project.getProjectName());
        existingProjectId.setBudget(project.getBudget());
        existingProjectId.setProjectStatus(project.getProjectStatus());
        existingProjectId.setStartDate(project.getStartDate());
        existingProjectId.setMeetingURL(project.getMeetingURL());
        existingProjectId.setDueDate(project.getDueDate());
        return projectRepository.save(existingProjectId);
    }

    public Project deleteProject(Long id) {
        Project project = getProject(id);
        projectRepository.deleteById(id);
        return project;
    }

    // Sets the Client to the project
    public Project reassignClient(Long clientId, Long projectId) {
        Project existingProjectId = getProject(projectId);
        Client client = clientService.getClient(clientId);
        existingProjectId.setClient(client);
        return projectRepository.save(existingProjectId);
    }

}
