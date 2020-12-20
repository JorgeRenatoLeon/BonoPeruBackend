package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrol;

    @Enumerated(EnumType.STRING)
    @Column
    private ERole nombre;

    public Rol() {

    }

    public Rol(ERole nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return idrol;
    }

    public void setId(Integer idrol) {
        this.idrol = idrol;
    }

    public ERole getName() {
        return nombre;
    }

    public void setName(ERole nombre) {
        this.nombre = nombre;
    }
}
