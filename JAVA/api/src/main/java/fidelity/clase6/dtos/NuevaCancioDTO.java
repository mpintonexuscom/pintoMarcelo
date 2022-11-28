package fidelity.clase6.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevaCancioDTO implements Serializable {
    
    private String nombre;
    private double duracion;

    public NuevaCancioDTO(){
    }
    
}