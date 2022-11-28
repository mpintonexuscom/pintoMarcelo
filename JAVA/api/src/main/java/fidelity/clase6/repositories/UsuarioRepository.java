package fidelity.clase6.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fidelity.clase6.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    public Usuario getByEmail(String email);
}
