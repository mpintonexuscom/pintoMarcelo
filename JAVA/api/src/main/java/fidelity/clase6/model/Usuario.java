package fidelity.clase6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario {
    
    @Id
    //valor generado en base de datos por el motor de la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private int id;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @OneToOne
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
