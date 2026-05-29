package com.workflow.api.mapper;

import com.workflow.api.dto.CreateTaskRequest;
import com.workflow.api.dto.TaskResponse;
import com.workflow.api.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {
    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getTitle(),
                task.getDescription(),
                task.getCompleted()
        );
    }

    public Task toEntity(CreateTaskRequest request) {
        return Task.builder()
                .description(request.description())
                .title(request.title())
                .completed(false)
                .build();
    }

    public List<TaskResponse> toResponseList(List<Task> tasks) {
        return tasks.stream()
                .map(this::toResponse)
                .toList();
    }
}
