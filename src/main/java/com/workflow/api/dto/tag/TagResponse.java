package com.workflow.api.dto.tag;

import com.workflow.api.entity.Task;

import java.util.Set;

public record TagResponse(
        Long id,
        String name,
        Set<Task> tasks
) {
}
