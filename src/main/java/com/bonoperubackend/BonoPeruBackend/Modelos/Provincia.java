package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "provincia")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idprovincia;

    private Integer fiddepartamento;

    private Integer ubigeo;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    private Integer zonariesgo;

    @NotBlank
    @Size(max = 3)
    private String estado;

    private Integer usuariocreacion;

    @Null
    private Integer usuarioactualizacion;

    private Timestamp fechacreacion;

    private Timestamp fechaactualizacion;

    public Provincia(Integer fiddepartamento, Integer ubigeo, @NotBlank @Size(max = 100) String nombre, Integer zonariesgo, @NotBlank @Size(max = 3) String estado, Integer usuariocreacion) {
        this.fiddepartamento = fiddepartamento;
        this.ubigeo=ubigeo;
        this.nombre = nombre;
        this.zonariesgo = zonariesgo;
        this.estado = estado;
        this.usuariocreacion = usuariocreacion;
    }

    public Provincia(){}

    public Integer getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(Integer idprovincia) {
        this.idprovincia = idprovincia;
    }

    public Integer getFiddepartamento() {
        return fiddepartamento;
    }

    public void setFiddepartamento(Integer fiddepartamento) {
        this.fiddepartamento = fiddepartamento;
    }

    public Integer getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Integer ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getZonariesgo() {
        return zonariesgo;
    }

    public void setZonariesgo(Integer zonariesgo) {
        this.zonariesgo = zonariesgo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getUsuariocreacion() {
        return usuariocreacion;
    }

    public void setUsuariocreacion(Integer usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    public Integer getUsuarioactualizacion() {
        return usuarioactualizacion;
    }

    public void setUsuarioactualizacion(Integer usuarioactualizacion) {
        this.usuarioactualizacion = usuarioactualizacion;
    }

    public Timestamp getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Timestamp fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Timestamp getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Timestamp fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }
}
