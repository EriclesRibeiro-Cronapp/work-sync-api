package com.workflow.api.service;

import com.workflow.api.dto.CreateTaskRequest;
import com.workflow.api.entity.Task;
import com.workflow.api.entity.User;
import com.workflow.api.repository.TaskRepository;
import com.workflow.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Optional<List<Task>> getTasksByUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        List<Task> tasks = taskRepository.findByUser(user);
        return Optional.ofNullable(tasks);
    }

    public Task create(
            CreateTaskRequest request,
            String userEmail
    ) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        Task newTask = Task.builder()
                .title(request.title())
                .description(request.description())
                .completed(false)
                .user(user)
                .build();

        return taskRepository.save(newTask);
    }
}
