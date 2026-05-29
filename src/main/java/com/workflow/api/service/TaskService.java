package com.workflow.api.service;

import com.workflow.api.dto.CreateTaskRequest;
import com.workflow.api.dto.TaskResponse;
import com.workflow.api.entity.Task;
import com.workflow.api.entity.User;
import com.workflow.api.mapper.TaskMapper;
import com.workflow.api.repository.TaskRepository;
import com.workflow.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponse> getTasksByUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        List<Task> tasks = taskRepository.findByUser(user);

        return taskMapper.toResponseList(tasks);
    }

    public TaskResponse create(
            CreateTaskRequest request,
            String userEmail
    ) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        Task newTask = taskMapper.toEntity(request);
        newTask.setUser(user);

        Task savedTask = taskRepository.save(newTask);

        return taskMapper.toResponse(savedTask);
    }
}
