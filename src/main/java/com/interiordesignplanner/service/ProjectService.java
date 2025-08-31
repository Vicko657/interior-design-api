package com.interiordesignplanner.service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.interiordesignplanner.dto.ProjectDTO;
import com.interiordesignplanner.dto.ProjectDTOMapper;
import com.interiordesignplanner.entity.Client;
import com.interiordesignplanner.entity.Project;
import com.interiordesignplanner.entity.ProjectStatus;
import com.interiordesignplanner.exception.ProjectNotFoundException;
import com.interiordesignplanner.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientService clientService;
    private final ProjectDTOMapper projectDTOMapper;

    public ProjectService(ProjectRepository projectRepository, ClientService clientService,
            ProjectDTOMapper projectDTOMapper) {
        this.projectRepository = projectRepository;
        this.clientService = clientService;
        this.projectDTOMapper = projectDTOMapper;
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(projectDTOMapper)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProject(Long id) throws NoSuchElementException {
        return projectRepository.findById(id)
                .map(projectDTOMapper)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public Project getProjectEntity(Long id) throws NoSuchElementException {
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
        Project existingProjectId = getProjectEntity(id);
        ;
        existingProjectId.setProjectName(project.getProjectName());
        existingProjectId.setBudget(project.getBudget());
        existingProjectId.setProjectStatus(project.getProjectStatus());
        existingProjectId.setStartDate(project.getStartDate());
        existingProjectId.setMeetingURL(project.getMeetingURL());
        existingProjectId.setDueDate(project.getDueDate());

        // When the Project Status changes to completed the completed date is set
        if (existingProjectId.getProjectStatus() == ProjectStatus.COMPLETED
                && existingProjectId.getCompletedAt() == null) {
            existingProjectId.setCompletedAt(Instant.now());
        }

        return projectRepository.save(existingProjectId);
    }

    public Project deleteProject(Long id) {
        Project project = getProjectEntity(id);
        projectRepository.deleteById(id);
        return project;
    }

    // Sets the Client to the project
    public Project reassignClient(Long clientId, Long projectId) {
        Project existingProjectId = getProjectEntity(projectId);
        Client client = clientService.getClient(clientId);
        existingProjectId.setClient(client);
        return projectRepository.save(existingProjectId);
    }

}
