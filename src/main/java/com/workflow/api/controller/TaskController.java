package com.workflow.api.controller;

import com.workflow.api.dto.CreateTaskRequest;
import com.workflow.api.entity.Task;
import com.workflow.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAll(
            Authentication authentication
    ) {
        Optional<List<Task>> tasks = taskService.getTasksByUser(
                authentication.getName()
        );
        return ResponseEntity.ok(tasks.orElse(null));
    }

    @PostMapping
    public ResponseEntity<Task> create(
            @RequestBody CreateTaskRequest request,
            Authentication authentication
            ) {
        Task task = taskService.create(
                request,
                authentication.getName()
        );

        return ResponseEntity.ok(task);
    }
}
