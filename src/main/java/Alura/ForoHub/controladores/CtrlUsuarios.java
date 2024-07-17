package Alura.ForoHub.controladores;


import Alura.ForoHub.dominio.usuarios.Usuario;
import Alura.ForoHub.dominio.usuarios.UsuarioDTO;
import Alura.ForoHub.servicios.seguridad.Token;
import Alura.ForoHub.servicios.seguridad.TokenDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/login")
@SecurityRequirement(name = "bearer-key")
public class CtrlUsuarios {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Token token;

    @PostMapping
    public ResponseEntity<TokenDTO> getLogin(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDTO.email(), usuarioDTO.password());
        var userAuth = authenticationManager.authenticate(authenticationToken);
        var token = this.token.createToken((Usuario) userAuth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
