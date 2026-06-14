package com.workflow.api.service;

import com.workflow.api.dto.common.PaginationResponse;
import com.workflow.api.dto.task.CreateTaskRequest;
import com.workflow.api.dto.task.TaskResponse;
import com.workflow.api.entity.Task;
import com.workflow.api.entity.User;
import com.workflow.api.exception.common.TaskNotFoundException;
import com.workflow.api.exception.common.UserNotFoundException;
import com.workflow.api.mapper.PaginationMapper;
import com.workflow.api.mapper.TaskMapper;
import com.workflow.api.repository.TaskRepository;
import com.workflow.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final PaginationMapper paginationMapper;

    public PaginationResponse<TaskResponse> getAllTasks(
            String userEmail,
            int pageNo,
            int pageSize
    ) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);

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
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);

        Task newTask = taskMapper.toEntity(request);
        newTask.setCreatedBy(user);

        Task savedTask = taskRepository.save(newTask);

        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse findById(String email, Long id) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        Task task = taskRepository.findByCreatedByAndId(user, id)
                .orElseThrow(TaskNotFoundException::new);

        return taskMapper.toResponse(task);
    }
}
