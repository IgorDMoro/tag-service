package com.projetochamada.tagservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TagRequest {

        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 10, message = "O nome da tag não pode ter mais de 10 caracteres")
        private String name;
}
