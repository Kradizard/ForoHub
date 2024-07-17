package Alura.ForoHub.controladores;

import Alura.ForoHub.dominio.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class CtrlTopicos {
    @Autowired
    private TopicoServicio topicoServicio;
    @Autowired
    private TopicoRepositorio topicoRepositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> addTopic(@RequestBody @Valid TopicoDTO data, HttpServletRequest request, UriComponentsBuilder uriBuilder) {
        var response = topicoServicio.addTopico(data, request);
        var res = new TopicoRespuesta(response);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(url).body(res);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoRespuesta>> getTopics(@PageableDefault(sort = "creation", direction = Sort.Direction.ASC, size = 10) Pageable page) {
        return ResponseEntity.ok(topicoRepositorio.findAll(page).map(TopicoRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TopicoDetalle>> getTopic(@PathVariable Long id) {
        return ResponseEntity.ok(topicoRepositorio.findId(id).map(TopicoDetalle::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDetalle> topicoUpdate(@PathVariable Long id, @Valid @RequestBody TopicoUpdate data, HttpServletRequest request){
        var response = topicoServicio.update(id, data,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id,HttpServletRequest request){
        topicoServicio.delete(id,request);
        return ResponseEntity.noContent().build();
    }
}
