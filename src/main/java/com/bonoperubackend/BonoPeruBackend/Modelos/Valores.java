package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "VALORES")
public class Valores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_VALORES;

    @NotBlank
    @Size(max = 100)
    private String TABLA;

    @NotBlank
    @Size(max = 3)
    private String ABREVIATURA;

    @NotBlank
    @Size(max = 100)
    private String NOMBRE;

    @Size(max = 500)
    private String DESCRIPCION;

    private int USUARIO_CREACION;

    private int USUARIO_ACTUALIZACION;

    public Valores(Integer ID_VALORES, String TABLA, String ABREVIATURA, String NOMBRE, String DESCRIPCION) {
        this.ID_VALORES = ID_VALORES;
        this.TABLA = TABLA;
        this.ABREVIATURA = ABREVIATURA;
        this.NOMBRE = NOMBRE;
        this.DESCRIPCION = DESCRIPCION;
    }

    public Valores(){}

    public Integer getID_VALORES() {
        return ID_VALORES;
    }

    public void setID_VALORES(Integer ID_VALORES) {
        this.ID_VALORES = ID_VALORES;
    }

    public String getTABLA() {
        return TABLA;
    }

    public void setTABLA(String TABLA) {
        this.TABLA = TABLA;
    }

    public String getABREVIATURA() {
        return ABREVIATURA;
    }

    public void setABREVIATURA(String ABREVIATURA) {
        this.ABREVIATURA = ABREVIATURA;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getUSUARIO_CREACION() {
        return USUARIO_CREACION;
    }

    public void setUSUARIO_CREACION(int USUARIO_CREACION) {
        this.USUARIO_CREACION = USUARIO_CREACION;
    }

    public int getUSUARIO_ACTUALIZACION() {
        return USUARIO_ACTUALIZACION;
    }

    public void setUSUARIO_ACTUALIZACION(int USUARIO_ACTUALIZACION) {
        this.USUARIO_ACTUALIZACION = USUARIO_ACTUALIZACION;
    }
}
