package com.example.Assignment.entity;

import com.example.Assignment.enums.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private Users assignedTo;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.TODO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public Users getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Users assignedTo) {
        this.assignedTo = assignedTo;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", parentTask=" + parentTask +
                ", assignedTo=" + assignedTo +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}
