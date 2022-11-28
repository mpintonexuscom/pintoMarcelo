package fidelity.clase6.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancionDTO implements Serializable {
    
    private int id;
    private String nombre;
    private double duracion;

    public CancionDTO(){
    }
}
