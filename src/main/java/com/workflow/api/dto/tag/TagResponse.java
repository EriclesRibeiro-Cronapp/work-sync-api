package com.workflow.api.dto.tag;

import com.workflow.api.entity.Task;

import java.util.Set;

public record TagResponse(
        String name,
        Set<Task> tasks
) {
}
