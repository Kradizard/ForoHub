package Alura.ForoHub.dominio.topico.validaciones;

import Alura.ForoHub.dominio.topico.TopicoDTOUpdate;
import Alura.ForoHub.dominio.topico.TopicoUpdate;
import Alura.ForoHub.dominio.usuarios.Usuario;
import org.springframework.stereotype.Component;

@Component
public interface ValidacionUpdate {
    void validate(TopicoDTOUpdate data, Usuario user);

    void validate(TopicoUpdate data, Usuario user);
}
