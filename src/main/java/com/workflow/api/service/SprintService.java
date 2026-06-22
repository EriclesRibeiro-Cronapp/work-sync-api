package com.workflow.api.service;

import com.workflow.api.dto.common.PaginationResponse;
import com.workflow.api.dto.sprint.CreateSprintRequest;
import com.workflow.api.dto.sprint.PatchSprintRequest;
import com.workflow.api.dto.sprint.SprintResponse;
import com.workflow.api.dto.sprint.UpdateSprintRequest;
import com.workflow.api.dto.task.TaskResponse;
import com.workflow.api.entity.Sprint;
import com.workflow.api.entity.Task;
import com.workflow.api.entity.User;
import com.workflow.api.exception.common.InvalidFieldException;
import com.workflow.api.exception.common.UserNotFoundException;
import com.workflow.api.exception.sprint.InvalidSprintDateException;
import com.workflow.api.exception.sprint.SprintNotFoundException;
import com.workflow.api.exception.task.TaskNotFoundException;
import com.workflow.api.mapper.PaginationMapper;
import com.workflow.api.mapper.SprintMapper;
import com.workflow.api.mapper.TaskMapper;
import com.workflow.api.repository.SprintRepository;
import com.workflow.api.repository.TaskRepository;
import com.workflow.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;
    private final TaskRepository taskRepository;

    private final SprintMapper sprintMapper;
    private final TaskMapper taskMapper;
    private final PaginationMapper paginationMapper;

    private Sprint getSprintById(Long id) {
        return sprintRepository.findById(id)
                .orElseThrow(SprintNotFoundException::new);
    }

    public PaginationResponse<TaskResponse> findAllTasksBySprintId(
            Long id,
            int pageNo,
            int pageSize
    ) {
        getSprintById(id);

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Task> tasks = taskRepository.findAllBySprintId(id, pageable);

        return paginationMapper.toResponse(
                tasks.map(taskMapper::toResponse)
        );
    }

    public SprintResponse findById(Long id) {
        Sprint sprint = getSprintById(id);

        return sprintMapper.toResponse(sprint);
    }

    public PaginationResponse<SprintResponse> findAll(
            int pageNo,
            int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Sprint> sprints = sprintRepository.findAll(pageable);

        return paginationMapper.toResponse(
                sprints.map(sprintMapper::toResponse)
        );
    }

    public SprintResponse create(CreateSprintRequest request) {
        if (request.startDate().isAfter(request.endDate()))
            throw new InvalidSprintDateException();

        Sprint newSprint = sprintMapper.toEntity(request);
        Sprint savedSprint = sprintRepository.save(newSprint);

        return sprintMapper.toResponse(savedSprint);
    }

    public SprintResponse update(Long id, UpdateSprintRequest request) {
        Sprint sprint =  getSprintById(id);

        if (request.startDate().isAfter(request.endDate()))
            throw new InvalidSprintDateException();

        sprint.setName(request.name());
        sprint.setDescription(request.description());
        sprint.setStatus(request.status());
        sprint.setStartDate(request.startDate());
        sprint.setEndDate(request.endDate());

        if (request.tasks() != null) {
            List<Task> tasks = taskRepository.findAllById(request.tasks());

            if (tasks.size() != request.tasks().size()) {
                throw new TaskNotFoundException();
            }

            sprint.setTasks(tasks);
        }

        Sprint updated = sprintRepository.save(sprint);

        return sprintMapper.toResponse(updated);
    }

    public SprintResponse partialUpdate(Long id, PatchSprintRequest request) {
        Sprint sprint = getSprintById(id);

        LocalDate startDate =
                request.startDate() != null
                        ? request.startDate()
                        :sprint.getStartDate();

        LocalDate endDate =
                request.endDate() != null
                        ? request.endDate()
                        :sprint.getEndDate();

        if (startDate.isAfter(endDate))
            throw new InvalidSprintDateException();

        if (request.name() != null) {
            if (request.name().isBlank())
                throw new InvalidFieldException("O campo 'name' não pode ser vazio");

            sprint.setName(request.name());
        }

        if (request.description() != null) {
            if (request.description().isBlank())
                throw new InvalidFieldException("O campo 'description' não pode ser vazio");
            sprint.setDescription(request.description());
        }

        if (request.status() != null)
            sprint.setStatus(request.status());

        sprint.setStartDate(startDate);
        sprint.setEndDate(endDate);

        Sprint updated = sprintRepository.save(sprint);
        return sprintMapper.toResponse(updated);
    }
}
