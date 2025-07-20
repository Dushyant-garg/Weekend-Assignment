package com.example.Assignment.controller;

import com.example.Assignment.entity.Project;
import com.example.Assignment.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // DTO for project creation
    public static class CreateProjectRequest {
        public String name;
        public String scope;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        public LocalDate deadline;

        public Long managerId;
        public List<Long> teamUserIds;
    }

    @PostMapping
    public Project createProject(@RequestBody CreateProjectRequest request) {
        return projectService.createProject(
                request.name,
                request.scope,
                request.deadline,
                request.managerId,
                request.teamUserIds
        );
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("{managerId}")
    public List<Project> getAllProjectsByManager(@PathVariable Long managerId) {
        return projectService.getAllProjectsByManager(managerId);
    }


}
