package com.workflow.api.repository;

import com.workflow.api.entity.Task;
import com.workflow.api.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByCreatedBy(User user, Pageable pageable);
    Page<Task> findAllByAssignedTo(User assignedTo, Pageable pageable);
    Optional<Task> findByCreatedByAndId(User user, Long id);
    Page<Task> findAllBySprintId(Long id, Pageable pageable);
}
