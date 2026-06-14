package com.workflow.api.mapper;

import com.workflow.api.dto.task.CreateTaskRequest;
import com.workflow.api.dto.task.TaskResponse;
import com.workflow.api.entity.Task;
import com.workflow.api.enums.TaskStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {
    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getType(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getTags()
        );
    }

    public Task toEntity(CreateTaskRequest request) {
        return Task.builder()
                .description(request.description())
                .title(request.title())
                .status(TaskStatus.PENDING)
                .priority(request.priority())
                .type(request.type())
                .build();
    }

    public List<TaskResponse> toResponseList(List<Task> tasks) {
        return tasks.stream()
                .map(this::toResponse)
                .toList();
    }
}
