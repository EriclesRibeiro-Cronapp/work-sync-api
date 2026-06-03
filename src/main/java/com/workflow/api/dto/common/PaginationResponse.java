package com.workflow.api.dto.common;

import java.util.List;

public record PaginationResponse<T>(
        List<T> data,
        int page,
        int size,
        int totalElements,
        int totalPages
) {
}
