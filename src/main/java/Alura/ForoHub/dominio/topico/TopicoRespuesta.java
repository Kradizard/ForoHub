package Alura.ForoHub.dominio.topico;

import java.time.LocalDateTime;

public record TopicoRespuesta(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        Long author,
        String course
) {
    public TopicoRespuesta(Topico topico) {
        this(topico.getId(), topico.getTitle(), topico.getMessage(),
                topico.getCreation(), topico.getStatus().toString(),
                topico.getAuthor().getId(), topico.getCourse());
    }
}