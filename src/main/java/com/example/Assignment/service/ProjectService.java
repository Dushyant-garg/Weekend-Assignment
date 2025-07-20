package com.example.Assignment.service;

import com.example.Assignment.entity.Project;
import com.example.Assignment.entity.Users;
import com.example.Assignment.repository.ProjectRepository;
import com.example.Assignment.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Project createProject(String name, String scope, LocalDate deadline, Long managerId, List<Long> teamUserIds) {
        Users manager = usersRepository.findById(managerId).orElseThrow();

        List<Users> team = usersRepository.findAllById(teamUserIds);

        Project project = new Project();
        project.setName(name);
        project.setScope(scope);
        project.setDeadline(deadline);
        project.setManager(manager);
        project.setTeam(team);

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getAllProjectsByManager(Long managerId) {
        Users manager = usersRepository.findById(managerId).orElseThrow();
        return projectRepository.findByManager(manager);
    }
}
