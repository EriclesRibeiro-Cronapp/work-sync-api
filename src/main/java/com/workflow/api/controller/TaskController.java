package com.workflow.api.controller;

import com.workflow.api.dto.common.PaginationResponse;
import com.workflow.api.dto.task.CreateTaskRequest;
import com.workflow.api.dto.task.TaskResponse;
import com.workflow.api.dto.task.UpdateTaskRequest;
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

@Tag(name = "Tasks")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<PaginationResponse<TaskResponse>> getAll(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {

        PaginationResponse<TaskResponse> tasks = taskService.getAllTasks(
                authentication.getName(),
                pageNo,
                pageSize);

        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    @Operation(summary = "Criar uma nova tarefa")
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

    @GetMapping("/{id}")
    @Operation(summary = "Obter tarefa pelo ID")
    public ResponseEntity<TaskResponse> getById(
            @PathVariable Long id,
            Authentication authentication
    ) {
        TaskResponse task = taskService.findById(authentication.getName(), id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar tarefa")
    public ResponseEntity<TaskResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest request,
            Authentication authentication
            ) {
        TaskResponse response = taskService.update(
                id,
                authentication.getName(),
                request
        );

        return ResponseEntity.ok(response);
    }

}
