package fidelity.clase6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fidelity.clase6.dtos.AgregarCancionesListaDTO;
import fidelity.clase6.dtos.NuevaListaReproduccionDTO;
import fidelity.clase6.exceptions.NotFoundException;
import fidelity.clase6.model.ListaReproduccion;
import fidelity.clase6.services.ListaReproduccionService;

@RestController
public class ListaReproducionController {
    
    @Autowired
    private ListaReproduccionService listaService;

    @GetMapping("/listas")
    public ResponseEntity<Iterable<ListaReproduccion>> devolverListas() {
        return ResponseEntity.status(HttpStatus.OK).body(listaService.devolverListas());
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<ListaReproduccion> devolverListaPorId(@PathVariable Integer id){
        ListaReproduccion lista = listaService.devolverListaPorId(id);

        if (lista == null) {
			throw new NotFoundException("Lista no encontrada");
		}

        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PostMapping("/crearLista")
    public ResponseEntity<ListaReproduccion> nuevaLista(@RequestBody NuevaListaReproduccionDTO nuevaListaDTO){

        ListaReproduccion nuevaLista = listaService.nuevaLista(nuevaListaDTO);
        
        //return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaLista);
    }

    @PatchMapping("/agregarCancionesLista/{id}")
    public ResponseEntity<ListaReproduccion> agregarCancionesLista(@PathVariable Integer id, @RequestBody AgregarCancionesListaDTO cancionesDTO){

        ListaReproduccion lista = listaService.devolverListaPorId(id);

        if (lista == null) {
			throw new NotFoundException("Lista no encontrada");
		}

        lista = listaService.agregarCanciones(lista, cancionesDTO);
        
        //return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    /*
    @GetMapping("/listaDTO/{id}")
    ListaReproduccionDTO devolverAutoDTOPorId(@PathVariable Integer id){
        Optional<ListaReproduccion> lista = listaRepo.findById(id);

        ModelMapper mapper = new ModelMapper();
        ListaReproduccionDTO listaDTO = mapper.map(lista.get(), ListaReproduccionDTO.class);

        return listaDTO;
    }*/

}
