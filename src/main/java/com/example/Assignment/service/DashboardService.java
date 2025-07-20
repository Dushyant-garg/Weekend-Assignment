package com.example.Assignment.service;

import com.example.Assignment.dtos.DashboardResponse;
import com.example.Assignment.dtos.MemberWorkload;
import com.example.Assignment.entity.Task;
import com.example.Assignment.entity.Users;
import com.example.Assignment.enums.TaskStatus;
import com.example.Assignment.repository.TaskRepository;
import com.example.Assignment.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UsersRepository userRepository;

    public DashboardResponse getDashboardStats() {
        List<Task> allTasks = taskRepository.findAll();
        long completed = allTasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.COMPLETED)
                .count();

        long overdue = allTasks.stream()
                .filter(task -> task.getDeadline().isBefore(LocalDate.now()) && task.getStatus() != TaskStatus.COMPLETED)
                .count();

        DashboardResponse response = new DashboardResponse();
        response.setTotalTasks(allTasks.size());
        response.setCompletedTasks((int) completed);
        response.setOverdueTasks((int) overdue);
        response.setCompletionPercentage(allTasks.isEmpty() ? 0.0 : (completed * 100.0 / allTasks.size()));

        // Workload per team member
        List<MemberWorkload> summary = new ArrayList<>();
        List<Users> users = userRepository.findAll();

        for (Users user : users) {
            int assigned = (int) allTasks.stream()
                    .filter(t -> t.getAssignedTo().contains(user))
                    .count();

            int done = (int) allTasks.stream()
                    .filter(t -> t.getAssignedTo().contains(user) && t.getStatus() == TaskStatus.COMPLETED)
                    .count();

            MemberWorkload workload = new MemberWorkload();
            workload.setUserId(user.getId());
            workload.setUsername(user.getUsername());
            workload.setTotalAssigned(assigned);
            workload.setCompleted(done);

            summary.add(workload);
        }

        response.setTeamSummary(summary);
        return response;
    }
}
