package Alura.ForoHub.dominio.topico.validaciones;

import Alura.ForoHub.dominio.topico.TopicoDTOUpdate;
import Alura.ForoHub.dominio.topico.TopicoRepositorio;
import Alura.ForoHub.dominio.topico.TopicoUpdate;
import Alura.ForoHub.dominio.usuarios.Usuario;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class ValidacionAutor implements ValidacionUpdate {
    @Autowired
    private TopicoRepositorio topicRepository;

    @Override
    public void validate(TopicoDTOUpdate data, Usuario usuario) {
        var topic = topicRepository.findById(data.id()).orElse(null);

        assert topic != null;
        if (!Objects.equals(topic.getAuthor().getId(), usuario.getId())) {
            throw new ValidationException("Este topico no le pertenece a este usuario");
        }
    }

    @Override
    public void validate(TopicoUpdate data, Usuario usuario) {
        var topic = topicRepository.findById(usuario.getId()).orElse(null);
        assert topic != null;
        if (!Objects.equals(topic.getAuthor().getId(), usuario.getId())) {
            throw new ValidationException("Este topico no le pertenece a este usuario");
        }
    }
}
