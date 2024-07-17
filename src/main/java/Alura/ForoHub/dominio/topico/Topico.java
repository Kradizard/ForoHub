package Alura.ForoHub.dominio.topico;

import Alura.ForoHub.dominio.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String message;
    private LocalDateTime creation;
    @Enumerated(EnumType.STRING)
    private TopicoEstado  status;
    @JoinColumn(name = "author_id")
    @ManyToOne
    private Usuario author;
    private String course;

    public Topico(Usuario usuario, TopicoDTO topic) {
        this.title = topic.title();
        this.message = topic.message();
        this.creation = LocalDateTime.now();
        this.status = TopicoEstado.CREADO;
        this.author = usuario;
        this.course = topic.course();
    }

    public void delete() {
        this.status = TopicoEstado.ELIMINADO;
    }

    public void update() {
        this.status = TopicoEstado.MODIFICADO;
    }
}
