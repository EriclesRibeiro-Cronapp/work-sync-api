package com.workflow.api.mapper;

import com.workflow.api.dto.common.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginationMapper {

    public <T> PaginationResponse<T> toResponse(Page<T> page) {
        return new PaginationResponse<T>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getNumberOfElements(),
                page.getTotalPages()
        );
    }
}
