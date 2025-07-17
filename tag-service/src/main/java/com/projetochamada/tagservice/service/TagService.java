package com.projetochamada.tagservice.service;

import com.projetochamada.tagservice.dto.TagRequest;
import com.projetochamada.tagservice.dto.TagResponse;
import com.projetochamada.tagservice.model.Tag;
import com.projetochamada.tagservice.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    public TagResponse createTag(TagRequest tagRequest) {
        // ### CORREÇÃO AQUI ###
        // Criamos a entidade Tag passando null para o ID, pois ele será gerado pelo banco,
        // e o nome vindo da requisição. Isso garante que estamos usando o construtor correto.
        Tag tag = new Tag(null, tagRequest.name());

        Tag savedTag = tagRepository.save(tag);
        return new TagResponse(savedTag.getId(), savedTag.getName());
    }

    @Transactional(readOnly = true)
    public List<TagResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> new TagResponse(tag.getId(), tag.getName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TagResponse getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));
        return new TagResponse(tag.getId(), tag.getName());
    }

    @Transactional
    public TagResponse updateTag(Long id, TagRequest tagRequest) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));
        tag.setName(tagRequest.name());
        Tag updatedTag = tagRepository.save(tag);
        return new TagResponse(updatedTag.getId(), updatedTag.getName());
    }

    @Transactional
    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new RuntimeException("Tag not found with id: " + id);
        }
        tagRepository.deleteById(id);
    }
}
