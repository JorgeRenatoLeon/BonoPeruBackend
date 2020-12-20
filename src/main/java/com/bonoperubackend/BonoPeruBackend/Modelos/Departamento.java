package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddepartamento;

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

    public Departamento(@NotBlank Integer ubigeo, @NotBlank @Size(max = 100) String nombre, @NotBlank Integer zonariesgo, @NotBlank @Size(max = 3) String estado, Integer usuariocreacion) {
        this.ubigeo=ubigeo;
        this.nombre = nombre;
        this.zonariesgo = zonariesgo;
        this.estado = estado;
        this.usuariocreacion = usuariocreacion;
    }

    public Departamento(){}

    public Integer getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
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
