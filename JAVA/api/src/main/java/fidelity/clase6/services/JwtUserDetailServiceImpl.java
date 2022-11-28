package fidelity.clase6.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fidelity.clase6.configuration.JwtUser;
import fidelity.clase6.model.Usuario;
import fidelity.clase6.repositories.UsuarioRepository;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuRepo.getByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("El usuario no existe");
        } else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+usuario.getRol().getNombre()));
            //JwtUser tempUser = new JwtUser();

            return new JwtUser(usuario.getEmail(), usuario.getPassword(), authorities);
        }
    }
}
