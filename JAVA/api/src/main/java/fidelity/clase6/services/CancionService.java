package fidelity.clase6.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fidelity.clase6.dtos.NuevaCancioDTO;
import fidelity.clase6.model.Cancion;
import fidelity.clase6.repositories.CancionRepository;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepo;
    
    public CancionService(){

    }

    public Cancion devolverCancionPorId(Integer id){
        Optional<Cancion> cancion = cancionRepo.findById(id);

        return cancion.orElse(null);
    }

    public Cancion nuevaCancion(NuevaCancioDTO nuevaCancionDTO){

        Cancion nuevaCancion = new Cancion();
        nuevaCancionDTO.setNombre(nuevaCancionDTO.getNombre());
        nuevaCancionDTO.setDuracion(nuevaCancionDTO.getDuracion());

        cancionRepo.save(nuevaCancion);

        return nuevaCancion;
    }
}