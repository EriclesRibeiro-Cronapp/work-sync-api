package com.workflow.api.controller;

import com.workflow.api.dto.task.CreateTaskRequest;
import com.workflow.api.dto.task.TaskResponse;
import com.workflow.api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tasks")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Lista todas as tarefas")
    public ResponseEntity<List<TaskResponse>> getAll(
            Authentication authentication
    ) {
        List<TaskResponse> tasks = taskService.getTasksByUser(
                authentication.getName()
        );
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(
            @Valid @RequestBody CreateTaskRequest request,
            Authentication authentication
            ) {
        TaskResponse task = taskService.create(
                request,
                authentication.getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(task);
    }
}
