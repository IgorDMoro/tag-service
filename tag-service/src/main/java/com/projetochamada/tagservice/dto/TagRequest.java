package com.projetochamada.tagservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagRequest {

        @NotBlank(message = "O nome não pode estar em branco") //
        @Size(max = 10, message = "O nome da tag não pode ter mais de 10 caracteres")
        private String name;

        @Size(max = 7, message = "A cor da tag não pode ter mais de 7 caracteres (ex: #RRGGBB)")
        private String color;
}