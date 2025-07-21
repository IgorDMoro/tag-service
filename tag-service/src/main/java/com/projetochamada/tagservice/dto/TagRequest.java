package com.projetochamada.tagservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor; // Adicionado para o Lombok gerar o construtor com todos os campos
import lombok.NoArgsConstructor;  // Adicionado para o Lombok gerar o construtor sem argumentos

@Data
@AllArgsConstructor // Adicionado para gerar o construtor com todos os campos
@NoArgsConstructor  // Adicionado para gerar o construtor padrão (necessário por algumas libs, ex: Jackson)
public class TagRequest {

        @NotBlank(message = "O nome não pode estar em branco") //
        @Size(max = 10, message = "O nome da tag não pode ter mais de 10 caracteres")
        private String name;

        // Adicionado o campo color. Você pode adicionar validações de formato para hexadecimal aqui.
        // Ex: @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Formato de cor inválido. Use #RRGGBB ou #RGB.")
        @Size(max = 7, message = "A cor da tag não pode ter mais de 7 caracteres (ex: #RRGGBB)")
        private String color;
}