package com.workflow.api.controller;

import com.workflow.api.dto.common.PaginationResponse;
import com.workflow.api.dto.task.CreateTaskRequest;
import com.workflow.api.dto.task.TaskResponse;
import com.workflow.api.entity.Task;
import com.workflow.api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Tasks")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

//    @GetMapping
//    @Operation(summary = "Lista todas as tarefas")
//    public ResponseEntity<List<TaskResponse>> getAll(
//            Authentication authentication
//    ) {
//        List<TaskResponse> tasks = taskService.getAllTasks(
//                authentication.getName(),
//        );
//        return ResponseEntity.ok(tasks);
//    }

    @GetMapping
    public ResponseEntity<PaginationResponse<TaskResponse>> getTasks(
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
}
