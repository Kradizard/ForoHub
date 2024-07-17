package Alura.ForoHub.dominio.topico;

import Alura.ForoHub.dominio.topico.validaciones.ValidacionTopico;
import Alura.ForoHub.dominio.topico.validaciones.ValidacionUpdate;
import Alura.ForoHub.dominio.usuarios.Usuario;
import Alura.ForoHub.dominio.usuarios.UsuarioRepositorio;
import Alura.ForoHub.servicios.errores.IntegrityValidation;
import Alura.ForoHub.servicios.seguridad.Token;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoServicio {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    TopicoRepositorio topicoRepositorio;
    @Autowired
    private Token tokenService;
    //@Autowired
    private List<ValidacionTopico> validations;
    //@Autowired
    private List<ValidacionUpdate> updateTopicals;

    public Topico addTopico(TopicoDTO topic, HttpServletRequest request) {
        var usuario = getAuthenticatedUser(request);
        if (usuarioRepositorio.findById(usuario.getId()).isEmpty()) {
            throw new IntegrityValidation("Este id para este usuario no existe");
        }
        validations.forEach(v -> {
            v.validate(topic);
        });
        return topicoRepositorio.save(new Topico(usuario, topic));
    }

    private Usuario getAuthenticatedUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.replace("Bearer ", "");
        String subject = tokenService.getSubject(token);
        return (Usuario) usuarioRepositorio.findByEmail(subject);
    }

    public TopicoDetalle update(Long id, TopicoUpdate data, HttpServletRequest request) {
        if (data.title() == null && data.message() == null) {
            throw new IntegrityValidation("No hay nada para editar");
        }
        if (!topicoRepositorio.existsById(id)) {
            throw new IntegrityValidation("No hay topicos con ese id");
        }

        var usuario = getAuthenticatedUser(request);
        TopicoDTO topicoDTO = new TopicoDTO(data.title(), data.message(), null);
        validations.forEach(v -> v.validate(topicoDTO));
        updateTopicals.forEach(v -> v.validate(data, usuario));

        var topico = topicoRepositorio.getReferenceById(id);
        if (data.title() != null && data.message() != null) {
            topico.setMessage(data.message());
            topico.setTitle(data.title());
        } else if (data.title() == null) {
            topico.setMessage(data.message());
        } else {
            topico.setTitle(data.title());
        }
        topico.update();
        return new TopicoDetalle(topico);
    }

    public void delete(Long id, HttpServletRequest request) {
        if (!topicoRepositorio.existsById(id)) {
            throw new IntegrityValidation("No existe el topico");
        }
        var user = getAuthenticatedUser(request);
        TopicoDTOUpdate data = new TopicoDTOUpdate(id, null, null);
        updateTopicals.forEach(v -> v.validate(data, user));
        var topic = topicoRepositorio.getReferenceById(id);
        topic.delete();
    }
}
