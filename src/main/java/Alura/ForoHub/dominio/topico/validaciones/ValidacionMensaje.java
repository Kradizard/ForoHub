package Alura.ForoHub.dominio.topico.validaciones;

import Alura.ForoHub.dominio.topico.TopicoDTO;
import Alura.ForoHub.dominio.topico.TopicoRepositorio;
import Alura.ForoHub.dominio.topico.TopicoUpdate;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacionMensaje implements ValidacionTopico {
    @Autowired
    TopicoRepositorio topicRepository;

    @Override
    public void validate(TopicoDTO dataTopic) {
        var message = topicRepository.existsByMessage(dataTopic.message());
        if (message) {
            throw new ValidationException("There is already a topic with that message.");
        }
    }

    @Override
    public void validate(TopicoUpdate data) {
        var message = topicRepository.existsByMessage(data.message());
        if (message) {
            throw new ValidationException("There is already a topic with that message.");
        }
    }
}