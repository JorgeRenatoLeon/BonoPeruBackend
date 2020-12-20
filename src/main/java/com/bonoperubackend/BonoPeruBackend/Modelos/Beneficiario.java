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
    private Integer idbeneficiario;

    private Integer fiddistrito;

    @NotBlank
    @Size(max = 10)
    private String codigofamilia;

    private Boolean femenino;

    private Boolean masculino;

    private Boolean esdiscapacitado;

    private float puntuacion;

    private float penalidad;

    @NotBlank
    @Size(max = 3)
    private String estado;

    private Integer usuariocreacion;

    private Integer usuarioactualizacion;

    private Timestamp fechacreacion;

    private Timestamp fechaactualizacion;

    public Beneficiario(Integer fiddistrito, @NotBlank @Size(max = 10) String codigofamilia, Boolean femenino, Boolean masculino, Boolean esdiscapacitado, float puntuacion, float penalidad, @NotBlank @Size(max = 3) String estado, Integer usuariocreacion) {
        this.fiddistrito = fiddistrito;
        this.codigofamilia = codigofamilia;
        this.femenino = femenino;
        this.masculino = masculino;
        this.esdiscapacitado = esdiscapacitado;
        this.puntuacion = puntuacion;
        this.penalidad = penalidad;
        this.estado = estado;
        this.usuariocreacion = usuariocreacion;
    }

    public Beneficiario(){}

    public Integer getIdbeneficiario() {
        return idbeneficiario;
    }

    public void setIdbeneficiario(Integer idbeneficiario) {
        this.idbeneficiario = idbeneficiario;
    }

    public Integer getFiddistrito() {
        return fiddistrito;
    }

    public void setFiddistrito(Integer fiddistrito) {
        this.fiddistrito = fiddistrito;
    }

    public String getCodigofamilia() {
        return codigofamilia;
    }

    public void setCodigofamilia(String codigofamilia) {
        this.codigofamilia = codigofamilia;
    }

    public Boolean getFemenino() {
        return femenino;
    }

    public void setFemenino(Boolean femenino) {
        this.femenino = femenino;
    }

    public Boolean getMasculino() {
        return masculino;
    }

    public void setMasculino(Boolean masculino) {
        this.masculino = masculino;
    }

    public Boolean getEsdiscapacitado() {
        return esdiscapacitado;
    }

    public void setEsdiscapacitado(Boolean esdiscapacitado) {
        this.esdiscapacitado = esdiscapacitado;
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
