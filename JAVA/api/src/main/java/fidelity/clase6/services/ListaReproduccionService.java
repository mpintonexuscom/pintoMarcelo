package fidelity.clase6.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fidelity.clase6.dtos.AgregarCancionesListaDTO;
import fidelity.clase6.dtos.NuevaListaReproduccionDTO;
import fidelity.clase6.model.Cancion;
import fidelity.clase6.model.ListaReproduccion;
import fidelity.clase6.repositories.CancionRepository;
import fidelity.clase6.repositories.ListaReproducionRepository;

@Service
public class ListaReproduccionService {

    @Autowired
    private ListaReproducionRepository listaRepo;

    @Autowired
    private CancionService cancionService;

    public ListaReproduccionService(){
    }

    public ListaReproduccion nuevaLista(NuevaListaReproduccionDTO nuevaListaDTO){

        ListaReproduccion nuevaLista = new ListaReproduccion();
        nuevaLista.setNombre(nuevaListaDTO.getNombre());

        /*List<Cancion> canciones = new ArrayList<Cancion>();
        for (Integer idCancion : nuevaListaDTO.getListaCancionesIds()) {
            // busco la cancion y la agrego a la lista
            Cancion cancion = cancionService.devolverCancionPorId(idCancion);
            if (cancion != null) {
                canciones.add(cancion);
            }
        }
        if (canciones.size() > 0) {
            nuevaLista.setListaCanciones(canciones);
        }*/

        listaRepo.save(nuevaLista);

        return nuevaLista;
    }

    public ListaReproduccion agregarCanciones(ListaReproduccion lista, AgregarCancionesListaDTO cancionesDTO){
        
        for (Integer idCancion : cancionesDTO.getListaCancionesIds()) {
            // busco la cancion y la agrego a la lista
            Cancion cancion = cancionService.devolverCancionPorId(idCancion);
            if (cancion != null) {
                lista.getListaCanciones().add(cancion); // esto hay que hacerlo con un @Query
            }
        }

        listaRepo.save(lista);

        return lista;
    }

    public ListaReproduccion devolverListaPorId(Integer id){
        Optional<ListaReproduccion> lista = listaRepo.findById(id);

        return lista.orElse(null);
    }

    public Iterable<ListaReproduccion> devolverListas(){
        Iterable<ListaReproduccion> listas = listaRepo.findAll();

        return listas;
    }

}