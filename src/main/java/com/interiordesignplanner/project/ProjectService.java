package com.interiordesignplanner.project;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.interiordesignplanner.client.Client;
import com.interiordesignplanner.client.ClientService;

import jakarta.transaction.Transactional;

/**
 * Project service class handles business logic and operations to help manage
 * the projects.
 * 
 * <p>
 * Responsibilities include creating, updating, retrieving, and deleting project
 * records, enforcing project status (PLANNING, ACTIVE, COMPLETED) to help track
 * progress,
 * managing budgets and deadlines and coordinating relationships with rooms.
 * 
 * Serves as an interface between controllers and the persistence layer.
 * </p>
 */
@Service
public class ProjectService {

    // Project CRUD Interface
    private final ProjectRepository projectRepository;

    // Client Service layer
    private final ClientService clientService;

    // Constructor
    public ProjectService(ProjectRepository projectRepository, ClientService clientService) {
        this.projectRepository = projectRepository;
        this.clientService = clientService;
    }

    /**
     * Returns all projects on the system and their room.
     */
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    /**
     * Finds all projects that have the same status, using the status record.
     * 
     * <p>
     * Retrieves all projects that have the same status for progress tracking.
     * 
     * Custom query created in the repository.
     * </p>
     * 
     * 
     * @param status project status enum
     * @returns projects with same status
     */
    public List<Status> getProjectStatus(String status) {
        ProjectStatus[] statusValues = ProjectStatus.values();
        for (ProjectStatus status1 : statusValues) {
            if (status1.name().equalsIgnoreCase(status)) {
                return projectRepository.getByStatus(status1);
            }
        }
        return null;
    }

    /**
     * Returns all projects in the order of due date, using the deadline record.
     * 
     * <p>
     * Sorts all projects in order of due date to help,
     * manage progress tracking and deadlines.
     * 
     * Custom query created in the repository.
     * </p>
     * 
     * @returns order by due date
     */
    public List<Deadline> getAllProjectsDueSoonOrderByDueDateAsc() {
        return projectRepository.findAllProjectsDueSoonOrderByDueDateAsc();
    }

    /**
     * Returns a project using their projectId.
     * 
     * 
     * <p>
     * Fetches a specific project for operations such as updating,
     * assigning rooms or checking project status.
     * </p>
     * 
     * @param id project's unique identifier
     * @throws ProjectNotFoundException if the project is not found
     */
    public Project getProject(Long id) throws NoSuchElementException {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    /**
     * Creates a new project for a client.
     * 
     * <p>
     * Creates a new design project for specific client
     * entity for CRUD operations. It will be assigned a unique identifier.
     * </p>
     * 
     * @param project  project object to be created
     * @param clientId client's unique identifier
     * @throws IllegalArgumentException the project fields are null
     */
    public Project createProject(Project project, Long clientId) {
        if (project == null) {
            throw new IllegalArgumentException("Project must not be null");
        }

        if (project.getId() != null && projectRepository.existsById(project.getId())) {
            throw new OptimisticLockingFailureException("ID" + project.getId() + "was not found");
        }
        Client client = clientService.getClient(clientId);
        project.setClient(client);
        return projectRepository.save(project);
    }

    /**
     * Updates a existing project.
     * 
     * <p>
     * Use this method to modify project details such as deadlines, budget or
     * description.
     * 
     * If the project status has been updated to COMPLETED the
     * completedAt field will be set with the current date.
     * </p>
     * 
     * @param id      project's unique identifier
     * @param project project object to be updated
     * @return updated project
     */
    public Project updateProject(Long id, Project project) {
        Project existingProjectId = getProject(id);
        existingProjectId.setProjectName(project.getProjectName());
        existingProjectId.setBudget(project.getBudget());
        existingProjectId.setStatus(project.getStatus());
        existingProjectId.setStartDate(project.getStartDate());
        existingProjectId.setMeetingURL(project.getMeetingURL());
        existingProjectId.setDueDate(project.getDueDate());
        existingProjectId.setClient(project.getClient());

        // Updated Project Status to COMPLETED, sets completedAt field
        if (existingProjectId.getStatus() == ProjectStatus.COMPLETED
                && existingProjectId.getCompletedAt() == null) {
            existingProjectId.setCompletedAt(Instant.now());
        }
        return projectRepository.save(existingProjectId);
    }

    /**
     * Deletes a existing project.
     * 
     * <p>
     * This method is used when a project is canceled and should no longer be
     * tracked. Associated rooms may also be deleted.
     * </p>
     * 
     * @param id project's unique identifier
     * @return project is deleted
     */
    public Project deleteProject(Long id) {
        Project project = getProject(id);
        projectRepository.deleteById(id);
        return project;
    }

    /**
     * Reassigns Project to a existing client.
     * 
     * <p>
     * Use this method when a project was assigned to the wrong client and needs to
     * 
     * be reassigned.
     * It will update the many to one relationship.
     * </p>
     * 
     * @param clientId  client's unique identifier
     * @param projectId project's unique identifier
     * @return project is reassigned
     */
    public Project reassignClient(Long clientId, Long projectId) {
        Project existingProjectId = getProject(projectId);
        Client client = clientService.getClient(clientId);
        existingProjectId.setClient(client);
        return projectRepository.save(existingProjectId);
    }

    /**
     * Saves project entity when room is created
     * 
     * <p>
     * Creates the One to One relationship
     * with Project, when the room is created.
     * </p>
     *
     * @param project project's object to be saved
     */

    @Transactional
    public Project saveProjectEntity(Project project) {
        return projectRepository.save(project);
    }

}
