package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name="listacronograma")
public class ListaCronograma implements Serializable {
    @Id
    private Integer idlugarentrega;
    @Column(nullable=false, length=200)
    private String nombre;
    @Column(nullable=false, length=200)
    private String locacion;
    private LocalDate fecha;
    private LocalTime horainicio;
    private LocalTime horafin;
    private Integer aforo;
    private Integer mujeres;
    private Integer discapacitados;
    private Integer riesgo;
    private Integer beneficiarios;

    public ListaCronograma() {
        super();
    }

    public ListaCronograma(Integer idlugarentrega, String nombre, String locacion, LocalDate fecha, LocalTime horainicio, LocalTime horafin, Integer aforo, Integer mujeres, Integer discapacitados, Integer riesgo, Integer beneficiarios) {
        this.idlugarentrega = idlugarentrega;
        this.nombre = nombre;
        this.locacion = locacion;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.aforo = aforo;
        this.mujeres = mujeres;
        this.discapacitados = discapacitados;
        this.riesgo = riesgo;
        this.beneficiarios=beneficiarios;
    }

    public Integer getIdlugarentrega() {
        return idlugarentrega;
    }

    public void setIdlugarentrega(Integer idlugarentrega) {
        this.idlugarentrega = idlugarentrega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(LocalTime horainicio) {
        this.horainicio = horainicio;
    }

    public LocalTime getHorafin() {
        return horafin;
    }

    public void setHorafin(LocalTime horafin) {
        this.horafin = horafin;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Integer getMujeres() {
        return mujeres;
    }

    public void setMujeres(Integer mujeres) {
        this.mujeres = mujeres;
    }

    public Integer getDiscapacitados() {
        return discapacitados;
    }

    public void setDiscapacitados(Integer discapacitados) {
        this.discapacitados = discapacitados;
    }

    public Integer getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Integer riesgo) {
        this.riesgo = riesgo;
    }

    public Integer getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(Integer beneficiarios) {
        this.beneficiarios = beneficiarios;
    }
}
