package com.workflow.api.repository;

import com.workflow.api.entity.Task;
import com.workflow.api.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByUser(User user, Pageable pageable);
}
