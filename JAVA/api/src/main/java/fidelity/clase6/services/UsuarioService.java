package fidelity.clase6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fidelity.clase6.model.Usuario;
import fidelity.clase6.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuRepo;

    private  boolean userVerified(Usuario usuario){
        return usuRepo.existsById(usuario.getId());
    }

    public Usuario register(Usuario usuario){

        if (this.userVerified(usuario)) {
            return null;
        }
        
        return usuario;
    }

    public Usuario obtenerUsuarioPorEmail(String email){
        
        return usuRepo.getByEmail(email);
    }
}
