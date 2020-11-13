package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;

    @Enumerated(EnumType.STRING)
    @Column
    private ERole nombre;

    public Rol() {

    }

    public Rol(ERole nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id_rol;
    }

    public void setId(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public ERole getName() {
        return nombre;
    }

    public void setName(ERole nombre) {
        this.nombre = nombre;
    }
}
