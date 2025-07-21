package com.projetochamada.tagservice.controller;

import com.projetochamada.tagservice.dto.TagRequest;
import com.projetochamada.tagservice.dto.TagResponse;
import com.projetochamada.tagservice.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<TagResponse> createTag(@Valid @RequestBody TagRequest tagRequest) {
        TagResponse createdTag = tagService.createTag(tagRequest);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<TagResponse> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTagById(@PathVariable Long id) {
        TagResponse tag = tagService.getTagById(id);
        return ResponseEntity.ok(tag);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<TagResponse> updateTag(@PathVariable Long id, @Valid @RequestBody TagRequest tagRequest) {
        TagResponse updatedTag = tagService.updateTag(id, tagRequest);
        return ResponseEntity.ok(updatedTag);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
