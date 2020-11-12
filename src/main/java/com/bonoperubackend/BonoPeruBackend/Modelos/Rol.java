package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_ROL;

    @Enumerated(EnumType.STRING)
    @Column
    private ERole NOMBRE;

    public Rol() {

    }

    public Rol(ERole NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public Integer getId() {
        return ID_ROL;
    }

    public void setId(Integer ID_ROL) {
        this.ID_ROL = ID_ROL;
    }

    public ERole getName() {
        return NOMBRE;
    }

    public void setName(ERole NOMBRE) {
        this.NOMBRE = NOMBRE;
    }
}
