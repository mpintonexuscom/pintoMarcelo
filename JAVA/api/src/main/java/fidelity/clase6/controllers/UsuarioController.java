package fidelity.clase6.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import fidelity.clase6.configuration.JwtTokenUtils;
import fidelity.clase6.model.Usuario;
import fidelity.clase6.services.UsuarioService;

@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuService;

    @Autowired
    private JwtTokenUtils tokenUtils;

    @GetMapping("/user/{email}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable String email, 
           @RequestHeader("token") String token) throws Exception {

        HashMap<String, Object> responseMap = new HashMap<>();
        
        if (tokenUtils.validateToken(token)) {
            Usuario usuario = usuService.obtenerUsuarioPorEmail(email);
            if (usuario != null) {
                responseMap.put("error", false);
                responseMap.put("mensaje", "Usuario encontrado");
                responseMap.put("id", usuario.getId());
                responseMap.put("username", usuario.getEmail());
                responseMap.put("rol", usuario.getRol().getNombre());
                return ResponseEntity.status(HttpStatus.OK).body(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("mensaje", "El usuario no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
            }
        } else {
            responseMap.put("error", true);
            responseMap.put("mensaje", "Credenciales invalidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
        }
    }

}
