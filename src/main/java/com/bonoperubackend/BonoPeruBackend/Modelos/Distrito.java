package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "distrito")
public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_distrito;

    private Integer fid_provincia;

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

    public Distrito(Integer fid_provincia, @NotBlank @Size(max = 100) String nombre, Integer zona_riesgo, @NotBlank @Size(max = 3) String estado, Integer usuario_creacion) {
        this.fid_provincia = fid_provincia;
        this.nombre = nombre;
        this.zona_riesgo = zona_riesgo;
        this.estado = estado;
        this.usuario_creacion = usuario_creacion;
    }

    public Distrito(){}

    public Integer getId_distrito() {
        return id_distrito;
    }

    public void setId_distrito(Integer id_distrito) {
        this.id_distrito = id_distrito;
    }

    public Integer getFid_provincia() {
        return fid_provincia;
    }

    public void setFid_provincia(Integer fid_provincia) {
        this.fid_provincia = fid_provincia;
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
