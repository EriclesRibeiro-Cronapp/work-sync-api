package com.workflow.api.mapper;

import com.workflow.api.dto.tag.CreateTagRequest;
import com.workflow.api.dto.tag.TagResponse;
import com.workflow.api.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TagMapper {

    public TagResponse toResponse(Tag tag) {
        return new TagResponse(
                tag.getId(),
                tag.getName()
        );
    }

    public Tag toEntity(CreateTagRequest request) {
        return Tag.builder()
                .name(request.name())
                .build();
    }

    public Set<TagResponse> toResponseSet(Set<Tag> tags) {
        if (tags == null) {
            return Set.of();
        }

        return tags.stream()
                .map(this::toResponse)
                .collect(Collectors.toSet());
    }
}
