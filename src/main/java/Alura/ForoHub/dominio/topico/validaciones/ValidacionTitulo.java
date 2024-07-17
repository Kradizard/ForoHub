package Alura.ForoHub.dominio.topico.validaciones;

import Alura.ForoHub.dominio.topico.TopicoDTO;
import Alura.ForoHub.dominio.topico.TopicoRepositorio;
import Alura.ForoHub.dominio.topico.TopicoUpdate;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacionTitulo implements ValidacionTopico {

    @Autowired
    TopicoRepositorio topicRepository;

    @Override
    public void validate(TopicoDTO dataTopic) {
        var title = topicRepository.existsByTitle(dataTopic.title());
        if (title) {
            throw new ValidationException("There is already a topic with that title.");
        }
    }

    @Override
    public void validate(TopicoUpdate data) {
        var title = topicRepository.existsByTitle(data.title());
        if (title) {
            throw new ValidationException("There is already a topic with that title.");
        }
    }
}
