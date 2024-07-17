package Alura.ForoHub.dominio.topico;

import jakarta.validation.constraints.NotBlank;

public record TopicoDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String course
) {
}
