package com.example.Assignment.controller;

import com.example.Assignment.entity.SubTask;
import com.example.Assignment.service.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subtasks")
public class SubTaskController {

    @Autowired
    private SubTaskService subTaskService;

    @PostMapping("/{parentTaskId}")
    public SubTask createSubtask(
            @PathVariable Long parentTaskId,
            @RequestBody Map<String, String> body
    ) {
        String name = body.get("name");
        String description = body.get("description");
        LocalDate deadline = LocalDate.parse(body.get("deadline"));
        Long userId = Long.parseLong(body.get("userId"));

        return subTaskService.createSubtask(parentTaskId, name, description, deadline, userId);
    }

    @GetMapping("/{taskId}")
    public List<SubTask> getSubtasks(@PathVariable Long taskId) {
        return subTaskService.getSubtasksByParentTask(taskId);
    }
}
