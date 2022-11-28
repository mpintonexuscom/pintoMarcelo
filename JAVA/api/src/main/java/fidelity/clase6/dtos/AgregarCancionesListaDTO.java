package fidelity.clase6.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgregarCancionesListaDTO implements Serializable {
    private List<Integer> listaCancionesIds;

    public AgregarCancionesListaDTO(){

    }
}
