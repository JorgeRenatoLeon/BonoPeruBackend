package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.time.LocalTime;

@Entity(name="lugarentregaalgoritmo")
public class LugarEntregaAlgoritmo implements Serializable{
    @Id
    private Integer idlugarentrega;

    private String codigo;

    private Integer capacidad;

    private Integer cantbonosentregados;
    private Integer cantbonosnoentregados;
    private Integer cantquejas;
    private Integer nivelcontagio;

    private Integer ubigeodistrito;
    private Integer ubigeoprovincia;
    private Integer ubigeodepartamento;

    private Integer idhorariolugarentrega;


    public LugarEntregaAlgoritmo() {
        super();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getCantbonosentregados() {
        return cantbonosentregados;
    }

    public void setCantbonosentregados(Integer cantbonosentregados) {
        this.cantbonosentregados = cantbonosentregados;
    }

    public Integer getCantbonosnoentregados() {
        return cantbonosnoentregados;
    }

    public void setCantbonosnoentregados(Integer cantbonosnoentregados) {
        this.cantbonosnoentregados = cantbonosnoentregados;
    }

    public Integer getCantquejas() {
        return cantquejas;
    }

    public void setCantquejas(Integer cantquejas) {
        this.cantquejas = cantquejas;
    }

    public Integer getNivelcontagio() {
        return nivelcontagio;
    }

    public void setNivelcontagio(Integer nivelcontagio) {
        this.nivelcontagio = nivelcontagio;
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

    public Integer getIdlugarentrega() {
        return idlugarentrega;
    }

    public void setIdlugarentrega(Integer idlugarentrega) {
        this.idlugarentrega = idlugarentrega;
    }

    public Integer getIdhorariolugarentrega() {
        return idhorariolugarentrega;
    }

    public void setIdhorariolugarentrega(Integer idhorariolugarentrega) {
        this.idhorariolugarentrega = idhorariolugarentrega;
    }

}





