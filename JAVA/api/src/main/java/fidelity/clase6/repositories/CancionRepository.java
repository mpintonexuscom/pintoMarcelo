package fidelity.clase6.repositories;

import org.springframework.data.repository.CrudRepository;

import fidelity.clase6.model.Cancion;

public interface CancionRepository extends CrudRepository<Cancion, Integer>{
    
}
