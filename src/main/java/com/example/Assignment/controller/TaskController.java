package com.example.Assignment.controller;

import com.example.Assignment.entity.Task;
import com.example.Assignment.entity.Users;
import com.example.Assignment.enums.TaskStatus;
import com.example.Assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    public static class CreateTaskRequest {
        public String name;
        public String description;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        public LocalDate deadline;

        public Long projectId;
        public List<Long> assignedUserIds;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public Task createTask(@RequestBody CreateTaskRequest request) {
        return taskService.createTask(
                request.name,
                request.description,
                request.deadline,
                request.projectId,
                request.assignedUserIds
        );
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProject(projectId);
    }

    @PutMapping("/{id}/status")
    public Task updateStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        return taskService.updateTaskStatus(id, status);
    }

    @PutMapping("/{id}/dependencies")
    public Task addDependencies(@PathVariable Long id, @RequestBody List<Long> dependencyIds) {
        return taskService.addDependencies(id, dependencyIds);
    }

}

