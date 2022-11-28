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
@Table(name = "rol")
public class Rol {
    
    @Id
    //valor generado en base de datos por el motor de la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRol")
    private int id;

    @Column(name = "Nombre")
    private String nombre;
}
