package com.projetochamada.tagservice.service;

import com.projetochamada.tagservice.dto.TagRequest;
import com.projetochamada.tagservice.dto.TagResponse;
import com.projetochamada.tagservice.model.Tag; // Certifique-se de que Tag.java tem o campo 'color'
import com.projetochamada.tagservice.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Transactional
    public TagResponse createTag(TagRequest tagRequest) {
        // Você pode adicionar uma validação aqui para nome duplicado se quiser,
        // mas a anotação unique = true na entidade Tag já garante isso no BD.
        // if (tagRepository.findByName(tagRequest.getName()).isPresent()) {
        //    throw new RuntimeException("Já existe uma tag com este nome: " + tagRequest.getName());
        // }

        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        tag.setColor(tagRequest.getColor()); // Define a cor da tag

        Tag savedTag = tagRepository.save(tag);
        return convertToResponse(savedTag);
    }

    @Transactional(readOnly = true)
    public List<TagResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TagResponse getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag não encontrada com o id: " + id)); //
        return convertToResponse(tag);
    }

    @Transactional
    public TagResponse updateTag(Long id, TagRequest tagRequest) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag não encontrada com o id: " + id)); //

        tag.setName(tagRequest.getName());
        tag.setColor(tagRequest.getColor()); // Atualiza a cor da tag

        Tag updatedTag = tagRepository.save(tag);
        return convertToResponse(updatedTag);
    }

    @Transactional
    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new RuntimeException("Tag não encontrada com o id: " + id); //
        }
        tagRepository.deleteById(id);
    }

    // Método auxiliar para converter a entidade Tag para o DTO de resposta
    private TagResponse convertToResponse(Tag tag) {
        // CORREÇÃO: Passa todos os três argumentos para o construtor do TagResponse record.
        return new TagResponse(tag.getId(), tag.getName(), tag.getColor());
    }
}