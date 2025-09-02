package com.interiordesignplanner.client;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.interiordesignplanner.project.Project;

@Service
public class ClientDTOMapper implements Function<Client, ClientDTO> {

    @Override
    public ClientDTO apply(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getFirstName() + " " + client.getLastName(),
                client.getEmail(),
                client.getPhoneNo(),
                client.getAddress(),
                client.getNotes(),
                client.getProject()
                        .stream()
                        .collect(Collectors.toMap(Project::getId,
                                Project::getProjectName)));
    }

}
