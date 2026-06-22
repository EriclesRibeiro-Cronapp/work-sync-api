package com.workflow.api.service;

import com.workflow.api.dto.common.PaginationResponse;
import com.workflow.api.dto.task.CreateTaskRequest;
import com.workflow.api.dto.task.TaskResponse;
import com.workflow.api.dto.task.UpdateTaskRequest;
import com.workflow.api.entity.Tag;
import com.workflow.api.entity.Task;
import com.workflow.api.entity.User;
import com.workflow.api.exception.task.TaskNotFoundException;
import com.workflow.api.exception.common.UserNotFoundException;
import com.workflow.api.mapper.PaginationMapper;
import com.workflow.api.mapper.TaskMapper;
import com.workflow.api.repository.TagRepository;
import com.workflow.api.repository.TaskRepository;
import com.workflow.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    private final TaskMapper taskMapper;

    private final PaginationMapper paginationMapper;

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public PaginationResponse<TaskResponse> getAllTasks(
            String userEmail,
            int pageNo,
            int pageSize
    ) {
        User user = getUserByEmail(userEmail);

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Task> tasks = taskRepository.findAllByCreatedBy(user, pageable);

        return paginationMapper.toResponse(
                tasks.map(taskMapper::toResponse)
        );
    }

    public TaskResponse create(
            CreateTaskRequest request,
            String userEmail
    ) {
        User user = getUserByEmail(userEmail);

        Task newTask = taskMapper.toEntity(request);
        newTask.setCreatedBy(user);

        Task savedTask = taskRepository.save(newTask);

        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse findById(String userEmail, Long id) {
        User user = getUserByEmail(userEmail);

        Task task = taskRepository.findByCreatedByAndId(user, id)
                .orElseThrow(TaskNotFoundException::new);

        return taskMapper.toResponse(task);
    }

    public TaskResponse update(
            Long id,
            String userEmail,
            UpdateTaskRequest request
    ) {
        getUserByEmail(userEmail);

        // TODO: validar acesso à task através do projeto/organização
        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        task.setStatus(request.status());
        task.setDescription(request.description());
        task.setTitle(request.title());
        task.setPriority(request.priority());

        if (request.assignedId() != null) {
            User assignedUser = userRepository.findById(request.assignedId())
                    .orElseThrow(UserNotFoundException::new);

            task.setAssignedTo(assignedUser);
        } else {
            task.setAssignedTo(null);
        }

        if (request.tagIds() != null) {
            Set<Tag> tags = new HashSet<>(
                    tagRepository.findAllById(request.tagIds())
            );
            task.setTags(tags);
        }

        return taskMapper.toResponse(taskRepository.save(task));
    }
}
