package Alura.ForoHub.dominio.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
