package com.workflow.api.mapper;

import com.workflow.api.dto.sprint.CreateSprintRequest;
import com.workflow.api.dto.sprint.SprintDetailsResponse;
import com.workflow.api.dto.sprint.SprintResponse;
import com.workflow.api.entity.Sprint;
import com.workflow.api.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SprintMapper {
    private final TaskMapper taskMapper;

    public SprintResponse toResponse(Sprint sprint) {
        return new SprintResponse(
                sprint.getId(),
                sprint.getName(),
                sprint.getDescription(),
                sprint.getStatus(),
                sprint.getStartDate(),
                sprint.getEndDate(),
                sprint.getCreatedAt(),
                sprint.getUpdatedAt()
        );
    }

    public SprintDetailsResponse toDetailsResponse(Sprint sprint,List<Task> tasks) {
        return new SprintDetailsResponse(
                sprint.getId(),
                sprint.getName(),
                sprint.getDescription(),
                sprint.getStatus(),
                sprint.getStartDate(),
                sprint.getEndDate(),
                sprint.getCreatedAt(),
                sprint.getUpdatedAt(),
                taskMapper.toResponseList(tasks)
        );
    }

    public Sprint toEntity(CreateSprintRequest request) {
        return Sprint.builder()
                .name(request.name())
                .description(request.description())
                .status(request.status())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();
    }

    public List<SprintResponse> toResponseList(List<Sprint> sprints) {
        if (sprints==null || sprints.isEmpty()) {
            return new ArrayList<>();
        }

        return sprints.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
