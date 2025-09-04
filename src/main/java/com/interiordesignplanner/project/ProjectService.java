package com.interiordesignplanner.project;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.interiordesignplanner.client.Client;
import com.interiordesignplanner.client.ClientService;

import jakarta.transaction.Transactional;

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

    public List<Project> getProjectStatus(String status) {

        ProjectStatus[] statusValues = ProjectStatus.values();

        for (ProjectStatus status1 : statusValues) {
            if (status1.name().equalsIgnoreCase(status)) {
                return projectRepository.getByStatus(status1);
            }
        }

        return null;

    }

    public List<Project> getAllProjectsOrderByDueDateAsc() {

        return projectRepository.findAllProjectsOrderByDueDateAsc();

    }

    public Project getProject(Long id) throws NoSuchElementException {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID" + id + "was not found"));
    }

    public Project createProject(Project project, Long clientId) {
        if (project == null) {
            throw new IllegalArgumentException("Project must not be null");
        }

        if (project.getId() != null && projectRepository.existsById(project.getId())) {
            throw new OptimisticLockingFailureException("ID" + project.getId() + "was not found");
        }
        Client client = clientService.getClientEntity(clientId);
        project.setClient(client);
        return projectRepository.save(project);
    }

    public Project getProjectEntity(Long id) throws NoSuchElementException {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
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
        existingProjectId.setClient(project.getClient());

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
        Client client = clientService.getClientEntity(clientId);
        existingProjectId.setClient(client);
        return projectRepository.save(existingProjectId);
    }

    @Transactional
    public Project saveProjectEntity(Project project) {
        return projectRepository.save(project);
    }

}
