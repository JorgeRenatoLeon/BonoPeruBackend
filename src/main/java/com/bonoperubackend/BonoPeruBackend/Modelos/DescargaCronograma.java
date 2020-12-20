package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name="descargarcronograma")
public class DescargaCronograma implements Serializable {
    @Id
    private Integer idlugarentrega;
    private String departamento;
    private String provincia;
    private String distrito;
    private String lugar;
    private LocalDate fecha;
    private LocalTime horainicio;
    private LocalTime horafin;
    private String codigofamilia;

    public DescargaCronograma() {
        super();
    }

    public DescargaCronograma(Integer idlugarentrega, String departamento, String provincia, String distrito, String lugar, LocalDate fecha, LocalTime horainicio, LocalTime horafin, String codigofamilia) {
        this.idlugarentrega = idlugarentrega;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.lugar = lugar;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.codigofamilia = codigofamilia;
    }

    public Integer getIdlugarentrega() {
        return idlugarentrega;
    }

    public void setIdlugarentrega(Integer idlugarentrega) {
        this.idlugarentrega = idlugarentrega;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
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

    public String getCodigofamilia() {
        return codigofamilia;
    }

    public void setCodigofamilia(String codigofamilia) {
        this.codigofamilia = codigofamilia;
    }
}
