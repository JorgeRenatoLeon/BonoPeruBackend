package com.bonoperubackend.BonoPeruBackend.Modelos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "valores")
public class Valores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idvalores;

    @NotBlank
    @Size(max = 100)
    private String tabla;

    @NotBlank
    @Size(max = 3)
    private String abreviatura;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    private Timestamp fechacreacion;

    private Timestamp fechaactualizacion;

    public Valores(@NotBlank @Size(max = 100) String tabla, @NotBlank @Size(max = 3) String abreviatura, @NotBlank @Size(max = 100) String nombre, @Size(max = 500) String descripcion) {
        this.tabla = tabla;
        this.abreviatura = abreviatura;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Valores(){}

    public Integer getIdvalores() {
        return idvalores;
    }

    public void setIdvalores(Integer idvalores) {
        this.idvalores = idvalores;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
