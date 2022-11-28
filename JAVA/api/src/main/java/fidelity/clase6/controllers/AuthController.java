package fidelity.clase6.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fidelity.clase6.configuration.JwtTokenUtils;
import fidelity.clase6.model.Usuario;
import fidelity.clase6.services.JwtUserDetailServiceImpl;
import fidelity.clase6.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UsuarioService usuService;

    @Autowired
    private JwtTokenUtils tokenUtils;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserService;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("uername") String username, 
                                       @RequestParam("password") String password) throws Exception{

        HashMap<String, Object> responseMap = new HashMap<>();
        try {
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (auth.isAuthenticated()) {
                UserDetails userDetails = jwtUserService.loadUserByUsername(username);
                String token = tokenUtils.generateToken(userDetails);
                responseMap.put("error", false);
                responseMap.put("mensaje", "Login exitoso");
                responseMap.put("token", token);
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("mensaje", "Credenciales invalidas");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
            }
        } catch (BadCredentialsException e){
            responseMap.put("error", true);
            responseMap.put("mensaje", "Credenciales invalidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
        } catch (DisabledException e){
            responseMap.put("error", true);
            responseMap.put("mensaje", "Usuario no habiltado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap);
        } catch (Exception e){
            responseMap.put("error", true);
            responseMap.put("mensaje", "Ha ocurrido un error inesperado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap);
        } 
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam("email") String email, 
           @RequestParam("password") String password,
           @RequestParam("rol") String rol) throws Exception{

        String passEnc = new BCryptPasswordEncoder().encode(password);
        Usuario newUsuario = new Usuario(email, passEnc);
        // ver de obtener el rol y asignarselo al usuario

        HashMap<String, Object> responseMap = new HashMap<>();
        UserDetails userDetails = jwtUserService.loadUserByUsername(email);
        String token = tokenUtils.generateToken(userDetails);
        usuService.register(newUsuario);
        responseMap.put("error", false);
        responseMap.put("username", email);
        responseMap.put("mensaje", "Usuario registrado");
        responseMap.put("token", token);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
        
    }

    /*@GetMapping("/logout")
    public ResponseEntity<Usuario> logout(@RequestBody Usuario usuario){

        usuario = usuService.logout(usuario);
        
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }*/

}
