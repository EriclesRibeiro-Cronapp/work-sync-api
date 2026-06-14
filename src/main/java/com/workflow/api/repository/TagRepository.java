package com.workflow.api.repository;

import com.workflow.api.entity.Tag;
import com.workflow.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByCreatedBy(User createdBy);
    Optional<Tag> findByCreatedByAndId(User createdBy, Long id);
    Optional<Tag> findByCreatedByAndName(User createdBy, String name);
    boolean existsByCreatedByAndNameAndIdNot(
            User createdBy,
            String name,
            Long id
    );
}
