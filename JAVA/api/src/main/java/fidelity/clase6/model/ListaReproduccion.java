package fidelity.clase6.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
@Table(name = "listareproducion")
public class ListaReproduccion {

    @Id
    //valor generado en base de datos por el motor de la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLista")
    private int id;

    @Column(name = "Nombre")
    private String nombre;

    @OneToMany(fetch=FetchType.LAZY)
    private List<Cancion> listaCanciones;

    public ListaReproduccion() {
    }

    public ListaReproduccion(String nombre, List<Cancion> listaCanciones) {
        this.nombre = nombre;
        this.listaCanciones = listaCanciones;
    }
}
