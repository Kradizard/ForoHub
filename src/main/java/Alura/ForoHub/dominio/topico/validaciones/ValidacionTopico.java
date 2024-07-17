package Alura.ForoHub.dominio.topico.validaciones;
import Alura.ForoHub.dominio.topico.TopicoDTO;
import Alura.ForoHub.dominio.topico.TopicoUpdate;



public interface ValidacionTopico {
    void validate(TopicoDTO data);

    void validate(TopicoUpdate data);
}
