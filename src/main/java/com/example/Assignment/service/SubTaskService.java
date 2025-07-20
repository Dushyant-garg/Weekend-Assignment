package com.example.Assignment.service;

import com.example.Assignment.entity.SubTask;
import com.example.Assignment.entity.Task;
import com.example.Assignment.entity.Users;
import com.example.Assignment.enums.TaskStatus;
import com.example.Assignment.repository.ProjectRepository;
import com.example.Assignment.repository.SubTaskRepository;
import com.example.Assignment.repository.TaskRepository;
import com.example.Assignment.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubTaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubTaskRepository subTaskRepository;

    @Autowired
    private UsersRepository usersRepository;

    public SubTask createSubtask(Long parentTaskId, String name, String description, LocalDate deadline, Long userId) {
        Task parentTask = taskRepository.findById(parentTaskId)
                .orElseThrow(() -> new RuntimeException("Parent task not found"));

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SubTask subtask = new SubTask();
        subtask.setName(name);
        subtask.setDescription(description);
        subtask.setDeadline(deadline);
        subtask.setParentTask(parentTask);
        subtask.setAssignedTo(user);
        subtask.setStatus(TaskStatus.TODO);

        return subTaskRepository.save(subtask);
    }

    public List<SubTask> getSubtasksByParentTask(Long taskId) {
        return subTaskRepository.findAll().stream()
                .filter(subtask -> subtask.getParentTask().getId().equals(taskId))
                .toList();
    }
}
