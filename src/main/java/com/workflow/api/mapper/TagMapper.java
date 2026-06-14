package com.workflow.api.mapper;

import com.workflow.api.dto.tag.CreateTagRequest;
import com.workflow.api.dto.tag.TagResponse;
import com.workflow.api.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagMapper {
    public TagResponse toResponse(Tag tag) {
        return new TagResponse(
                tag.getId(),
                tag.getName(),
                tag.getTasks()
        );
    }

    public Tag toEntity(CreateTagRequest request) {
        return Tag.builder()
                .name(request.name())
                .build();
    }

    public List<TagResponse> toResponseList(List<Tag> tags) {
        return tags.stream()
                .map(this::toResponse)
                .toList();
    }
}
