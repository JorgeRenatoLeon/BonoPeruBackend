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
    private Integer id_provincia;

    private Integer fid_departamento;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    private Integer zona_riesgo;

    @NotBlank
    @Size(max = 3)
    private String estado;

    private Integer usuario_creacion;

    @Null
    private Integer usuario_actualizacion;

    private Timestamp fecha_creacion;

    private Timestamp fecha_actualizacion;

    public Provincia(Integer fid_departamento, @NotBlank @Size(max = 100) String nombre, Integer zona_riesgo, @NotBlank @Size(max = 3) String estado, Integer usuario_creacion) {
        this.fid_departamento = fid_departamento;
        this.nombre = nombre;
        this.zona_riesgo = zona_riesgo;
        this.estado = estado;
        this.usuario_creacion = usuario_creacion;
    }

    public Provincia(){};

    public Integer getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(Integer id_provincia) {
        this.id_provincia = id_provincia;
    }

    public Integer getFid_departamento() {
        return fid_departamento;
    }

    public void setFid_departamento(Integer fid_departamento) {
        this.fid_departamento = fid_departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getZona_riesgo() {
        return zona_riesgo;
    }

    public void setZona_riesgo(Integer zona_riesgo) {
        this.zona_riesgo = zona_riesgo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getUsuario_creacion() {
        return usuario_creacion;
    }

    public void setUsuario_creacion(Integer usuario_creacion) {
        this.usuario_creacion = usuario_creacion;
    }

    public Integer getUsuario_actualizacion() {
        return usuario_actualizacion;
    }

    public void setUsuario_actualizacion(Integer usuario_actualizacion) {
        this.usuario_actualizacion = usuario_actualizacion;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Timestamp getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Timestamp fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
}
