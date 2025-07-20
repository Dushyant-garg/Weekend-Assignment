package com.example.Assignment.service;

import com.example.Assignment.entity.Project;
import com.example.Assignment.entity.Task;
import com.example.Assignment.entity.Users;
import com.example.Assignment.enums.TaskStatus;
import com.example.Assignment.repository.ProjectRepository;
import com.example.Assignment.repository.TaskRepository;
import com.example.Assignment.repository.UsersRepository;
import com.example.Assignment.util.GetUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Task createTask(String name, String description, LocalDate deadline, Long projectId, List<Long> assignedUserIds) {

        Project project = projectRepository.findById(projectId).orElseThrow(()-> new RuntimeException("Project not found"));
        Long userId = GetUserDetails.getCurrentUserId();
        Users manager = project.getManager(); // Auto-fetch project manager
        if (!project.getManager().getId().equals(userId)) {
            throw new RuntimeException("Only the project manager can assign tasks.");
        }

        // Fetching assigned users
        List<Users> assignedUsers = usersRepository.findAllById(assignedUserIds);

        // ensuring assigned users are part of the project team
        for (Users user : assignedUsers) {
            if (!project.getTeam().contains(user)) {
                throw new RuntimeException("User " + user.getUsername() + " is not part of the project team");
            }
        }

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setProject(project);
        task.setManager(manager);
        task.setAssignedTo(assignedUsers);

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByProject(Long projectId) {
        projectRepository.findById(projectId).orElseThrow(()-> new RuntimeException("Project not found"));
        return taskRepository.findByProjectId(projectId);
    }

    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Ensuring dependency check when setting to IN_PROGRESS
        if (status == TaskStatus.IN_PROGRESS || status == TaskStatus.COMPLETED) {
            for (Task dependency : task.getDependencies()) {
                if (dependency.getStatus() != TaskStatus.COMPLETED) {
                    throw new RuntimeException("Cannot start task until all dependencies are completed.");
                }
            }
        }

        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Task addDependencies(Long taskId, List<Long> dependencyIds) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Set<Task> dependencies = new HashSet<>(taskRepository.findAllById(dependencyIds));

        if (dependencies.contains(task)) {
            throw new RuntimeException("Task cannot depend on itself.");
        }

        task.getDependencies().addAll(dependencies);
        return taskRepository.save(task);
    }

}
