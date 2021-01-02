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
    private Integer idbeneficiario;
    @Column(nullable=false, length=200)
    private String codigofamilia;
    @Column(nullable=false, length=200)
    private String departamento;
    @Column(nullable=false, length=200)
    private String provincia;
    @Column(nullable=false, length=200)
    private String distrito;
    @Column(nullable=false, length=10)
    private String sexo;
    @Column(nullable=false, length=10)
    private String discapacitado;
    private Integer numhorarios;
    @Column(nullable=false, length=10)
    private String codigo1;
    private LocalDate fecha1;
    private LocalTime horainicio1;
    private LocalTime horafin1;
    @Column(nullable=false, length=10)
    private String estado1;
    @Column(nullable=false, length=10)
    private String codigo2;
    private LocalDate fecha2;
    private LocalTime horainicio2;
    private LocalTime horafin2;
    @Column(nullable=false, length=10)
    private String estado2;
    private Integer incidentes;

    public ListaCronograma() {
        super();
    }

    public ListaCronograma(Integer idbeneficiario, String codigofamilia, String departamento, String provincia, String distrito, String sexo, String discapacitado, Integer numhorarios, String codigo1, LocalDate fecha1, LocalTime horainicio1, LocalTime horafin1, String estado1, String codigo2, LocalDate fecha2, LocalTime horainicio2, LocalTime horafin2, String estado2, Integer incidentes) {
        this.idbeneficiario = idbeneficiario;
        this.codigofamilia = codigofamilia;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.sexo = sexo;
        this.discapacitado = discapacitado;
        this.numhorarios = numhorarios;
        this.codigo1 = codigo1;
        this.fecha1 = fecha1;
        this.horainicio1 = horainicio1;
        this.horafin1 = horafin1;
        this.estado1 = estado1;
        this.codigo2 = codigo2;
        this.fecha2 = fecha2;
        this.horainicio2 = horainicio2;
        this.horafin2 = horafin2;
        this.estado2 = estado2;
        this.incidentes = incidentes;
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

    public Integer getIdbeneficiario() {
        return idbeneficiario;
    }

    public void setIdbeneficiario(Integer idbeneficiario) {
        this.idbeneficiario = idbeneficiario;
    }

    public String getCodigofamilia() {
        return codigofamilia;
    }

    public void setCodigofamilia(String codigofamilia) {
        this.codigofamilia = codigofamilia;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDiscapacitado() {
        return discapacitado;
    }

    public void setDiscapacitado(String discapacitado) {
        this.discapacitado = discapacitado;
    }

    public Integer getNumhorarios() {
        return numhorarios;
    }

    public void setNumhorarios(Integer numhorarios) {
        this.numhorarios = numhorarios;
    }

    public String getCodigo1() {
        return codigo1;
    }

    public void setCodigo1(String codigo1) {
        this.codigo1 = codigo1;
    }

    public LocalDate getFecha1() {
        return fecha1;
    }

    public void setFecha1(LocalDate fecha1) {
        this.fecha1 = fecha1;
    }

    public LocalTime getHorainicio1() {
        return horainicio1;
    }

    public void setHorainicio1(LocalTime horainicio1) {
        this.horainicio1 = horainicio1;
    }

    public LocalTime getHorafin1() {
        return horafin1;
    }

    public void setHorafin1(LocalTime horafin1) {
        this.horafin1 = horafin1;
    }

    public String getEstado1() {
        return estado1;
    }

    public void setEstado1(String estado1) {
        this.estado1 = estado1;
    }

    public String getCodigo2() {
        return codigo2;
    }

    public void setCodigo2(String codigo2) {
        this.codigo2 = codigo2;
    }

    public LocalDate getFecha2() {
        return fecha2;
    }

    public void setFecha2(LocalDate fecha2) {
        this.fecha2 = fecha2;
    }

    public LocalTime getHorainicio2() {
        return horainicio2;
    }

    public void setHorainicio2(LocalTime horainicio2) {
        this.horainicio2 = horainicio2;
    }

    public LocalTime getHorafin2() {
        return horafin2;
    }

    public void setHorafin2(LocalTime horafin2) {
        this.horafin2 = horafin2;
    }

    public String getEstado2() {
        return estado2;
    }

    public void setEstado2(String estado2) {
        this.estado2 = estado2;
    }

    public Integer getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(Integer incidentes) {
        this.incidentes = incidentes;
    }
}
