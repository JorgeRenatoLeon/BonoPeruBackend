package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="beneficiarioalgoritmo")
public class BeneficiarioAlgoritmo implements Serializable {


    @Id
    private Integer idbeneficiario;
    private String codigofamilia;
    private float penalidad;

    private Boolean esdiscapacitado;

    private Boolean femenino;
    private Boolean masculino;

    private Integer ubigeodistrito;
    private Integer ubigeoprovincia;
    private Integer ubigeodepartamento;

    public BeneficiarioAlgoritmo() {
        super();
    }

    public String getCodigofamilia() {
        return codigofamilia;
    }

    public void setCodigofamilia(String codigofamilia) {
        this.codigofamilia = codigofamilia;
    }

    public float getPenalidad() {
        return penalidad;
    }

    public void setPenalidad(float penalidad) {
        this.penalidad = penalidad;
    }

    public Boolean getEsdiscapacitado() {
        return esdiscapacitado;
    }

    public void setEsdiscapacitado(Boolean esdiscapacitado) {
        this.esdiscapacitado = esdiscapacitado;
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

    public Integer getUbigeodistrito() {
        return ubigeodistrito;
    }

    public void setUbigeodistrito(Integer ubigeodistrito) {
        this.ubigeodistrito = ubigeodistrito;
    }

    public Integer getUbigeoprovincia() {
        return ubigeoprovincia;
    }

    public void setUbigeoprovincia(Integer ubigeoprovincia) {
        this.ubigeoprovincia = ubigeoprovincia;
    }

    public Integer getUbigeodepartamento() {
        return ubigeodepartamento;
    }

    public void setUbigeodepartamento(Integer ubigeodepartamento) {
        this.ubigeodepartamento = ubigeodepartamento;
    }

    public Integer getIdbeneficiario() {
        return idbeneficiario;
    }

    public void setIdbeneficiario(Integer idbeneficiario) {
        this.idbeneficiario = idbeneficiario;
    }
}
