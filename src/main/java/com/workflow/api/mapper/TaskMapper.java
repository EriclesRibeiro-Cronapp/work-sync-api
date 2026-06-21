package com.workflow.api.mapper;

import com.workflow.api.dto.task.CreateTaskRequest;
import com.workflow.api.dto.task.TaskResponse;
import com.workflow.api.entity.Task;
import com.workflow.api.enums.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final TagMapper tagMapper;

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getType(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                tagMapper.toResponseSet(task.getTags())
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
        if (tasks==null || tasks.isEmpty()) {
            return new ArrayList<>();
        }

        return tasks.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
