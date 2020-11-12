package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "provincia")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_PROVINCIA;
    @NotBlank
    private int FID_DEPARTAMENTO;
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

    public Provincia(@NotBlank int FID_DEPARTAMENTO, @NotBlank @Size(max = 100) String NOMBRE, @NotBlank Integer ZONA_RIESGO, @NotBlank @Size(max = 3) String ESTADO) {
        this.FID_DEPARTAMENTO = FID_DEPARTAMENTO;
        this.NOMBRE = NOMBRE;
        this.ZONA_RIESGO = ZONA_RIESGO;
        this.ESTADO = ESTADO;
    }

    public Provincia(){}

    public Integer getID_PROVINCIA() {
        return ID_PROVINCIA;
    }

    public void setID_PROVINCIA(Integer ID_PROVINCIA) {
        this.ID_PROVINCIA = ID_PROVINCIA;
    }

    public int getFID_DEPARTAMENTO() {
        return FID_DEPARTAMENTO;
    }

    public void setFID_DEPARTAMENTO(int FID_DEPARTAMENTO) {
        this.FID_DEPARTAMENTO = FID_DEPARTAMENTO;
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
