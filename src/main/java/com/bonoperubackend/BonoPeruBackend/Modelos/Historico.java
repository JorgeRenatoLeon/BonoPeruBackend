package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name="historico")
public class Historico implements Serializable {
    @Id
    private Integer id;
    private String nombre;
    private LocalDate fechaini;
    private LocalDate fechafin;
    private Integer beneficiarios;
    private Integer lugares;

    public Historico() {
        super();
    }

    public Historico(Integer id, String nombre, LocalDate fechaini, LocalDate fechafin, Integer beneficiarios, Integer lugares) {
        this.id = id;
        this.nombre = nombre;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.beneficiarios = beneficiarios;
        this.lugares = lugares;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaini() {
        return fechaini;
    }

    public void setFechaini(LocalDate fechaini) {
        this.fechaini = fechaini;
    }

    public LocalDate getFechafin() {
        return fechafin;
    }

    public void setFechafin(LocalDate fechafin) {
        this.fechafin = fechafin;
    }

    public Integer getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(Integer beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public Integer getLugares() {
        return lugares;
    }

    public void setLugares(Integer lugares) {
        this.lugares = lugares;
    }
}
