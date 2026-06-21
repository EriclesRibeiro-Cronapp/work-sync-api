package com.workflow.api.controller;

import com.workflow.api.dto.tag.CreateTagRequest;
import com.workflow.api.dto.tag.TagResponse;
import com.workflow.api.dto.tag.UpdateTagRequest;
import com.workflow.api.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "Tags")
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping
    @Operation(summary = "Criar uma nova tag")
    public ResponseEntity<TagResponse> create(
            @Valid @RequestBody CreateTagRequest request,
            Authentication authentication
            ) {
        TagResponse response = tagService.create(
                request,
                authentication.getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    @Operation(summary = "Listar tags do usuário")
    public ResponseEntity<Set<TagResponse>> findAll(
            Authentication authentication
    ) {
        Set<TagResponse> response = tagService
                .findAll(authentication.getName());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tagId}")
    @Operation(summary = "Buscar tag por ID")
    public ResponseEntity<TagResponse> findById(
            @PathVariable Long tagId,
            Authentication authentication
    ) {
        TagResponse response = tagService.findById(
                tagId,
                authentication.getName()
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{tagId}")
    @Operation(summary = "Editar tag")
    public ResponseEntity<TagResponse> update(
            @PathVariable Long tagId,
            @Valid @RequestBody UpdateTagRequest request,
            Authentication authentication
    ) {
        TagResponse response = tagService.update(
                tagId,
                request,
                authentication.getName()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{tagId}")
    @Operation(summary = "Deletar tag")
    public ResponseEntity<Void> delete(
            @PathVariable Long tagId,
            Authentication authentication
    ) {
        tagService.delete(
                tagId,
                authentication.getName()
        );

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
