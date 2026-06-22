package com.workflow.api.controller;

import com.workflow.api.dto.common.PaginationResponse;
import com.workflow.api.dto.sprint.CreateSprintRequest;
import com.workflow.api.dto.sprint.PatchSprintRequest;
import com.workflow.api.dto.sprint.SprintResponse;
import com.workflow.api.dto.sprint.UpdateSprintRequest;
import com.workflow.api.dto.task.TaskResponse;
import com.workflow.api.service.SprintService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Sprints")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/sprints")
@RequiredArgsConstructor
public class SprintController {
    private final SprintService sprintService;

    @GetMapping("/{id}/tasks")
    public ResponseEntity<PaginationResponse<TaskResponse>> getTasksBySprintId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {

        PaginationResponse<TaskResponse> response = sprintService.findAllTasksBySprintId(
                id,
                pageNo,
                pageSize
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SprintResponse> getSprintById(@PathVariable Long id) {
        SprintResponse response = sprintService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<SprintResponse>> getSprints(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PaginationResponse<SprintResponse> response = sprintService.findAll(
                pageNo,
                pageSize
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SprintResponse> createSprint(
            @Valid @RequestBody CreateSprintRequest request
    ) {
        SprintResponse response = sprintService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SprintResponse> updateSprint(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSprintRequest request
    ) {
        SprintResponse response = sprintService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SprintResponse> partialUpdateSprint(
            @PathVariable Long id,
            @Valid @RequestBody PatchSprintRequest request
    ) {
        SprintResponse response = sprintService.partialUpdate(id, request);
        return ResponseEntity.ok(response);
    }
}
