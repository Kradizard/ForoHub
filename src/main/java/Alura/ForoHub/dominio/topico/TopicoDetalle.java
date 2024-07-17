package Alura.ForoHub.dominio.topico;

import java.time.LocalDateTime;

public record TopicoDetalle(
        String title,
        String message,
        LocalDateTime creationDate,
        TopicoEstado status,
        Long author
) {
    public TopicoDetalle(Topico topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getCreation(), topic.getStatus(), topic.getAuthor().getId());
    }
}