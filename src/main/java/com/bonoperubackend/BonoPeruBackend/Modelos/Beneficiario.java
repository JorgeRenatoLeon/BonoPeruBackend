package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "beneficiario")
public class Beneficiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_beneficiario;

    private Integer fid_distrito;

    @NotBlank
    @Size(max = 10)
    private String codigo_familia;

    @NotBlank
    @Size(max = 1)
    private String genero;

    private Boolean es_discapacitado;

    private float puntuacion;

    private float penalidad;

    @NotBlank
    @Size(max = 3)
    private String estado;

    private Integer usuario_creacion;

    private Integer usuario_actualizacion;

    private Timestamp fecha_creacion;

    private Timestamp fecha_actualizacion;

    public Beneficiario(Integer fid_distrito, @NotBlank @Size(max = 10) String codigo_familia, @NotBlank @Size(max = 1) String genero, Boolean es_discapacitado, float puntuacion, float penalidad, @NotBlank @Size(max = 3) String estado, Integer usuario_creacion) {
        this.fid_distrito = fid_distrito;
        this.codigo_familia = codigo_familia;
        this.genero = genero;
        this.es_discapacitado = es_discapacitado;
        this.puntuacion = puntuacion;
        this.penalidad = penalidad;
        this.estado = estado;
        this.usuario_creacion = usuario_creacion;
    }

    public Beneficiario(){}

    public Integer getId_beneficiario() {
        return id_beneficiario;
    }

    public void setId_beneficiario(Integer id_beneficiario) {
        this.id_beneficiario = id_beneficiario;
    }

    public Integer getFid_distrito() {
        return fid_distrito;
    }

    public void setFid_distrito(Integer fid_distrito) {
        this.fid_distrito = fid_distrito;
    }

    public String getCodigo_familia() {
        return codigo_familia;
    }

    public void setCodigo_familia(String codigo_familia) {
        this.codigo_familia = codigo_familia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean getEs_discapacitado() {
        return es_discapacitado;
    }

    public void setEs_discapacitado(Boolean es_discapacitado) {
        this.es_discapacitado = es_discapacitado;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public float getPenalidad() {
        return penalidad;
    }

    public void setPenalidad(float penalidad) {
        this.penalidad = penalidad;
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
