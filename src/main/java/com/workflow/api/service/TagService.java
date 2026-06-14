package com.workflow.api.service;

import com.workflow.api.dto.tag.CreateTagRequest;
import com.workflow.api.dto.tag.TagResponse;
import com.workflow.api.dto.tag.UpdateTagRequest;
import com.workflow.api.entity.Tag;
import com.workflow.api.entity.User;
import com.workflow.api.exception.common.TagAlreadyExistsException;
import com.workflow.api.exception.common.TagNotFoundException;
import com.workflow.api.exception.common.UserNotFoundException;
import com.workflow.api.mapper.TagMapper;
import com.workflow.api.repository.TagRepository;
import com.workflow.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final TagMapper tagMapper;

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    private Tag getTagByUserAndId(
            User user,
            Long id
    ) {
        return tagRepository.findByCreatedByAndId(user, id)
                .orElseThrow(TagNotFoundException::new);
    }

    private boolean tagAlreadyExists(
            User user,
            String name
    ) {
        Optional<Tag> tag = tagRepository.findByCreatedByAndName(
                user,
                name
        );

        return tag.isPresent();
    }

    private Tag getTagByUserEmailAndId(String userEmail, Long id) {
        User user = getUserByEmail(userEmail);

        return tagRepository.findByCreatedByAndId(user, id)
                .orElseThrow(TagNotFoundException::new);
    }

    public TagResponse create(CreateTagRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);

        if (tagAlreadyExists(user, request.name()))
            throw new TagAlreadyExistsException();

        Tag newTag = tagMapper.toEntity(request);
        newTag.setCreatedBy(user);

        Tag savedTag = tagRepository.save(newTag);
        return tagMapper.toResponse(savedTag);
    }

    public List<TagResponse> findAll(String userEmail) {
        User user = getUserByEmail(userEmail);

        List<Tag> tags = tagRepository.findByCreatedBy(user);

        return tagMapper.toResponseList(tags);
    }

    public TagResponse findById(
            Long id,
            String userEmail
    ) {
        return tagMapper.toResponse(
                getTagByUserEmailAndId(userEmail, id)
        );
    }

    public TagResponse update(
            Long id,
            UpdateTagRequest request,
            String userEmail
    ) {
        User user = getUserByEmail(userEmail);

        Tag tag = getTagByUserAndId(user, id);

        if (tagRepository.existsByCreatedByAndNameAndIdNot(
                user,
                request.name(),
                id
        )) {
            throw new TagAlreadyExistsException();
        }

        tag.setName(request.name());

        return tagMapper.toResponse(tagRepository.save(tag));
    }

    public void delete(
            Long id,
            String userEmail
    ) {
        tagRepository.delete(
                getTagByUserEmailAndId(userEmail, id)
        );
    }
}
