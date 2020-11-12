package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_DEPARTAMENTO;

    @NotBlank
    @Size(max = 100)
    private String NOMBRE;

    @NotBlank
    private Integer ZONA_RIESGO;

    @NotBlank
    @Size(max = 3)
    private String ESTADO;

    private int USUARIO_CREACION;

    private int USUARIO_ACTUALIZACION;

    public Departamento(@NotBlank @Size(max = 100) String NOMBRE, @NotBlank Integer ZONA_RIESGO, @NotBlank @Size(max = 3) String ESTADO) {
        this.NOMBRE = NOMBRE;
        this.ZONA_RIESGO = ZONA_RIESGO;
        this.ESTADO = ESTADO;
    }

    public Departamento(){}

    public Integer getID_DEPARTAMENTO() {
        return ID_DEPARTAMENTO;
    }

    public void setID_DEPARTAMENTO(Integer ID_DEPARTAMENTO) {
        this.ID_DEPARTAMENTO = ID_DEPARTAMENTO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public Integer getZONA_RIESGO() {
        return ZONA_RIESGO;
    }

    public void setZONA_RIESGO(Integer ZONA_RIESGO) {
        this.ZONA_RIESGO = ZONA_RIESGO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
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
