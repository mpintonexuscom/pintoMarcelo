package fidelity.clase6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cancion")
public class Cancion {
    @Id
    //valor generado en base de datos por el motor de la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCancion")
    private int id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Duracion")
    private double duracion;

    public Cancion() {
    }

    public Cancion(String nombre, double duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }
}
