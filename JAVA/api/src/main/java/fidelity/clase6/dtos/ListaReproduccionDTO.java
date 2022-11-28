package fidelity.clase6.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaReproduccionDTO implements Serializable {
    
    private int id;
    private String nombre;
    private List<CancionDTO> listaCanciones;

    public ListaReproduccionDTO(){
    }
}
